package match;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerTest {

    private Player player;

    @BeforeEach
    void initEach(){
        player = new Player();
    }

    @Test
    void testAddPoint() {
        int expectedScore = 1;

        player.addPoint();

        assertEquals(expectedScore, player.getCurrentGamePoints());
    }

    @Test
    void testWinASet() {
        int expectedSetScore = 1;

        player.winASet();

        assertEquals(expectedSetScore, player.getSetScore());
    }

    @Test
    void testResetGameScore() {
        int expectedScore = 0;
        int expectedSetScore = 1;

        player.addPoint();
        player.winASet();
        assertEquals(expectedScore + 1, player.getCurrentGamePoints());
        assertEquals(expectedSetScore, player.getSetScore());
        player.resetGameScore();

        assertEquals(expectedScore, player.getCurrentGamePoints());
        assertEquals(expectedSetScore, player.getSetScore());
    }
}