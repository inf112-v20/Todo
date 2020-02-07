package inf112.skeleton.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.badlogic.gdx.Input;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for TestGame
 */
public class TestGameTest
{
    TestGame game;

    @Before
    public void setUp() {
        game = new TestGame();
    }

    @Test
    public void PlayerDoesNotMoveIfMovementKeysAreNotPressed() {
        float x = game.playerPosition.x;
        float y = game.playerPosition.y;
        game.keyDown(Input.Keys.SPACE);
        assertEquals(x, game.playerPosition.x, 0.0001);
        assertEquals(y, game.playerPosition.y, 0.0001);

    }

    @Test
    public void PlayerMovesRightOnRightKeyPressed() {

    }
}