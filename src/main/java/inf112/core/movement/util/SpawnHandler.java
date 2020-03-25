package inf112.core.movement.util;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Player;
import inf112.core.tile.ITile;
import inf112.core.tile.SpawnTile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class should be utilized by the class responsible for movement, specifically when tasked to move
 * players to their spawn positions indicated by the map, or to their backup position.
 *
 * @author eskil
 */
public class SpawnHandler {

    private GameBoard board;
    private List<Player> players;
    private Map<Vector2, ITile> spawnPosToTileMapping;

    public SpawnHandler(GameBoard gameBoard, List<Player> players) {
        this.board = gameBoard;
        this.players = players;
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

    /**
     * Based on a given position, determines the available adjacent positions (both orthogonally and diagonally).
     * Essentially, these are the positions without any players or holes.
     *
     * @param pos, the central position
     * @return all the available adjacent positions
     */
    public List<Vector2> getAvailableAdjPositions(Vector2 pos) {
        if (!board.onBoard(pos))
            throw new IllegalArgumentException("Given position is out of board bounds.");

        return new ArrayList<>();
    }

    public boolean isBackupAvailable(Player player) {
        for (Player p : players)
            if (player.getBackupCopy().equals(p.getPositionCopy()))
                return false;
        return true;
    }
}
