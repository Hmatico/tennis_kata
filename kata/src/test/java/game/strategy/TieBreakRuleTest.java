package game.strategy;

import game.GameScore;
import match.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TieBreakRuleTest {

    private Map<String, Player> scoreBoard;
    private TennisGameStrategy rule;

    @BeforeEach
    void initEach(){
        rule = new TieBreakRule();
        scoreBoard = new HashMap<>();
    }

    @Test
    void testUpdatePlayerScoreNoWinner() {
        scoreBoard.put("Alpha", createPlayerWithGameScore(6));
        scoreBoard.put("Beta", createPlayerWithGameScore(5));
        int expectedPoint = 6;
        String expectedScore = "6";
        rule.updatePlayerScore(scoreBoard,"Beta", "Alpha");

        assertEquals(expectedPoint, scoreBoard.get("Beta").getCurrentGamePoints());
        assertEquals(expectedScore, scoreBoard.get("Beta").getCurrentGameScore());
        assertEquals(6, scoreBoard.get("Alpha").getCurrentGamePoints());
    }

    @Test
    void testUpdatePlayerScoreWithWinner() {
        scoreBoard.put("Alpha", createPlayerWithGameScore(10));
        scoreBoard.put("Beta", createPlayerWithGameScore(11));
        int expectedPoint = 12;
        String expectedScore = GameScore.GAME.getGameScore();
        rule.updatePlayerScore(scoreBoard,"Beta", "Alpha");

        assertEquals(expectedPoint, scoreBoard.get("Beta").getCurrentGamePoints());
        assertEquals(expectedScore, scoreBoard.get("Beta").getCurrentGameScore());
        assertEquals(10, scoreBoard.get("Alpha").getCurrentGamePoints());
    }

    private Player createPlayerWithGameScore(int score){
        Player player = new Player();
        for (int i = 0; i < score ; i++) {
            player.addPoint();
        }
        return player;
    }
}