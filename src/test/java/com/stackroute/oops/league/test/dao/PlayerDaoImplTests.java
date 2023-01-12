package com.stackroute.oops.league.test.dao;

import com.stackroute.oops.league.dao.PlayerDao;
import com.stackroute.oops.league.dao.PlayerDaoImpl;
import com.stackroute.oops.league.exception.PlayerAlreadyExistsException;
import com.stackroute.oops.league.exception.PlayerNotFoundException;
import com.stackroute.oops.league.model.Player;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlayerDaoImplTests {
    private static final String MESSAGE_ONE = "PlayerDaoImpl should be initialized with an empty LinkedList properly";
    private static final String MESSAGE_TWO = "Given valid player data should add player to database and return true";
    private static final String MESSAGE_THREE = "Given invalid or empty player id should throw exception";
    private static final String MESSAGE_FOUR = "Given Valid playerId should return the player object";
    private static final String MESSAGE_FIVE = "Given empty player repo when searched should throw exception";
    private static final String MESSAGE_SIX = "getAllPlayers should return player list of all players";
    private static final String MESSAGE_SEVEN = "Given player id should delete the player";
    private static final String MESSAGE_EIGHT = "Given player id as null to search should return null";
    private static PlayerDao playerDao;
    private static Player player1;
    private static Player player2;
    private static final String PLAYER_FILE_NAME="src/test/resources/player.csv";
    private static final String TEAM_FILE_NAME = "src/test/resources/finalteam.csv";

    @BeforeAll
    public static void setUp() throws Exception {
        playerDao = new PlayerDaoImpl();
        player1 = new Player("001", "John", "password", 7);
        player2 = new Player("002", "Zid", "1234", 7);
    }

    @AfterAll
    public static void tearDown() {
        player1 = null;
        player2 = null;
        playerDao = null;
    }

    @Test
    @Order(1)
    public void givenPlayerDaoObjectThenPlayerListSetAsArrayList() {
        assertThat(MESSAGE_ONE, playerDao.getAllPlayers(PLAYER_FILE_NAME), is(empty()));
    }

    @Test
    @Order(2)
    public void givenPlayerDataWhenAddedThenReturnTrue() throws PlayerAlreadyExistsException {
        assertTrue(playerDao.addPlayer(player1, PLAYER_FILE_NAME), MESSAGE_TWO);
    }

    @Test
    @Order(3)
    public void givenPlayerDataWithPasswordLength4() throws PlayerAlreadyExistsException {
        assertFalse(playerDao.addPlayer(player2, PLAYER_FILE_NAME), MESSAGE_TWO);
    }

    @Test
    @Order(4)
    public void givenPlayerDataAddedThenReturnAllPlayers() {
        List<Player> expected = playerDao.getAllPlayers(PLAYER_FILE_NAME);
        System.out.println(expected.get(0));
        assertEquals(player1.getPlayerId(), expected.get(0).getPlayerId(), MESSAGE_SIX);
    }

    @Test
    @Order(5)
    public void givenValidPlayerIdThenReturnPlayer() throws PlayerNotFoundException {
        Player expected = playerDao.findPlayer("001",PLAYER_FILE_NAME);
        assertEquals(player1.getPlayerId(), expected.getPlayerId(), MESSAGE_FOUR);
    }

    @Test
    @Order(6)
    public void givenPlayerDetailsWhenUpdatedThenReturnTrue(){
            player1.setTeamTitle("Hiphop");
    }

    @Test
    @Order(7)
    public void givenNonExistingOrEmptyPlayerIdThenThrowException() {
        assertThrows(PlayerNotFoundException.class, () -> playerDao.findPlayer("002", PLAYER_FILE_NAME), MESSAGE_THREE);
        assertThrows(PlayerNotFoundException.class, () -> playerDao.findPlayer("", PLAYER_FILE_NAME), MESSAGE_THREE);
    }

    @Test
    @Order(8)
    public void givenInValidPlayerIdThenReturnNull() {
        assertThrows(PlayerNotFoundException.class, () -> playerDao.findPlayer("", PLAYER_FILE_NAME), MESSAGE_THREE);
        assertThrows(PlayerNotFoundException.class, () -> playerDao.findPlayer("  ", PLAYER_FILE_NAME), MESSAGE_THREE);
        assertThrows(PlayerNotFoundException.class, () -> playerDao.findPlayer(null, PLAYER_FILE_NAME), MESSAGE_THREE);
    }
    @Test
    @Order(9)
    public void givenAllPlayerDataDeletedWhenSearchedThenThrowException() throws FileNotFoundException {
        new PrintWriter(PLAYER_FILE_NAME).close();
        assertThrows(PlayerNotFoundException.class, () -> playerDao.findPlayer("001", PLAYER_FILE_NAME), MESSAGE_FIVE);
    }

}