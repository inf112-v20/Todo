package inf112.core.movement;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.MapNames;
import inf112.core.game.MainGame;
import inf112.core.movement.util.SpawnHandler;
import inf112.core.player.Player;
import inf112.core.testingUtils.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class SpawnHandlerTest {

    private MainGame game;
    private Player player1, player2;
    private List<Player> players;
    private Vector2 flagPos, cornerPos;
    private List<Vector2> availableAdjPosFromCentre, availableAdjPosFromCorner;

    private SpawnHandler spawnHandler;

    @Before
    public void init() {

        /*
         * Initial situation is like this:
         *
         * player 1 has already visited the flag and moved away from both the flag and the adjacent positions
         * pos (0,0)
         *
         * player 2 has just visited the flag
         * (pos(2,2)
         *
         * i.e. both has their spawn on the flag
         */

        Player.resetPlayerCount();
        game = new MainGame(MapNames.SPAWN_TESTING);
        game.createPlayers(2);
        flagPos = new Vector2(2,2);
        availableAdjPosFromCentre = new ArrayList<>();
        availableAdjPosFromCentre.add(new Vector2(1,1));
        availableAdjPosFromCentre.add(new Vector2(1,2));
        availableAdjPosFromCentre.add(new Vector2(2,1));
        availableAdjPosFromCentre.add(new Vector2(2,3));
        availableAdjPosFromCentre.add(new Vector2(3,3));

        cornerPos = new Vector2(0, 4);
        availableAdjPosFromCorner = new ArrayList<>();
        availableAdjPosFromCorner.add(new Vector2(0, 3));
        availableAdjPosFromCorner.add(new Vector2(1, 4));


        // simulate player 1 has previously visited flag and moved away
        player1 = game.getPlayerById(1);
        player1.setBackup((int) flagPos.x, (int) flagPos.y);

        // simulate player 2 visits flag
        player2 = game.getPlayerById(2);
        player2.move(flagPos);
        player2.setBackupHere();

        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        spawnHandler = new SpawnHandler(game.getBoard());
    }

    @Test
    public void isBackupAvailableReturnsFalseWhenBackupIsOccupied() {
        assertFalse(spawnHandler.isBackupAvailable(player1));
    }

    @Test
    public void isBackupAvailableReturnsTrueWhenBackupIsAvailable() {
        player2.move(availableAdjPosFromCentre.get(0));
        assertTrue(spawnHandler.isBackupAvailable(player1));
    }

    @Test
    public void getAvailablePositionsReturnsTheCorrectPositionsWhenNoPlayerIsInTheSurroundingArea() {
        List<Vector2> adjPosFromSpawnHandler = spawnHandler.getAvailableAdjPositions(flagPos);
        for (Vector2 pos : availableAdjPosFromCentre)
            assertTrue(adjPosFromSpawnHandler.contains(pos));
        for (Vector2 pos : adjPosFromSpawnHandler)
            assertTrue(availableAdjPosFromCentre.contains(pos));
    }

    @Test
    public void getAvailablePositionsReturnsTheCorrectPositionsWhenOnePlayerIsInTheSurroundingArea() {
        player2.move(availableAdjPosFromCentre.get(0));
        availableAdjPosFromCentre.remove(0);
        List<Vector2> adjPosFromSpawnHandler = spawnHandler.getAvailableAdjPositions(flagPos);
        for (Vector2 pos : availableAdjPosFromCentre)
            assertTrue(adjPosFromSpawnHandler.contains(pos));
        for (Vector2 pos : adjPosFromSpawnHandler)
            assertTrue(availableAdjPosFromCentre.contains(pos));
    }

    @Test
    public void getAvailablePositionsReturnsTheCorrectPositionsWhenBackupIsAtCornerAndNoPlayerIsInTheSurroundings() {
        List<Vector2> adjPosFromSpawnHandler = spawnHandler.getAvailableAdjPositions(cornerPos);
        for (Vector2 pos : availableAdjPosFromCorner)
            assertTrue(adjPosFromSpawnHandler.contains(pos));
        for (Vector2 pos : adjPosFromSpawnHandler)
            assertTrue(availableAdjPosFromCorner.contains(pos));
    }

}
