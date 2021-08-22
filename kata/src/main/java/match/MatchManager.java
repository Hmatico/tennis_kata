package match;

import game.GameScore;
import game.TennisGame;

import java.util.HashMap;
import java.util.Map;

/**
 * MatchManager class
 * last update : 22/08/2021
 * @author Mathieu VALENTIN
 */
public class MatchManager {

    private static final int MAX_SET_SCORE = 6;
    private static final int CEIL_SET_SCORE = 4;
    private static final String SCORE_FORMAT = "Player %s : score set = %s | game score = %s\n";

    private TennisGame currentGame;
    private Map<String, Player> scoreBoard;

    public MatchManager(String playerA, String playerB){
        scoreBoard = new HashMap<>();
        scoreBoard.put(playerA, new Player());
        scoreBoard.put(playerB, new Player());
        currentGame = new TennisGame(scoreBoard);
    }

    /**
     * Add point to the scorer and update the match scoreboard
     * @param scorer current player
     * @param opponent other player
     */
    public void scorePoint(String scorer, String opponent){
        if(!hasWinMatch(scorer, opponent) || !hasWinMatch(opponent, scorer)){
            currentGame.addAPoint(scorer, opponent);
            if(isGameWinner(scorer)){
                scoreBoard.get(scorer).winASet();
                resetScoreBoard(scorer, opponent);
                currentGame = new TennisGame(scoreBoard);
            }
        }
    }

    /**
     * Check if the current player is the winner of the current game
     * @param scorer current player
     * @return true if the current player is the winner of the current game, else false
     */
    private boolean isGameWinner(String scorer) {
        return GameScore.GAME.getGameScore().equals(scoreBoard.get(scorer).getCurrentGameScore());
    }

    /**
     * Reset players' game score
     * @param scorer current player
     * @param opponent other player
     */
    private void resetScoreBoard(String scorer, String opponent) {
        scoreBoard.get(scorer).resetGameScore();
        scoreBoard.get(opponent).resetGameScore();
    }

    /**
     * Return true if the player has win the match
     * @param player first player
     * @param opponent second player
     * @return true if the first player has with the match, else false
     */
    private boolean hasWinMatch(String player, String opponent){
        return scoreBoard.get(player).getSetScore() > MAX_SET_SCORE
                || (scoreBoard.get(player).getSetScore() == MAX_SET_SCORE
                        && scoreBoard.get(opponent).getSetScore() <= CEIL_SET_SCORE);
    }

    /**
     * Get the match score in string
     * @return match score to string
     */
    public String getScore(){
        StringBuilder score = new StringBuilder();
        for (String player : scoreBoard.keySet()){
            score.append(getScorePlayer(player));
        }
        return score.toString();
    }

    /**
     * Get the player score
     * @param player player
     * @return the player score
     */
    public String getScorePlayer(String player){
        if( scoreBoard.containsKey(player)){
            return String.format(SCORE_FORMAT, player, scoreBoard.get(player).getSetScore(), scoreBoard.get(player).getCurrentGameScore());
        } else return null;
    }

    /**
     * Get the match winner name
     * @param player first player
     * @param opponent second player
     * @return the name of the winner or null
     */
    public String getMatchWinner(String player, String opponent) {
        if(hasWinMatch(player, opponent)) {
            return player;
        } else if(hasWinMatch(opponent, player)) {
            return opponent;
        } else return null;
    }
}
