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
    public void rotateLeftFromWestShouldReturnSouth() {
        testPlayer.setDirection(WEST);
        testPlayer.rotateLeft();
        assertEquals(testPlayer.getDirection(), SOUTH);
    }

    @Test
    public void rotateLeftFromSouthShouldReturnEast() {
        testPlayer.setDirection(SOUTH);
        testPlayer.rotateLeft();
        assertEquals(testPlayer.getDirection(), EAST);
    }

    @Test
    public void rotateLeftFromEastShouldReturnNorth() {
        testPlayer.setDirection(EAST);
        testPlayer.rotateLeft();
        assertEquals(testPlayer.getDirection(), NORTH);
    }

    @Test
    public void rotateRightFromNorthShouldReturnEast() {
        testPlayer.setDirection(NORTH);
        testPlayer.rotateRight();
        assertEquals(testPlayer.getDirection(), EAST);
    }

    @Test
    public void rotateRightFromEastShouldReturnSouth() {
        testPlayer.setDirection(EAST);
        testPlayer.rotateRight();
        assertEquals(testPlayer.getDirection(), SOUTH);
    }

    @Test
    public void rotateRightFromSouthShouldReturnWest() {
        testPlayer.setDirection(SOUTH);
        testPlayer.rotateRight();
        assertEquals(testPlayer.getDirection(), WEST);
    }

    @Test
    public void rotateRightFromWestShouldReturnNorth() {
        testPlayer.setDirection(WEST);
        testPlayer.rotateRight();
        assertEquals(testPlayer.getDirection(), NORTH);
    }
}
