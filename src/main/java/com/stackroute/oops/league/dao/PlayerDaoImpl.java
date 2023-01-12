package com.stackroute.oops.league.dao;

import com.stackroute.oops.league.exception.PlayerAlreadyExistsException;
import com.stackroute.oops.league.exception.PlayerNotFoundException;
import com.stackroute.oops.league.main;
import com.stackroute.oops.league.model.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is implementing the PlayerDao interface
 * This class has one field playerList to store list of players
 */
public class PlayerDaoImpl implements PlayerDao {

    LinkedList<Player> playerList;

    public PlayerDaoImpl() {
        playerList = new LinkedList<>();
    }

    /**
     * Constructor to initialize an empty ArrayList for playerList
     * <p>
     * /**
     * addPlayer has to return true if player object is stored in "player.csv" as comma separated fields successfully
     * proceed to store player details in player.csv only when password length is greater than six and yearExpr is greater than zero
     * addPlayer has to return false if any of above condition doesn't match
     * throw PlayerAlreadyExistsException if player with same playerId already exist
     */
    @Override
    public boolean addPlayer(Player player, String fileName) throws PlayerAlreadyExistsException {
        FileOutputStream f;
        ObjectOutputStream o;

        if ((player == null || player.getPassword().length() < 6 || player.getYearExpr() <= 0)) {
            return false;
        }

        if (playerList.contains(player)) {
            throw new PlayerAlreadyExistsException();
        }

        try {
            f = new FileOutputStream(fileName);
            o = new ObjectOutputStream(f);

            o.writeObject(player);
            playerList.add(player);

            o.close();
            f.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /*Return the list of player objects by reading data from the file "player.csv"
     */
    @Override
    public List<Player> getAllPlayers(String fileName) {
        // Read file in try block and catch any exception in catch block
        List<Player> players = new ArrayList<>();
        try {
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);

            Player player;

            do {
                player = (Player) oi.readObject();
                if (player != null) {
                    players.add(player);
                }
            } while (player != null);
            oi.close();
            fi.close();
        } catch (Exception e) {
            // catch any type of exception and print the same
            e.printStackTrace();
        }

        return players;
    }

    /**
     * Return Player object given playerId to search
     * Throw PlayerNotFoundException if player with playerId doesn't match with any player
     */
    @Override
    public Player findPlayer(String playerId, String fileName) throws PlayerNotFoundException {
        /* Throw PlayerNotFoundException if provided playerId is either null, empty or blank*/
        if (playerId == null || playerId.trim().isEmpty()) {
            throw new PlayerNotFoundException();
        }

        List<Player> allPlayers = getAllPlayers(fileName);
        for (Player player : allPlayers) {
            if (player.getPlayerId().equals(playerId)) {
                //System.out.println(player);
                return player;
            }
        }
        throw new PlayerNotFoundException();
    }
}
