package com.stackroute.oops.league.dao;

import com.stackroute.oops.league.exception.PlayerNotFoundException;
import com.stackroute.oops.league.main;
import com.stackroute.oops.league.model.Player;
import com.stackroute.oops.league.model.PlayerTeam;

import java.io.*;
import java.util.*;


/**
 * This class implements the PlayerTeamDao interface
 * This class has three private fields playerTeamSet,playerDao
 */
@SuppressWarnings("unchecked")
public class PlayerTeamDaoImpl implements PlayerTeamDao {


    TreeSet<PlayerTeam> playerTeamSet;
    PlayerDaoImpl playerDao;

    /**
     * Constructor to initialize an empty TreeSet and PlayerDao object
     */
    public PlayerTeamDaoImpl() {
        playerTeamSet = new TreeSet<>();
        playerDao = new PlayerDaoImpl();
    }


    /*Add given player to finalteam.csv file
     * return true if playerId and teamTitle is stored in "finalteam.csv" as comma separated fields successfully
     * return false if provided 'player.teamTitle' is equal to null
     * check if provided player already exist in "player.csv" file by making a call to 'playerDao.findPlayer(playerId)'
     * throw PlayerNotFoundException if playerDao.findPlayer(playerId) returns null  */
    @Override
    public boolean addPlayerToTeam(Player player, String teamFileName, String playerFileName) throws PlayerNotFoundException {

        if (player.getTeamTitle() == null) {
            return false;
        }

        playerDao.findPlayer(player.getPlayerId(), playerFileName);

        FileOutputStream f;
        ObjectOutputStream o;

        try {
            f = new FileOutputStream(teamFileName);
            o = new ObjectOutputStream(f);

            PlayerTeam playerTeam = new PlayerTeam(player.getPlayerId(), player.getTeamTitle());

            o.writeObject(playerTeam);
            playerTeamSet.add(playerTeam);

            o.close();
            f.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    //Return the set of all PlayerTeam by reading the file content from "finalteam.csv" file
    @Override
    public Set<PlayerTeam> getAllPlayerTeams(String fileName) {
        /* read file in try block and catch any typeof exception raised in catch block*/

        Set<PlayerTeam> playersTeams = new HashSet<>();
        try {
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);

            PlayerTeam player;

            do {
                player = (PlayerTeam) oi.readObject();
                if (player != null) {
                    playersTeams.add(player);
                }
            } while (player != null);
            oi.close();
            fi.close();
        } catch (Exception e) {
            // catch any type of exception and print the same
            e.printStackTrace();
        }

        /* Catch any type of exception caused and print the exception message*/
        return playersTeams;
    }

    /*
   Returns the set of playerTeam belonging to a particular teamTitle
   one can get to know about playerTeam and their corresponding team by "finalteam.csv" file
    */
    @Override
    public Set<PlayerTeam> getPlayerSetByTeamTitle(String teamTitle, String fileName) {

        Set<PlayerTeam> teams = new HashSet<>();

        for (PlayerTeam team : getAllPlayerTeams(fileName)) {
            if(team.getTeamTitle().equals(teamTitle)){
                teams.add(team);
            }
        }

        return teams;
    }
}
