package inf112.core.laser;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.MapNames;
import inf112.core.game.MainGame;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.testingUtils.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class LaserPositionsTest {

    private MainGame game;
    private LaserPositions laserPositions;
    private Set<Vector2> verticalLaserPositions, horizontalLaserPositions, crossLaserPositions;
    Player hitPlayer, shootingPlayer;
    List<Player> players;
    Map<Player, Integer> hitPlayers;

    @Before
    public void init() {
        game = new MainGame(MapNames.LASER_TESTING_MAP2);

        shootingPlayer = new Player(6, 0);
        hitPlayer = new Player(6, 4);

        shootingPlayer.rotateTo(Direction.NORTH);
        hitPlayer.rotateTo(Direction.WEST);

        players = new ArrayList<>();
        players.add(shootingPlayer);
        players.add(hitPlayer);

        hitPlayers = new HashMap<>();
        hitPlayers.put(hitPlayer, 1);    // this player is hit 1 time

        verticalLaserPositions = new HashSet<>();
        verticalLaserPositions.add(new Vector2(0,0));
        verticalLaserPositions.add(new Vector2(0,1));
        verticalLaserPositions.add(new Vector2(0,2));
        verticalLaserPositions.add(new Vector2(0,3));
        verticalLaserPositions.add(new Vector2(0,4));

        verticalLaserPositions.add(new Vector2(6,1));
        verticalLaserPositions.add(new Vector2(6,2));
        verticalLaserPositions.add(new Vector2(6,4));    // player gets hit here

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
//        horizontalLaserPositions.add(new Vector2(6, 3));
//        horizontalLaserPositions.add(new Vector2(7, 3));
        horizontalLaserPositions.add(new Vector2(8, 3));
        horizontalLaserPositions.add(new Vector2(9, 3));

        horizontalLaserPositions.add(new Vector2(5,4));

        crossLaserPositions = new HashSet<>();
        crossLaserPositions.add(new Vector2(6,3));
        crossLaserPositions.add(new Vector2(7,3));

        laserPositions = new LaserPositions(game.getBoard(), players);
        laserPositions.gatherAllLaserPositions();    // fire lasers logically
    }

    @Test
    public void laserHandlerGathersCorrectAmountOfVerticalLaserPositionsTest() {
        assertEquals(verticalLaserPositions.size(), laserPositions.getAllVerticalLaserPositions().size());
    }

    @Test
    public void laserHandlerGathersTheCorrectVerticalLaserPositionsTest() {
        for (Vector2 pos : laserPositions.getAllVerticalLaserPositions())
            assertTrue(verticalLaserPositions.contains(pos));
    }

    @Test
    public void laserHandlerGathersCorrectAmountOfHorizontalLaserPositionsTest() {
        assertEquals(horizontalLaserPositions.size(), laserPositions.getAllHorizontalLaserPositions().size());
    }

    @Test
    public void laserHandlerGathersTheCorrectHorizontalLaserPositionsTest() {
        for (Vector2 pos : laserPositions.getAllHorizontalLaserPositions())
            assertTrue(horizontalLaserPositions.contains(pos));
    }

    @Test
    public void laserHandlerGathersCorrectAmountOfCrossedLaserPositionsTest() {
        assertEquals(crossLaserPositions.size(), laserPositions.getAllCrossedLaserPositions().size());
    }

    @Test
    public void laserHandlerGathersTheCorrectCrossedLaserPositionsTest() {
        for (Vector2 pos : laserPositions.getAllCrossedLaserPositions())
            assertTrue(crossLaserPositions.contains(pos));
    }

    @Test
    public void numberOfHitPlayersIsCorrect() {
        assertEquals(hitPlayers.size(), laserPositions.getHitPlayersMap().size());
    }

    @Test
    public void hitPlayersIsTheCorrectPlayers() {
        for (Player player : hitPlayers.keySet())
            assertTrue(laserPositions.getHitPlayersMap().containsKey(player));
    }

    @Test
    public void hitPlayersHasRegisteredTheCorrectAmountOfDamage() {
        for (Player player : hitPlayers.keySet())
            assertEquals(hitPlayers.get(player), laserPositions.getHitPlayersMap().get(player));
    }

}
