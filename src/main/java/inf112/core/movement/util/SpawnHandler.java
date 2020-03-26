package inf112.core.movement.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.game.MainGame;
import inf112.core.player.Player;
import inf112.core.tile.ITile;
import inf112.core.tile.SpawnTile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * This class should be utilized by the class responsible for movement, specifically when tasked to move
 * players to their spawn positions indicated by the map, or to their backup position.
 *
 * @author eskil
 */
public class SpawnHandler extends InputAdapter {

    private enum SpawnState {
        SELECT_POSITION, SELECT_ROTATION;
    }

    private MainGame game;
    private GameBoard board;
    private Map<Vector2, ITile> spawnPosToTileMapping;
    private SpawnState state;
    private Player activePlayer;

    public SpawnHandler(MainGame mainGame) {
        this.game = mainGame;
        this.board = game.getBoard();
        this.spawnPosToTileMapping = board.getSpawns();
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

    public boolean isBackupAvailable(Player player) {
        for (Player p : game.getPlayers())
            if (player.getBackupCopy().equals(p.getPositionCopy()))
                return false;
        return true;
    }

    public void setActivePlayer(Player player) {
        this.activePlayer = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state.equals(SpawnState.SELECT_POSITION))
            switch (keycode) {
                case Input.Keys.NUMPAD_1:
                    break;
                case Input.Keys.NUMPAD_2:
                    break;
                case Input.Keys.NUMPAD_3:
                    break;
                case Input.Keys.NUMPAD_4:
                    break;
                case Input.Keys.NUMPAD_6:
                    break;
                case Input.Keys.NUMPAD_7:
                    break;
                case Input.Keys.NUMPAD_8:
                    break;
                case Input.Keys.NUMPAD_9:
                    break;
                case Input.Keys.ENTER:
                    this.state = SpawnState.SELECT_ROTATION;
                    System.out.println("Position confirmed.");
                    System.out.println("Select rotation.");
                    break;
                default:
                    System.out.println("Unknown key");
                    return false;
            }
        else { // state == SELECT_ROTATION
            switch (keycode) {
                case Input.Keys.NUMPAD_2:
                    break;
                case Input.Keys.NUMPAD_4:
                    break;
                case Input.Keys.NUMPAD_6:
                    break;
                case Input.Keys.NUMPAD_8:
                    break;
                case Input.Keys.DOWN:
                    break;
                case Input.Keys.LEFT:
                    break;
                case Input.Keys.RIGHT:
                    break;
                case Input.Keys.UP:
                    break;
                case Input.Keys.ENTER:
                    Gdx.input.setInputProcessor(game.getDefaultInputProcessor());    // spawn handling over
                    System.out.println("Rotation confirmed.");
                    break;
                default:
                    System.out.println("Unknown key");
                    return false;
            }
        }
        return true;
    }
}
