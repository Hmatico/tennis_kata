package game.strategy;

import match.Player;

import java.util.Map;

/**
 * TennisGameStrategy interface
 * last update : 22/08/2021
 * @author Mathieu VALENTIN
 */
public interface TennisGameStrategy {

    /**
     * Add a point to the current player and update scoreBoard
     * @param scoreBoard match scoreboard
     * @param scorer current player
     * @param opponent other player
     */
    void updatePlayerScore(Map<String, Player> scoreBoard, String scorer, String opponent);

}
