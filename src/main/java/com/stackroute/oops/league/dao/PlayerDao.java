package com.stackroute.oops.league.dao;

import com.stackroute.oops.league.exception.PlayerAlreadyExistsException;
import com.stackroute.oops.league.exception.PlayerNotFoundException;
import com.stackroute.oops.league.model.Player;

import java.util.List;

public interface PlayerDao {

    boolean addPlayer(Player player, String fileName) throws PlayerAlreadyExistsException;

    List<Player> getAllPlayers(String fileName);

    Player findPlayer(String playerId, String fileName) throws PlayerNotFoundException;


}
