package inf112.core.movement;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Player;
import inf112.core.tile.ITile;
import inf112.core.tile.TileId;

import java.util.Hashtable;
import java.util.Map;


/**
 * This class should be utilized by the class responsible for movement, specifically when tasked to move
 * players to their spawn positions indicated by the map.
 *
 * @author eskil
 */
public class SpawnHandler {
    private Map<Integer, Vector2> playerIdToSpawnMapping;    // map player id to a position-vector representing a spawn

    public SpawnHandler(GameBoard gameBoard) {
        this.playerIdToSpawnMapping = new Hashtable<>();

        Map<Vector2, ITile> spawnToTileIdMapping = gameBoard.getSpawns();

        for (Vector2 spawnPos : spawnToTileIdMapping.keySet()) {
            Integer playerId = tileIdToPlayerId(spawnToTileIdMapping.get(spawnPos).getTileId());
            playerIdToSpawnMapping.put(playerId, spawnPos);
        }
    }

    /**
     * Finds the initial spawn position (implied by the GameBoard itself) of the given Player, based
     * on the Player id.
     *
     * @param player
     * @return the initial spawn position, or null if the GameBoard lacks such a position
     */
    public Vector2 getSpawnPosition(Player player) {
        return playerIdToSpawnMapping.get(player.getId());
    }

    /**
     * Translates a TileId representing the spawn of a certain player to that player's id
     *
     * @param tileId
     * @return
     */
    private int tileIdToPlayerId(TileId tileId) {
        switch (tileId) {
            case SPAWN_PLAYER1:
                return 1;
            case SPAWN_PLAYER2:
                return 2;
            case SPAWN_PLAYER3:
                return 3;
            case SPAWN_PLAYER4:
                return 4;
            case SPAWN_PLAYER5:
                return 5;
            case SPAWN_PLAYER6:
                return 6;
            case SPAWN_PLAYER7:
                return 7;
            case SPAWN_PLAYER8:
                return 8;
            default:
                throw new IllegalArgumentException("TileId must indicate a spawn");

        }
    }

}
