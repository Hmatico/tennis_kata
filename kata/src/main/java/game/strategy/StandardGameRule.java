package game.strategy;

import game.GameScore;
import match.Player;

import java.util.Map;

/**
 * StandardGameRule class
 * last update : 22/08/2021
 * @author Mathieu VALENTIN
 */
public class StandardGameRule implements TennisGameStrategy {

    private static final int DECISIVE_POINT_LIMIT = 3;

    /**
     * Add a point to the current player and update scoreBoard
     * @param scoreBoard match scoreboard
     * @param scorer current player
     * @param opponent other player
     */
    @Override
    public void updatePlayerScore(Map<String, Player> scoreBoard, String scorer, String opponent) {
        scoreBoard.get(scorer).addPoint();
        if(scoreBoard.get(scorer).getCurrentGamePoints() <= DECISIVE_POINT_LIMIT) {
            scoreBoard.get(scorer).setCurrentGameScore(GameScore.values()[scoreBoard.get(scorer).getCurrentGamePoints()].getGameScore());
        } else {
            scoreBoard.get(scorer).setCurrentGameScore(GameScore.GAME.getGameScore());
        }
    }
}
