package inf112.core.playercards;

import inf112.core.game.MainGame;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.cards.MovementCard;
import inf112.core.cards.RotationCard;
import inf112.core.testingUtils.GdxTestRunner;
import inf112.core.tile.Rotation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(GdxTestRunner.class)
public class CardMovementTest {

    MainGame game;
    MovementHandler movementHandler;
    Player testPlayer;
    int oldPlayerX;
    int oldPlayerY;
    Direction oldPlayerDir;

    @Before
    public void init() {
        game = new MainGame();
        movementHandler = new MovementHandler(game);
        testPlayer = new Player("TestPlayer");

        testPlayer.rotateTo(Direction.NORTH);
        oldPlayerX = testPlayer.getX();
        oldPlayerY = testPlayer.getY();
        oldPlayerDir = testPlayer.getDirection();
        movementHandler.add(testPlayer);
        movementHandler.setActive(testPlayer);
    }

    @Test
    public void forward1CardMovesPlayerForward1() {

        testPlayer.addToProgramSheet(new MovementCard(1,1,true,"",null));
        movementHandler.cardMovement(testPlayer,0);

        assertEquals(oldPlayerX, movementHandler.getActivePlayer().getX());
        assertEquals(oldPlayerY+1, movementHandler.getActivePlayer().getY());
    }

    @Test
    public void forward2CardMovesPlayerForward2() {

        testPlayer.addToProgramSheet(new MovementCard(1,2,true,"",null));
        movementHandler.cardMovement(testPlayer,0);

        assertEquals(oldPlayerX, movementHandler.getActivePlayer().getX());
        assertEquals(oldPlayerY+2, movementHandler.getActivePlayer().getY());
    }

    @Test
    public void forward3CardMovesPlayerForward3() {

        testPlayer.addToProgramSheet(new MovementCard(1,3,true,"",null));
        movementHandler.cardMovement(testPlayer,0);

        assertEquals(oldPlayerX, movementHandler.getActivePlayer().getX());
        assertEquals(oldPlayerY+3, movementHandler.getActivePlayer().getY());
    }

    @Test
    public void backwardsCardMovesPlayerBackward() {
        testPlayer.rotateTo(Direction.SOUTH); // Must be facing south as the player cannot move out of the board
        testPlayer.addToProgramSheet(new MovementCard(1,1,false,"",null));
        movementHandler.cardMovement(testPlayer,0);

        assertEquals(oldPlayerX, movementHandler.getActivePlayer().getX());
        assertEquals(oldPlayerY+1, movementHandler.getActivePlayer().getY());
    }

    @Test
    public void rotateLeftCardRotatesLeft(){
        testPlayer.addToProgramSheet(new RotationCard(0, Rotation.LEFT,1,"",null));
        movementHandler.cardMovement(testPlayer,0);

        assertEquals(oldPlayerDir.rotateLeft(), movementHandler.getActivePlayer().getDirection());
    }

    @Test
    public void rotateRightCardRotatesRight(){
        testPlayer.addToProgramSheet(new RotationCard(0,Rotation.RIGHT,1,"",null));
        movementHandler.cardMovement(testPlayer,0);

        assertEquals(oldPlayerDir.rotateRight(), movementHandler.getActivePlayer().getDirection());
    }

}
