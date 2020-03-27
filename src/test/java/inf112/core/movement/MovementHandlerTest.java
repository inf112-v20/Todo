package inf112.core.movement;

import com.badlogic.gdx.Input;
import inf112.core.game.MainGame;
import inf112.core.player.Player;
import inf112.core.player.Direction;
import inf112.core.testingUtils.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class MovementHandlerTest {

    private MovementHandler movementHandler;
    private Player player1;

    @Before
    public void init() {
        movementHandler = new MovementHandler(new MainGame());
        player1 = new Player("TestSpiller");
    }

    @Test
    public void addPlayerTest() {
        boolean result = movementHandler.add(player1);
        assertTrue(result);
    }

    @Test
    public void containsAddedPlayer() {
        movementHandler.add(player1);
        assertTrue(movementHandler.contains(player1));
    }

//    @Test
//    public void addedPlayerIsTheSamePlayerTest() {
//        movementHandler.add(player1);
//        Player candidate = movementHandler.getPlayerByName("TestSpiller");
//        assertEquals(player1, candidate);
//    }

    @Test
    public void setUnknownPlayerAsActiveGivesAnExceptionTest() {
        try {
            movementHandler.setActive(player1);
            fail();
        } catch (Exception e) {}
    }

    @Test
    public void keyPressMovesActivePlayerOneForwardTest() {
        player1.setDirection(Direction.NORTH);
        int oldPlayerX = player1.getX();
        int oldPlayerY = player1.getY();
        movementHandler.add(player1);
        movementHandler.setActive(player1);
        movementHandler.keyDown(Input.Keys.UP);
        assertEquals(oldPlayerX, movementHandler.getActivePlayer().getX());
        assertEquals(oldPlayerY+1, movementHandler.getActivePlayer().getY());
    }
}
