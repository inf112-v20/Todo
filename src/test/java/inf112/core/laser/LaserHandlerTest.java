package inf112.core.laser;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.MapNames;
import inf112.core.game.MainGame;
import inf112.core.player.Player;
import inf112.core.testingUtils.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class LaserHandlerTest {

    MainGame game;
    LaserHandler laserHandler;
    List<Vector2> verticalLaserPositions, horizontalLaserPositions, crossLaserPositions;

    @Before
    public void init() {
        game = new MainGame(MapNames.LASER_TESTING_MAP2);
        laserHandler = new LaserHandler(game.getBoard(), new ArrayList<Player>());    // temporary

        verticalLaserPositions = new ArrayList<>();
        verticalLaserPositions.add(new Vector2(0,0));
        verticalLaserPositions.add(new Vector2(0,1));
        verticalLaserPositions.add(new Vector2(0,2));
        verticalLaserPositions.add(new Vector2(0,3));
        verticalLaserPositions.add(new Vector2(0,4));
    }

    @Test
    public void laserHandlerGathersCorrectAmountOfVerticalLaserPositionsTest() {
        assertEquals(laserHandler.gatherAllVerticalLaserPositions().size(), verticalLaserPositions.size());
    }

}
