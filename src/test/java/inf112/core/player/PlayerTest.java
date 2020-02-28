package inf112.core.player;

import org.junit.Before;
import org.junit.Test;
import static inf112.core.player.Direction.*;
import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Player testPlayer;

    @Before
    public void init() {
        testPlayer = new Player();
    }

    @Test
    public void rotateLeftFromNorthShouldReturnWest() {
        testPlayer.setDirection(NORTH);
        testPlayer.rotateLeft();
        assertEquals(testPlayer.getDirection(), WEST);
    }

    @Test
    public void rotateRightFromNorthShouldReturnEast() {
        testPlayer.setDirection(NORTH);
        testPlayer.rotateRight();
        assertEquals(testPlayer.getDirection(), EAST);
    }
}
