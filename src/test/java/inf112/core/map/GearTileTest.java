package inf112.core.map;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.MapNames;
import inf112.core.game.MainGame;
import inf112.core.game.RoundHandler;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.testingUtils.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class GearTileTest {
    MainGame game;
    MovementHandler movementHandler;
    RoundHandler roundHandler;
    Player testPlayer;

    static final Vector2 RIGHT_GEAR = new Vector2(4,4);
    static final Vector2 LEFT_GEAR = new Vector2(3,4);

    @Before
    public void setup() {
        game = new MainGame(MapNames.GEAR_TEST_MAP);
        movementHandler = game.getMovementHandler();
        roundHandler = game.getRoundHandler();
        testPlayer = new Player("testPlayer");
        movementHandler.add(testPlayer);
        movementHandler.setActive(testPlayer);
    }

    @Test
    public void RightGearShouldRotateplayerRight(){
        movementHandler.moveToPos(testPlayer, RIGHT_GEAR);
        testPlayer.rotateTo(Direction.NORTH);
        roundHandler.gearsRotate();
        assertEquals(Direction.EAST, testPlayer.getDirection());
    }

    @Test
    public void  LeftGearShouldRotateplayerRight(){
        movementHandler.moveToPos(testPlayer, LEFT_GEAR);
        testPlayer.rotateTo(Direction.NORTH);
        roundHandler.gearsRotate();
        assertEquals(Direction.WEST, testPlayer.getDirection());
    }
}
