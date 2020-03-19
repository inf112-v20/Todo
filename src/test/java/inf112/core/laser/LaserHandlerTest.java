package inf112.core.laser;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.MapNames;
import inf112.core.game.MainGame;
import inf112.core.player.Player;
import inf112.core.testingUtils.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class LaserHandlerTest {

    private MainGame game;
    private LaserHandler laserHandler;
    private Set<Vector2> verticalLaserPositions, horizontalLaserPositions, crossLaserPositions;

    @Before
    public void init() {
        game = new MainGame(MapNames.LASER_TESTING_MAP2);
        laserHandler = new LaserHandler(game.getBoard(), new ArrayList<Player>());    // temporary

        verticalLaserPositions = new HashSet<>();
        verticalLaserPositions.add(new Vector2(0,0));
        verticalLaserPositions.add(new Vector2(0,1));
        verticalLaserPositions.add(new Vector2(0,2));
        verticalLaserPositions.add(new Vector2(0,3));
        verticalLaserPositions.add(new Vector2(0,4));

        verticalLaserPositions.add(new Vector2(7,0));
        verticalLaserPositions.add(new Vector2(7,1));
        verticalLaserPositions.add(new Vector2(7,2));
//        verticalLaserPositions.add(new Vector2(7,3));
        verticalLaserPositions.add(new Vector2(7,4));

        horizontalLaserPositions = new HashSet<>();
        horizontalLaserPositions.add(new Vector2(1, 3));
        horizontalLaserPositions.add(new Vector2(2, 3));
        horizontalLaserPositions.add(new Vector2(3, 3));
        horizontalLaserPositions.add(new Vector2(4, 3));
        horizontalLaserPositions.add(new Vector2(5, 3));
        horizontalLaserPositions.add(new Vector2(6, 3));
//        horizontalLaserPositions.add(new Vector2(7, 3));
        horizontalLaserPositions.add(new Vector2(8, 3));
        horizontalLaserPositions.add(new Vector2(9, 3));

        crossLaserPositions = new HashSet<>();
        crossLaserPositions.add(new Vector2(7,3));

        laserHandler.gatherAllLaserPositions();
    }

    @Test
    public void laserHandlerGathersCorrectAmountOfVerticalLaserPositionsTest() {
        assertEquals(verticalLaserPositions.size(), laserHandler.getAllVerticalLaserPositions().size());
    }

    @Test
    public void laserHandlerGathersTheCorrectVerticalLaserPositionsTest() {
        for (Vector2 pos : laserHandler.getAllVerticalLaserPositions())
            assertTrue(verticalLaserPositions.contains(pos));
    }

    @Test
    public void laserHandlerGathersCorrectAmountOfHorizontalLaserPositionsTest() {
        assertEquals(horizontalLaserPositions.size(), laserHandler.getAllHorizontalLaserPositions().size());
    }

    @Test
    public void laserHandlerGathersTheCorrectHorizontalLaserPositionsTest() {
        for (Vector2 pos : laserHandler.getAllHorizontalLaserPositions())
            assertTrue(horizontalLaserPositions.contains(pos));
    }

    @Test
    public void laserHandlerGathersCorrectAmountOfCrossedLaserPositionsTest() {
        assertEquals(crossLaserPositions.size(), laserHandler.getAllCrossedLaserPositions().size());
    }

    @Test
    public void laserHandlerGathersTheCorrectCrossedLaserPositionsTest() {
        for (Vector2 pos : laserHandler.getAllCrossedLaserPositions())
            assertTrue(crossLaserPositions.contains(pos));
    }

}
