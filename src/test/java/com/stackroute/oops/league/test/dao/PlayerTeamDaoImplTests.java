package com.stackroute.oops.league.test.dao;

import com.stackroute.oops.league.dao.PlayerDao;
import com.stackroute.oops.league.dao.PlayerDaoImpl;
import com.stackroute.oops.league.dao.PlayerTeamDao;
import com.stackroute.oops.league.dao.PlayerTeamDaoImpl;
import com.stackroute.oops.league.exception.PlayerAlreadyExistsException;
import com.stackroute.oops.league.exception.PlayerNotFoundException;
import com.stackroute.oops.league.model.Player;
import com.stackroute.oops.league.model.PlayerTeam;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlayerTeamDaoImplTests {
    private static final String MESSAGE_ONE = "PlayerTeamDaoImpl should be initialized with an empty LinkedList properly";
    private static final String MESSAGE_TWO = "Given valid player data should add player to database and return true";
    private static final String MESSAGE_THREE = "Given invalid or empty player id should throw exception";
    private static final String MESSAGE_FOUR = "Given Valid playerId should return the player object";
    private static final String MESSAGE_FIVE = "Given empty player repo when searched should throw exception";
    private static final String MESSAGE_SIX = "getAllPlayers should return player list of all players";
    private static final String MESSAGE_SEVEN = "Given player id should delete the player";
    private static final String MESSAGE_EIGHT = "Given player id as null to search should return null";
    private static PlayerTeamDao playerTeamDao;
    private static PlayerDao playerDao;
    private static Player player;
    private static PlayerTeam playerTeam;
    private static final String PLAYER_FILE_NAME="src/test/resources/player.csv";
    private static final String TEAM_FILE_NAME = "src/test/resources/finalteam.csv";

    @BeforeAll
    public static void setUp() throws Exception {
        playerTeamDao = new PlayerTeamDaoImpl();
        playerDao = new PlayerDaoImpl();
        player = new Player("001", "praveen", "password", 7);
        player.setTeamTitle("HIPHOP");
        playerTeam = new PlayerTeam("001", "HIPHOP");
    }

    @AfterAll
    public static void tearDown() {
        player = null;
        playerDao = null;
        playerTeamDao = null;
        playerTeam = null;
    }

    @Test
    @Order(1)
    public void givenPlayerDaoObjectThenPlayerListSetAsArrayList() {
        assertThat(MESSAGE_ONE, playerTeamDao.getAllPlayerTeams(TEAM_FILE_NAME), is(empty()));
    }

    @Test
    @Order(2)
    public void givenPlayerDataWhenAddedThenReturnTrue() throws PlayerNotFoundException, PlayerAlreadyExistsException {
        playerDao.addPlayer(player, PLAYER_FILE_NAME);
        assertTrue(playerTeamDao.addPlayerToTeam(player, TEAM_FILE_NAME, PLAYER_FILE_NAME), MESSAGE_TWO);
    }

    @Test
    @Order(3)
    public void givenFileNameMustReturnAllTeam() {
        assertFalse(playerTeamDao.getAllPlayerTeams(TEAM_FILE_NAME).isEmpty(), MESSAGE_TWO);
    }

    @Test
    @Order(4)
    public void givenFileNameAndTeamNameMustReturnTeam() {
        assertFalse(playerTeamDao.getPlayerSetByTeamTitle("HIPHOP",TEAM_FILE_NAME).isEmpty(), MESSAGE_TWO);
    }

    @Test
    @Order(5)
    public void givenFileNameMustReturnEmptyWhenFileIsEmpty() throws FileNotFoundException {
        new PrintWriter(PLAYER_FILE_NAME).close();
        new PrintWriter(TEAM_FILE_NAME).close();
        assertTrue(playerTeamDao.getAllPlayerTeams(TEAM_FILE_NAME).isEmpty(), MESSAGE_TWO);
    }
}
