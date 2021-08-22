package game.strategy;

import game.GameScore;
import match.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DeuceRuleTest {

    private Map<String, Player> scoreBoard;
    private TennisGameStrategy rule;

    @BeforeEach
    void initEach(){
        rule = new DeuceRule();
        scoreBoard = new HashMap<>();
    }

    @Test
    void testUpdatePlayerScoreAdvantage() {
        scoreBoard.put("Alpha", createPlayerWithGameScore(3));
        scoreBoard.put("Beta", createPlayerWithGameScore(3));

        int expectedPointAlpha = 3;
        int expectedPointBeta = 4;
        String expectedScoreAlpha = GameScore.FORTY.getGameScore();
        String expectedScoreBeta = GameScore.ADVANTAGE.getGameScore();
        rule.updatePlayerScore(scoreBoard,"Beta", "Alpha");

        assertEquals(expectedPointAlpha, scoreBoard.get("Alpha").getCurrentGamePoints());
        assertEquals(expectedScoreAlpha, scoreBoard.get("Alpha").getCurrentGameScore());
        assertEquals(expectedPointBeta, scoreBoard.get("Beta").getCurrentGamePoints());
        assertEquals(expectedScoreBeta, scoreBoard.get("Beta").getCurrentGameScore());
    }

    @Test
    void testUpdatePlayerScoreDeuce() {
        scoreBoard.put("Alpha", createPlayerWithGameScore(4));
        scoreBoard.put("Beta", createPlayerWithGameScore(3));

        int expectedPointAlpha = 4;
        int expectedPointBeta = 4;
        String expectedScoreAlpha = GameScore.DEUCE.getGameScore();
        String expectedScoreBeta = GameScore.DEUCE.getGameScore();
        rule.updatePlayerScore(scoreBoard,"Beta", "Alpha");

        assertEquals(expectedPointAlpha, scoreBoard.get("Alpha").getCurrentGamePoints());
        assertEquals(expectedScoreAlpha, scoreBoard.get("Alpha").getCurrentGameScore());
        assertEquals(expectedPointBeta, scoreBoard.get("Beta").getCurrentGamePoints());
        assertEquals(expectedScoreBeta, scoreBoard.get("Beta").getCurrentGameScore());
    }

    @Test
    void testUpdatePlayerScoreWin() {
        scoreBoard.put("Alpha", createPlayerWithGameScore(4));
        scoreBoard.put("Beta", createPlayerWithGameScore(3));

        int expectedPointAlpha = 5;
        int expectedPointBeta = 3;
        String expectedScoreAlpha = GameScore.GAME.getGameScore();
        String expectedScoreBeta = GameScore.FORTY.getGameScore();
        rule.updatePlayerScore(scoreBoard,"Alpha", "Beta");

        assertEquals(expectedPointAlpha, scoreBoard.get("Alpha").getCurrentGamePoints());
        assertEquals(expectedScoreAlpha, scoreBoard.get("Alpha").getCurrentGameScore());
        assertEquals(expectedPointBeta, scoreBoard.get("Beta").getCurrentGamePoints());
        assertEquals(expectedScoreBeta, scoreBoard.get("Beta").getCurrentGameScore());
    }

    @Test
    void testScenarioRule(){
        scoreBoard.put("Alpha", createPlayerWithGameScore(3));
        scoreBoard.put("Beta", createPlayerWithGameScore(3));
        // ADV - 40
        int expectedPointAlpha = 4;
        int expectedPointBeta = 3;
        String expectedScoreAlpha = GameScore.ADVANTAGE.getGameScore();
        String expectedScoreBeta = GameScore.FORTY.getGameScore();
        rule.updatePlayerScore(scoreBoard,"Alpha", "Beta");

        assertEquals(expectedPointAlpha, scoreBoard.get("Alpha").getCurrentGamePoints());
        assertEquals(expectedScoreAlpha, scoreBoard.get("Alpha").getCurrentGameScore());
        assertEquals(expectedPointBeta, scoreBoard.get("Beta").getCurrentGamePoints());
        assertEquals(expectedScoreBeta, scoreBoard.get("Beta").getCurrentGameScore());
        // DEUCE
        expectedPointBeta = 4;
        expectedScoreAlpha = GameScore.DEUCE.getGameScore();
        expectedScoreBeta = GameScore.DEUCE.getGameScore();
        rule.updatePlayerScore(scoreBoard,"Beta", "Alpha");

        assertEquals(expectedPointAlpha, scoreBoard.get("Alpha").getCurrentGamePoints());
        assertEquals(expectedScoreAlpha, scoreBoard.get("Alpha").getCurrentGameScore());
        assertEquals(expectedPointBeta, scoreBoard.get("Beta").getCurrentGamePoints());
        assertEquals(expectedScoreBeta, scoreBoard.get("Beta").getCurrentGameScore());
        // 40 - AVD
        expectedPointBeta = 5;
        expectedScoreAlpha = GameScore.FORTY.getGameScore();
        expectedScoreBeta = GameScore.ADVANTAGE.getGameScore();
        rule.updatePlayerScore(scoreBoard,"Beta", "Alpha");

        assertEquals(expectedPointAlpha, scoreBoard.get("Alpha").getCurrentGamePoints());
        assertEquals(expectedScoreAlpha, scoreBoard.get("Alpha").getCurrentGameScore());
        assertEquals(expectedPointBeta, scoreBoard.get("Beta").getCurrentGamePoints());
        assertEquals(expectedScoreBeta, scoreBoard.get("Beta").getCurrentGameScore());
        // 40 - Win game
        expectedPointBeta = 6;
        expectedScoreAlpha = GameScore.FORTY.getGameScore();
        expectedScoreBeta = GameScore.GAME.getGameScore();
        rule.updatePlayerScore(scoreBoard,"Beta", "Alpha");

        assertEquals(expectedPointAlpha, scoreBoard.get("Alpha").getCurrentGamePoints());
        assertEquals(expectedScoreAlpha, scoreBoard.get("Alpha").getCurrentGameScore());
        assertEquals(expectedPointBeta, scoreBoard.get("Beta").getCurrentGamePoints());
        assertEquals(expectedScoreBeta, scoreBoard.get("Beta").getCurrentGameScore());

    }

    private Player createPlayerWithGameScore(int score){
        Player player = new Player();
        for (int i = 0; i < score ; i++) {
            player.addPoint();
        }
        return player;
    }
}