package match;

import game.GameScore;

/**
 * Player class
 * last update : 22/08/2021
 * @author Mathieu VALENTIN
 */
public class Player {

    private int setScore;
    private int currentGamePoints;
    private String currentGameScore;

    public Player() {
        this.setScore = 0;
        this.currentGamePoints = 0;
        this.currentGameScore = GameScore.LOVE.getGameScore();
    }

    /**
     * Increment player's game score
     */
    public void addPoint() {
        this.currentGamePoints++;
    }

    /**
     * Increment player's set score
     */
    public void winASet(){
        this.setScore++;
    }

    /**
     * reset player's game score
     */
    public void resetGameScore(){
        this.currentGamePoints = 0;
        this.currentGameScore = GameScore.LOVE.getGameScore();
    }

    public int getSetScore() {
        return setScore;
    }

    public int getCurrentGamePoints() {
        return currentGamePoints;
    }

    public String getCurrentGameScore() {
        return currentGameScore;
    }

    public void setCurrentGameScore(String gameScore) {
        this.currentGameScore = gameScore;
    }
}
