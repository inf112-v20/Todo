package inf112.core.flag;

import inf112.core.board.GameBoard;
import inf112.core.movement.FlagHandler;
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
    private Player playerNotOnFlag, playerOnFlag1, playerOnFlag2;

    @Before
    public void init() {
        this.gameBoard = new GameBoard();
        this.flagHandler = new FlagHandler(gameBoard, 3);
        this.playerNotOnFlag = new Player(5, 5);
        this.playerOnFlag1 = new Player(3, 9);
        this.playerOnFlag2 = new Player(5, 9);
    }

    @Test
    public void playerNotOnAFlagShouldYieldFalseTest() {
        assertFalse(flagHandler.isOnCorrectFlag(playerNotOnFlag));
    }

    @Test
    public void playerVisitingFlag1AsFirstFlagShouldYieldTrueTest() {
        assertTrue(flagHandler.isOnCorrectFlag(playerOnFlag1));
    }

    @Test
    public void playerVisitingFlag1ASecondTimeShouldYieldFalseTest() {
        flagHandler.incrementFlagsVisited(playerOnFlag1);    // simulating the player visiting flag 1
        assertFalse(flagHandler.isOnCorrectFlag(playerOnFlag1));
    }

    @Test
    public void playerVisitingFlag2AsFirstFlagShouldYieldFalseTest() {
        assertFalse(flagHandler.isOnCorrectFlag(playerOnFlag2));
    }

    @Test
    public void playerVisitingFlag2AsSecondFlagShouldYieldTrueTest() {
        flagHandler.incrementFlagsVisited(playerOnFlag2);    // simulating that player has visited flag 1 already
        assertTrue(flagHandler.isOnCorrectFlag(playerOnFlag2));
    }

    @Test
    public void playerThatHasVisitedLessThan3FlagsShouldYieldFalse() {
        // simulating the visitations of two flags
        flagHandler.incrementFlagsVisited(playerNotOnFlag);
        flagHandler.incrementFlagsVisited(playerNotOnFlag);
        assertFalse(flagHandler.hasVisitedAllFlags(playerNotOnFlag));
    }

    @Test
    public void playerThatHasVisited3FlagsExactlyShouldYieldTrue() {
        flagHandler.incrementFlagsVisited(playerNotOnFlag);
        flagHandler.incrementFlagsVisited(playerNotOnFlag);
        flagHandler.incrementFlagsVisited(playerNotOnFlag);
        assertTrue(flagHandler.hasVisitedAllFlags(playerNotOnFlag));
    }
}
