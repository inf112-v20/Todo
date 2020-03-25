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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class SpawnHandlerTest {

    private MainGame game;
    private Player player1, player2;
    private List<Player> players;
    private Vector2 flagPos;
    private List<Vector2> availableAdjPositions;

    private SpawnHandler spawnHandler;

    @Before
    public void init() {

        /*
         * Initial situation is like this:
         *
         * player 1 has already visited the flag and moved away from both the flag and the adjacent positions
         * player 2 has just visited the flag
         *
         * i.e. both has their spawn on the flag
         */

        Player.resetPlayerCount();
        game = new MainGame(MapNames.SPAWN_TESTING);
        game.createPlayers(2);
        flagPos = new Vector2(2,2);
        availableAdjPositions = new ArrayList<>();
        availableAdjPositions.add(new Vector2(1,1));
        availableAdjPositions.add(new Vector2(1,2));
        availableAdjPositions.add(new Vector2(2,1));
        availableAdjPositions.add(new Vector2(2,3));
        availableAdjPositions.add(new Vector2(3,3));


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

        spawnHandler = new SpawnHandler(game.getBoard(), players);
    }

    @Test
    public void isBackupAvailableReturnsFalseWhenBackupIsOccupied() {
        assertFalse(spawnHandler.isBackupAvailable(player1));
    }

    @Test
    public void isBackupAvailableReturnsTrueWhenBackupIsAvailable() {
        player2.move(availableAdjPositions.get(0));
        assertTrue(spawnHandler.isBackupAvailable(player1));
    }

    @Test
    public void getAvailablePositionsReturnsTheCorrectPositionsWhenNoPlayerIsInTheSurroundingArea() {
        List<Vector2> adjPosFromSpawnHandler = spawnHandler.getAvailableAdjPositions(flagPos);
        for (Vector2 pos : availableAdjPositions)
            assertTrue(adjPosFromSpawnHandler.contains(pos));
    }

}
