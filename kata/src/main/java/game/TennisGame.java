package game;

import game.strategy.DeuceRule;
import game.strategy.StandardGameRule;
import game.strategy.TennisGameStrategy;
import game.strategy.TieBreakRule;
import match.Player;

import java.util.Map;

/**
 * TennisGame class
 * last update : 22/08/2021
 * @author Mathieu VALENTIN
 */
public class TennisGame {

    private static final int DEUCE_LIMIT_POINT = 3;
    private static final int TIE_BREAK_LIMIT_SET_POINT = 6;

    private Map<String, Player> scoreBoard;
    private TennisGameStrategy gameStrategy;

    public TennisGame(Map<String, Player> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    /**
     * Add a point to the current player
     * @param scorer current player
     * @param opponent other player
     */
    public void addAPoint(String scorer, String opponent){
        if (scoreBoard.containsKey(scorer) && scoreBoard.containsKey(opponent)) {
            checkCurrentRule(scorer, opponent);
            gameStrategy.updatePlayerScore(scoreBoard, scorer, opponent);
        }
    }

    /**
     * Check scoring rule to apply
     * @param scorer current player
     * @param opponent other player
     */
    private void checkCurrentRule(String scorer, String opponent) {
        if (isTieBreak(scorer,opponent)) {
            this.gameStrategy = new TieBreakRule();
        } else if (isDeuce(scorer, opponent)) {
            this.gameStrategy = new DeuceRule();
        } else this.gameStrategy = new StandardGameRule();
    }

    /**
     * Verify if the tie-break rule is applicable
     * @param scorer current player
     * @param opponent other player
     * @return true if the tie-break rule is applicable, else false
     */
    private boolean isTieBreak(String scorer, String opponent) {
        return scoreBoard.get(scorer).getSetScore() >= TIE_BREAK_LIMIT_SET_POINT
                && scoreBoard.get(scorer).getCurrentGamePoints() >= 0
                && scoreBoard.get(opponent).getSetScore() >= TIE_BREAK_LIMIT_SET_POINT
                && scoreBoard.get(opponent).getCurrentGamePoints() >= 0;
    }

    /**
     * Verify if the deuce rule is applicable
     * @param scorer current player
     * @param opponent other player
     * @return true if the deuce rule is applicable, else false
     */
    private boolean isDeuce(String scorer, String opponent) {
        return Math.abs(scoreBoard.get(scorer).getCurrentGamePoints() - scoreBoard.get(opponent).getCurrentGamePoints()) <= 1
                && scoreBoard.get(scorer).getCurrentGamePoints() >= DEUCE_LIMIT_POINT
                && scoreBoard.get(opponent).getCurrentGamePoints() >= DEUCE_LIMIT_POINT;
    }
}
