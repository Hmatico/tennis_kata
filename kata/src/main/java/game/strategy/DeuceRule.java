package game.strategy;

import game.GameScore;
import match.Player;

import java.util.Map;

/**
 * DeuceRule class
 * last update : 22/08/2021
 * @author Mathieu VALENTIN
 */
public class DeuceRule implements TennisGameStrategy {

    /**
     * Add a point to the current player and update scoreBoard
     * @param scoreBoard match scoreboard
     * @param scorer current player
     * @param opponent other player
     */
    @Override
    public void updatePlayerScore(Map<String, Player> scoreBoard, String scorer, String opponent) {
        scoreBoard.get(scorer).addPoint();
        int currentPlayerDiff = scoreBoard.get(scorer).getCurrentGamePoints() - scoreBoard.get(opponent).getCurrentGamePoints();
        int otherPlayerDiff = scoreBoard.get(opponent).getCurrentGamePoints() - scoreBoard.get(scorer).getCurrentGamePoints();
        scoreBoard.put(scorer, applyDeuceRule(scoreBoard.get(scorer), currentPlayerDiff));
        scoreBoard.put(opponent, applyDeuceRule(scoreBoard.get(opponent), otherPlayerDiff));
    }

    /**
     * Apply the specification of the deuce rule to a player
     * @param player player
     * @param scoreDiff score difference between the two player of the game
     * @return player with updated game score status
     */
    private Player applyDeuceRule(Player player, int scoreDiff){
        switch (scoreDiff) {
            case 2 : player.setCurrentGameScore(GameScore.GAME.getGameScore());break;
            case 1 : player.setCurrentGameScore(GameScore.ADVANTAGE.getGameScore());break;
            case 0 : player.setCurrentGameScore(GameScore.DEUCE.getGameScore());break;
            default: player.setCurrentGameScore(GameScore.FORTY.getGameScore());
        }
        return player;
    }
}
