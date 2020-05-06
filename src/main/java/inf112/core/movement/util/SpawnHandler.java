package inf112.core.movement.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.board.MapLayer;
import inf112.core.game.MainGame;
import inf112.core.input.SpawnController;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.screens.GameScreen;
import inf112.core.tile.ITile;
import inf112.core.tile.SpawnTile;
import inf112.core.util.LayerOperation;
import inf112.core.util.VectorMovement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * This class should be utilized by the class responsible for movement, specifically when tasked to move
 * players to their spawn positions indicated by the map, or to their backup position.
 *
 * @author eskil
 */
public class SpawnHandler {

    public enum SpawnState {
        SELECT_POSITION, SELECT_ROTATION, NONE;
    }

    private MainGame game;
    private GameBoard board;
    private TiledMapTileLayer playerLayer;
    private Map<Vector2, ITile> spawnPosToTileMapping;
    private SpawnState state = SpawnState.NONE;
    private Player activePlayer;
    private Vector2 activePlayerBackupPos;
    private List<Vector2> adjPositions;

    public SpawnHandler(MainGame mainGame) {
        this.game = mainGame;
        this.board = game.getBoard();
        this.playerLayer = board.getLayer(MapLayer.PLAYER_LAYER);
        this.spawnPosToTileMapping = board.getSpawns();
    }

    public SpawnState getState() {
        return state;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public List<Vector2> getAdjPositions() {
        return adjPositions;
    }

    public void initSpawning(Player playerToBeSpawned) {
        this.activePlayer = playerToBeSpawned;
        this.activePlayerBackupPos = playerToBeSpawned.getArchiveMarkerCopy();
        game.getPlayerHandler().revivePlayer(activePlayer);

        if (isBackupAvailable()) {
            activePlayer.resetPosition();
            initRotationSelection();
        }
        else {
            initPositionSelection();
        }
    }

    public void initPositionSelection() {
        this.state = SpawnState.SELECT_POSITION;
        this.adjPositions = getAvailableAdjPositions(activePlayerBackupPos);

        // move player to one of the available positions
        game.getMovementHandler().moveToPos(activePlayer, adjPositions.get(0));
        LayerOperation.drawPlayer(playerLayer, activePlayer);
    }

    public void initRotationSelection() {
        this.state = SpawnState.SELECT_ROTATION;
        LayerOperation.drawPlayer(playerLayer, activePlayer);
    }

    /**
     * Finds the initial spawn position (implied by the GameBoard itself) of the given Player, based
     * on the player's Id
     *
     * @param player
     * @return the initial spawn position, or a generic one made uniquely for this player if no such spawn position is
     *         found on the board.
     */
    public Vector2 getInitialSpawnPosition(Player player) {
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

        List<Vector2> posList = new ArrayList<>();
        for (int x = (int) pos.x - 1; x <= (int) pos.x + 1; x++)
            for (int y = (int) pos.y - 1; y <= (int) pos.y + 1; y++) {
                Vector2 newPos = new Vector2(x, y);
                if (!board.onBoard(newPos) || newPos.equals(pos))
                    continue;

                // check for holes
                if (board.getVoids().containsKey(newPos))
                    continue;

                // check for players
                if (game.getPlayers().stream().filter(p -> p.hasPosition(newPos)).findAny().isPresent())
                    continue;
                posList.add(newPos);
            }
        return posList;
    }

    public boolean isBackupAvailable() {
        return this.isBackupAvailable(activePlayer);
    }

    public boolean isBackupAvailable(Player player) {
        Vector2 backUp = player.getArchiveMarkerCopy();
        for (Player p : game.getPlayers()) {
            if (p.equals(player))
                continue;
            if (p.hasPosition(backUp))
                return false;
        }
        return true;
    }
}
