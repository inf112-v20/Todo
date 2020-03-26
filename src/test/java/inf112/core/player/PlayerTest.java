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
        testPlayer.rotateTo(NORTH);
        testPlayer.rotateLeft();
        assertEquals(testPlayer.getDirection(), WEST);
    }

    @Test
    public void rotateLeftFromWestShouldReturnSouth() {
        testPlayer.rotateTo(WEST);
        testPlayer.rotateLeft();
        assertEquals(testPlayer.getDirection(), SOUTH);
    }

    @Test
    public void rotateLeftFromSouthShouldReturnEast() {
        testPlayer.rotateTo(SOUTH);
        testPlayer.rotateLeft();
        assertEquals(testPlayer.getDirection(), EAST);
    }

    @Test
    public void rotateLeftFromEastShouldReturnNorth() {
        testPlayer.rotateTo(EAST);
        testPlayer.rotateLeft();
        assertEquals(testPlayer.getDirection(), NORTH);
    }

    @Test
    public void rotateRightFromNorthShouldReturnEast() {
        testPlayer.rotateTo(NORTH);
        testPlayer.rotateRight();
        assertEquals(testPlayer.getDirection(), EAST);
    }

    @Test
    public void rotateRightFromEastShouldReturnSouth() {
        testPlayer.rotateTo(EAST);
        testPlayer.rotateRight();
        assertEquals(testPlayer.getDirection(), SOUTH);
    }

    @Test
    public void rotateRightFromSouthShouldReturnWest() {
        testPlayer.rotateTo(SOUTH);
        testPlayer.rotateRight();
        assertEquals(testPlayer.getDirection(), WEST);
    }

    @Test
    public void rotateRightFromWestShouldReturnNorth() {
        testPlayer.rotateTo(WEST);
        testPlayer.rotateRight();
        assertEquals(testPlayer.getDirection(), NORTH);
    }
}
