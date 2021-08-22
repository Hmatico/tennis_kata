package match;

import game.TennisGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MatchManagerTest {

    private MatchManager matchManager;
    Field scoreBoardField;

    @BeforeEach
    void initEach() throws NoSuchFieldException {
        matchManager = new MatchManager("Alpha", "Beta");
        scoreBoardField = matchManager.getClass().getDeclaredField("scoreBoard");
        scoreBoardField.setAccessible(true);
    }

    @Test
    void testScorePointToWinAGame() throws IllegalAccessException, NoSuchFieldException {
        Map<String, Player> scoreBoard = new HashMap<>();
        scoreBoard.put("Alpha", createPlayerWithSetAndGameScore(5,3));
        scoreBoard.put("Beta", createPlayerWithSetAndGameScore(5,2));
        scoreBoardField.set(matchManager, scoreBoard);
        Field tennisGameField = matchManager.getClass().getDeclaredField("currentGame");
        tennisGameField.setAccessible(true);
        tennisGameField.set(matchManager, new TennisGame(scoreBoard));

        matchManager.scorePoint("Alpha", "Beta");

        assertEquals(6, scoreBoard.get("Alpha").getSetScore());
        assertEquals(0, scoreBoard.get("Alpha").getCurrentGamePoints());
        assertEquals(5, scoreBoard.get("Beta").getSetScore());
        assertEquals(0, scoreBoard.get("Beta").getCurrentGamePoints());
    }

    @Test
    void testScorePointAlreadyWin() throws IllegalAccessException {
        Map<String, Player> scoreBoard = new HashMap<>();
        scoreBoard.put("Alpha", createPlayerWithSetAndGameScore(7,0));
        scoreBoard.put("Beta", createPlayerWithSetAndGameScore(5,0));
        scoreBoardField.set(matchManager, scoreBoard);

        matchManager.scorePoint("Alpha", "Beta");

        assertEquals(0, scoreBoard.get("Alpha").getCurrentGamePoints());

        matchManager.scorePoint("Beta", "Alpha");

        assertEquals(0, scoreBoard.get("Alpha").getCurrentGamePoints());
    }

    @Test
    void testGetMatchWinnerIsNull() {
        assertNull(matchManager.getMatchWinner("Alpha", "Beta"));
    }

    @Test
    void testGetMatchWinnerIsFirstPlayer() {
        String expectedWinner = "Alpha";

        for (int i = 0; i < 36; i++) {
            matchManager.scorePoint("Alpha", "Beta");
        }

        assertEquals(expectedWinner, matchManager.getMatchWinner("Alpha", "Beta"));
    }

    @Test
    void testGetMatchWinnerIsSecondPlayer() {
        String expectedWinner = "Beta";

        for (int i = 0; i <= 36; i++) {
            matchManager.scorePoint("Beta", "Alpha");
        }

        assertEquals(expectedWinner, matchManager.getMatchWinner("Alpha", "Beta"));
    }

    @Test
    void testGetMatchWinner() throws IllegalAccessException {
        Map<String, Player> scoreBoard = new HashMap<>();
        scoreBoard.put("Alpha", createPlayerWithSetAndGameScore(5,0));
        scoreBoard.put("Beta", createPlayerWithSetAndGameScore(5,0));
        scoreBoardField.set(matchManager, scoreBoard);

        assertNull(matchManager.getMatchWinner("Alpha", "Beta"));

        String expectedWinner = "Alpha";
        scoreBoard.put("Alpha", createPlayerWithSetAndGameScore(6,0));
        scoreBoard.put("Beta", createPlayerWithSetAndGameScore(4,0));
        scoreBoardField.set(matchManager, scoreBoard);

        assertEquals(expectedWinner, matchManager.getMatchWinner("Alpha", "Beta"));
    }

    @Test
    void testGetScorePlayer() throws IllegalAccessException, NoSuchFieldException {

        assertNull(matchManager.getScorePlayer("Toto"));

        Map<String, Player> scoreBoard = new HashMap<>();
        Player alpha = createPlayerWithSetAndGameScore(3,3);
        Player beta = createPlayerWithSetAndGameScore(5,2);
        Field alphaScoreField = alpha.getClass().getDeclaredField("currentGameScore");
        alphaScoreField.setAccessible(true);
        alphaScoreField.set(alpha, "40");
        Field betaScoreField = beta.getClass().getDeclaredField("currentGameScore");
        betaScoreField.setAccessible(true);
        betaScoreField.set(beta, "30");
        scoreBoard.put("Alpha", alpha);
        scoreBoard.put("Beta", beta);
        scoreBoardField.set(matchManager, scoreBoard);

        String expectedScoreAlpha = "Player Alpha : score set = 3 | game score = 40\n";
        String expectedScoreBeta= "Player Beta : score set = 5 | game score = 30\n";

        assertEquals(expectedScoreAlpha, matchManager.getScorePlayer("Alpha"));
        assertEquals(expectedScoreBeta, matchManager.getScorePlayer("Beta"));
    }

    @Test
    void testGetScore() throws NoSuchFieldException, IllegalAccessException {
        Map<String, Player> scoreBoard = new HashMap<>();
        Player alpha = createPlayerWithSetAndGameScore(3,3);
        Player beta = createPlayerWithSetAndGameScore(5,2);
        Field alphaScoreField = alpha.getClass().getDeclaredField("currentGameScore");
        alphaScoreField.setAccessible(true);
        alphaScoreField.set(alpha, "40");
        Field betaScoreField = beta.getClass().getDeclaredField("currentGameScore");
        betaScoreField.setAccessible(true);
        betaScoreField.set(beta, "30");
        scoreBoard.put("Alpha", alpha);
        scoreBoard.put("Beta", beta);
        scoreBoardField.set(matchManager, scoreBoard);

        String expectedScoreAlpha = "Player Alpha : score set = 3 | game score = 40\n";
        String expectedScoreBeta= "Player Beta : score set = 5 | game score = 30\n";

        assertEquals(expectedScoreAlpha+expectedScoreBeta, matchManager.getScore());
    }

    private Player createPlayerWithSetAndGameScore(int setScore, int gameScore){
        Player player = new Player();
        for (int i = 0; i < setScore ; i++) {
            player.winASet();
        }
        for (int i = 0; i < gameScore ; i++) {
            player.addPoint();
        }
        return player;
    }
}