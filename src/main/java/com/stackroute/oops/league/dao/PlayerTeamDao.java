package com.stackroute.oops.league.dao;

import com.stackroute.oops.league.exception.PlayerNotFoundException;
import com.stackroute.oops.league.model.Player;
import com.stackroute.oops.league.model.PlayerTeam;

import java.util.Set;

public interface PlayerTeamDao {
    boolean addPlayerToTeam(Player player, String teamFileName, String playerFileName) throws PlayerNotFoundException;

    Set<PlayerTeam> getAllPlayerTeams(String fileName);

    Set<PlayerTeam> getPlayerSetByTeamTitle(String teamTitle, String fileName);
}
