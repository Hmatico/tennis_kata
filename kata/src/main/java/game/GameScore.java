package game;

/**
 * gameScore enum
 * last update : 22/08/2021
 * @author Mathieu VALENTIN
 */
public enum GameScore {
    LOVE("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("ADV"),
    DEUCE("DEUCE"),
    GAME("Win Game");

    private String gameScore;

    GameScore(String gameScore) {
        this.gameScore = gameScore;
    }

    public String getGameScore() {
        return gameScore;
    }
}
