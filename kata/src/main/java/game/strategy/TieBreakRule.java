package game.strategy;

import game.GameScore;
import match.Player;

import java.util.Map;

/**
 * TieBreakRule class
 * last update : 22/08/2021
 * @author Mathieu VALENTIN
 */
public class TieBreakRule implements TennisGameStrategy{

    private static final int WIN_SCORE_DIFF = 2;
    private static final int WIN_SCORE_GAME = 7;

    /**
     * Add a point to the current player and update scoreBoard
     * @param scoreBoard match scoreboard
     * @param scorer current player
     * @param opponent other player
     */
    @Override
    public void updatePlayerScore(Map<String, Player> scoreBoard, String scorer, String opponent) {
        scoreBoard.get(scorer).addPoint();
        if(scoreBoard.get(scorer).getCurrentGamePoints() - scoreBoard.get(opponent).getCurrentGamePoints() != WIN_SCORE_DIFF
                && scoreBoard.get(scorer).getCurrentGamePoints() < WIN_SCORE_GAME) {
            scoreBoard.get(scorer).setCurrentGameScore(Integer.toString(scoreBoard.get(scorer).getCurrentGamePoints()));
        } else {
            scoreBoard.get(scorer).setCurrentGameScore(GameScore.GAME.getGameScore());
        }
    }
}
