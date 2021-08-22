package game;

import match.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TennisGameTest {

    private TennisGame tennisGame;
    private Map<String, Player> scoreBoard;
    private Method isDeuceRuleMethod;
    private Method isTieBreakRuleMethod;

    @BeforeEach
    void initEach() throws NoSuchMethodException {
        scoreBoard = new HashMap<>();
        tennisGame = new TennisGame(scoreBoard);

        isDeuceRuleMethod = tennisGame.getClass().getDeclaredMethod("isDeuce", String.class, String.class);
        isDeuceRuleMethod.setAccessible(true);
        isTieBreakRuleMethod = tennisGame.getClass().getDeclaredMethod("isTieBreak", String.class, String.class);
        isTieBreakRuleMethod.setAccessible(true);
    }

    @Test
    void addAPointStandard() {
        scoreBoard.put("Alpha", createPlayerWithGameScore(3));
        scoreBoard.put("Beta", createPlayerWithGameScore(2));

        int expectedPointAlpha = 3;
        int expectedPointBeta = 3;
        tennisGame.addAPoint("Beta", "Alpha");

        assertEquals(expectedPointAlpha, scoreBoard.get("Alpha").getCurrentGamePoints());
        assertEquals(expectedPointBeta, scoreBoard.get("Beta").getCurrentGamePoints());
    }

    @Test
    void addAPointDeuce() {
        scoreBoard.put("Alpha", createPlayerWithGameScore(3));
        scoreBoard.put("Beta", createPlayerWithGameScore(3));

        int expectedPointAlpha = 4;
        int expectedPointBeta = 3;
        tennisGame.addAPoint("Alpha", "Beta");

        assertEquals(expectedPointAlpha, scoreBoard.get("Alpha").getCurrentGamePoints());
        assertEquals(expectedPointBeta, scoreBoard.get("Beta").getCurrentGamePoints());
    }

    @Test
    void addAPointTieBreak() {
        scoreBoard.put("Alpha", createPlayerWithSetAndGameScore(6,10));
        scoreBoard.put("Beta", createPlayerWithSetAndGameScore(6,11));

        int expectedPointAlpha = 11;
        int expectedPointBeta = 11;
        tennisGame.addAPoint("Alpha", "Beta");

        assertEquals(expectedPointAlpha, scoreBoard.get("Alpha").getCurrentGamePoints());
        assertEquals(expectedPointBeta, scoreBoard.get("Beta").getCurrentGamePoints());

    }

    @Test
    void testCheckCurrentRuleStandard() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        scoreBoard.put("Alpha", createPlayerWithGameScore(3));
        scoreBoard.put("Beta", createPlayerWithGameScore(0));

        assertFalse((boolean) isDeuceRuleMethod.invoke(tennisGame, "Alpha", "Beta"));
        assertFalse((boolean) isTieBreakRuleMethod.invoke(tennisGame, "Alpha", "Beta"));
    }

    @Test
    void testCheckCurrentRuleDeuce() throws InvocationTargetException, IllegalAccessException {
        scoreBoard.put("Alpha", createPlayerWithGameScore(4));
        scoreBoard.put("Beta", createPlayerWithGameScore(4));

        assertTrue((boolean) isDeuceRuleMethod.invoke(tennisGame, "Alpha", "Beta"));
        assertFalse((boolean) isTieBreakRuleMethod.invoke(tennisGame, "Alpha", "Beta"));
    }

    @Test
    void testCheckCurrentRuleTieBreak() throws InvocationTargetException, IllegalAccessException {
        scoreBoard.put("Alpha", createPlayerWithSetAndGameScore(6,0));
        scoreBoard.put("Beta", createPlayerWithSetAndGameScore(6,0));

        assertFalse((boolean) isDeuceRuleMethod.invoke(tennisGame, "Alpha", "Beta"));
        assertTrue((boolean) isTieBreakRuleMethod.invoke(tennisGame, "Alpha", "Beta"));
    }

    private Player createPlayerWithGameScore(int score){
        Player player = new Player();
        for (int i = 0; i < score ; i++) {
            player.addPoint();
        }
        return player;
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