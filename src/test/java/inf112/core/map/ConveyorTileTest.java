package inf112.core.map;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.MapNames;
import inf112.core.game.MainGame;
import inf112.core.game.RoundHandler;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.testingUtils.GdxTestRunner;
import inf112.core.util.VectorMovement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static inf112.core.player.Direction.*;

@RunWith(GdxTestRunner.class)
public class ConveyorTileTest {

    MainGame game;
    MovementHandler movementHandler;
    RoundHandler roundHandler;
    Player testPlayer;

    static final Vector2 WEST_CONVEYOR_TEST_POS = new Vector2(6, 4);
    static final Vector2 EAST_CONVEYOR_TEST_POS = new Vector2(5, 5);
    static final Vector2 NORTH_CONVEYOR_TEST_POS = new Vector2(5, 4);
    static final Vector2 SOUTH_CONVEYOR_TEST_POS = new Vector2(6, 5);

    static final Vector2 NORTH_TO_EAST_TEST_POS = new Vector2(6, 7);
    static final Vector2 EAST_TO_SOUTH_TEST_POS = new Vector2(6, 8);
    static final Vector2 SOUTH_TO_WEST_TEST_POS = new Vector2(7, 8);
    static final Vector2 WEST_TO_NORTH_TEST_POS = new Vector2(7, 7);

    static final Vector2 NORTH_TO_WEST_TEST_POS = new Vector2(5, 9);
    static final Vector2 WEST_TO_SOUTH_TEST_POS = new Vector2(5, 8);
    static final Vector2 SOUTH_TO_EAST_TEST_POS = new Vector2(4, 8);
    static final Vector2 EAST_TO_NORTH_TEST_POS = new Vector2(4, 7);

    @Before
    public void setup() {
        game = new MainGame(MapNames.CONVEYOR_TESTING_MAP);
        movementHandler = game.getMovementHandler();
        roundHandler = game.getRoundHandler();
        testPlayer = new Player("testPlayer");
        movementHandler.add(testPlayer);
        movementHandler.setActive(testPlayer);
    }

    @Test
    public void NorthConveyorShouldMovePlayerOneTileNorth() {
        movementHandler.moveToPos(testPlayer, NORTH_CONVEYOR_TEST_POS);
        roundHandler.conveyorMove();
        assertEquals(testPlayer.getPositionCopy(), VectorMovement.generateNew(NORTH_CONVEYOR_TEST_POS, NORTH));
    }

    @Test
    public void EastConveyorShouldMovePlayerOneTileEast() {
        movementHandler.moveToPos(testPlayer, EAST_CONVEYOR_TEST_POS);
        roundHandler.conveyorMove();
        assertEquals(testPlayer.getPositionCopy(), VectorMovement.generateNew(EAST_CONVEYOR_TEST_POS, EAST));
    }

    @Test
    public void SouthConveyorShouldMovePlayerOneTileSouth() {
        movementHandler.moveToPos(testPlayer, SOUTH_CONVEYOR_TEST_POS);
        roundHandler.conveyorMove();
        assertEquals(testPlayer.getPositionCopy(), VectorMovement.generateNew(SOUTH_CONVEYOR_TEST_POS, SOUTH));
    }

    @Test
    public void WestConveyorShouldMovePlayerOneTileWest() {
        movementHandler.moveToPos(testPlayer, WEST_CONVEYOR_TEST_POS);
        Vector2 expectedPos = testPlayer.getPositionCopy();
        VectorMovement.go(expectedPos, WEST);
        roundHandler.conveyorMove();
        assertEquals(testPlayer.getPositionCopy(), expectedPos);
    }

    @Test
    public void NorthToEastConveyorShouldMovePlayerOneTile() {
        movementHandler.moveToPos(testPlayer, NORTH_TO_EAST_TEST_POS);
        Direction prevRotation = testPlayer.getDirection();
        roundHandler.conveyorMove();
        assertEquals(testPlayer.getPositionCopy(), VectorMovement.generateNew(NORTH_TO_EAST_TEST_POS, EAST));
        assertEquals(testPlayer.getDirection(), prevRotation.rotateLeft());
    }
}
