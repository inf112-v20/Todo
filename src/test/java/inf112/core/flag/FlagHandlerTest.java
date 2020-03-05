package inf112.core.flag;

import inf112.core.board.GameBoard;
import inf112.core.movement.FlagHandler;
import inf112.core.movement.SpawnHandler;
import inf112.core.player.Player;
import inf112.core.testingUtils.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class FlagHandlerTest {

    /**
     * Info about flag positions (given that GameBoard is instantiated with MapNames.TESTING_MAP)
     *
     * flag 1: (3, 9)
     * flag 2: (5, 9)
     * flag 3: (5, 11)
     * flag 4: (3, 11)
     */

    private GameBoard gameBoard;
    private FlagHandler flagHandler;
    private SpawnHandler spawnHandler;    // temporary
    private Player player;

    @Before
    public void init() {
        this.gameBoard = new GameBoard();
        this.flagHandler = new FlagHandler(gameBoard);
        this.spawnHandler = new SpawnHandler(gameBoard);
    }

    @Test
    public void playerNotOnAFlagShouldYieldFalseTest() {
        this.player = new Player(5, 5);    // should not be a flag position
        assertFalse(flagHandler.isOnFlag(player));
    }

    @Test
    public void playerOnAFlagShouldYieldTrueTest() {
        this.player = new Player(3, 9);    // should be a flag position
        assertTrue(flagHandler.isOnFlag(player));
    }
}
