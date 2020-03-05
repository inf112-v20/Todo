package inf112.core.movement;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Player;
import inf112.core.tile.ITile;
import inf112.core.tile.SpawnTile;

import java.util.Map;


/**
 * This class should be utilized by the class responsible for movement, specifically when tasked to move
 * players to their spawn positions indicated by the map.
 *
 * @author eskil
 */
public class SpawnHandler {

    private Map<Vector2, ITile> spawnPosToTileMapping;

    public SpawnHandler(GameBoard gameBoard) {
        this.spawnPosToTileMapping = gameBoard.getSpawns();
    }

    /**
     * Finds the initial spawn position (implied by the GameBoard itself) of the given Player, based
     * on the player's Id
     *
     * @param player
     * @return the initial spawn position, or a generic one made uniquely for this player if no such spawn position is
     *         found on the board.
     */
    public Vector2 getSpawnPosition(Player player) {
        for (Vector2 spawnPos : spawnPosToTileMapping.keySet())
            if (((SpawnTile) spawnPosToTileMapping.get(spawnPos)).getPlayerNumber() == player.getId())
                return spawnPos;
        return new Vector2(player.getId() - 1,0);
    }
}
