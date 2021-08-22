package game.strategy;

import game.GameScore;
import match.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StandardGameRuleTest {

    private Map<String, Player> scoreBoard;
    private TennisGameStrategy rule;

    @BeforeEach
    void initEach(){
        rule = new StandardGameRule();
        scoreBoard = new HashMap<>();
    }

    @Test
    void testUpdatePlayerScoreNoGameWinner() {
        scoreBoard.put("Alpha", createPlayerWithGameScore(3));
        scoreBoard.put("Beta", createPlayerWithGameScore(2));
        int expectedPoint = 3;
        String expectedScore = GameScore.FORTY.getGameScore();
        rule.updatePlayerScore(scoreBoard,"Beta", "Alpha");

        assertEquals(expectedPoint, scoreBoard.get("Beta").getCurrentGamePoints());
        assertEquals(expectedScore, scoreBoard.get("Beta").getCurrentGameScore());
        assertEquals(3, scoreBoard.get("Alpha").getCurrentGamePoints());
    }

    @Test
    void testUpdatePlayerScoreWithGameWinner() {
        scoreBoard.put("Alpha", createPlayerWithGameScore(3));
        scoreBoard.put("Beta", createPlayerWithGameScore(2));
        int expectedPoint = 4;
        String expectedScore = GameScore.GAME.getGameScore();
        rule.updatePlayerScore(scoreBoard,"Alpha", "Beta");

        assertEquals(expectedPoint, scoreBoard.get("Alpha").getCurrentGamePoints());
        assertEquals(expectedScore, scoreBoard.get("Alpha").getCurrentGameScore());
        assertEquals(2, scoreBoard.get("Beta").getCurrentGamePoints());
    }

    @Test
    void testScenarioRule(){
        scoreBoard.put("Alpha", createPlayerWithGameScore(0));
        scoreBoard.put("Beta", createPlayerWithGameScore(0));
        for (int i = 0; i < 3 ; i++) {
            int expectedPoint = i+1;
            String expectedScore = GameScore.values()[i+1].getGameScore();
            rule.updatePlayerScore(scoreBoard,"Alpha", "Beta");

            assertEquals(expectedPoint, scoreBoard.get("Alpha").getCurrentGamePoints());
            assertEquals(expectedScore, scoreBoard.get("Alpha").getCurrentGameScore());
            assertEquals(0, scoreBoard.get("Beta").getCurrentGamePoints());
        }
        int expectedPoint = 4;
        String expectedScore = GameScore.GAME.getGameScore();
        rule.updatePlayerScore(scoreBoard,"Alpha", "Beta");

        assertEquals(expectedPoint, scoreBoard.get("Alpha").getCurrentGamePoints());
        assertEquals(expectedScore, scoreBoard.get("Alpha").getCurrentGameScore());
        assertEquals(0, scoreBoard.get("Beta").getCurrentGamePoints());
    }

    private Player createPlayerWithGameScore(int score){
        Player player = new Player();
        for (int i = 0; i < score ; i++) {
            player.addPoint();
        }
        return player;
    }
}