package inf112.core.map;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.MapNames;
import inf112.core.game.MainGame;
import inf112.core.game.RoundHandler;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import inf112.core.testingUtils.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class WrenchTileTest {
    MainGame game;
    MovementHandler movementHandler;
    RoundHandler roundHandler;
    Player testPlayer;

    static final Vector2 SINLE_WRENCH = new Vector2(4,4);
    static final Vector2 DOUBLE_WRENCH = new Vector2(3,4);

    @Before
    public void setup() {
        game = new MainGame(MapNames.WRENCH_AND_GEARS_TESTING_MAP);
        movementHandler = game.getMovementHandler();
        roundHandler = game.getRoundHandler();
        testPlayer = new Player("testPlayer");
        movementHandler.add(testPlayer);
        movementHandler.setActive(testPlayer);
    }

    @Test
    public void SingleWrenchShouldRemoveOneDamageToken(){
        movementHandler.moveToPos(testPlayer, SINLE_WRENCH);
        testPlayer.addDamageTokens(2);
        movementHandler.wrenchesRepair();
        assertEquals(1, testPlayer.getDamageTokens());
    }

    @Test
    public void DoubleWrenchShouldRemoveOneDamageToken(){
        movementHandler.moveToPos(testPlayer, DOUBLE_WRENCH);
        testPlayer.addDamageTokens(2);
        movementHandler.wrenchesRepair();
        assertEquals(1, testPlayer.getDamageTokens());
    }
}
