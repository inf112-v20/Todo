package inf112.core.movement;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.core.board.GameBoard;
import inf112.core.player.Direction;
import inf112.core.player.Player;

import java.util.ArrayList;
import java.util.List;

import static inf112.core.board.MapLayer.*;

/**
 * A basic InputAdapter-implementation that is in control of moving the players logically as well
 * as graphically.
 *
 * @author eskil
 */
public class MovementHandler extends InputAdapter {
    private GameBoard board;
    private List<Player> players;
    private Player activePlayer;                 // movement will affect this player. Should be changed actively
    private TiledMapTileLayer playerLayer;       // layer in which all player cells are placed (for graphics)
    private TiledMapTileLayer backupLayer;
    private CollisionHandler collisionHandler;

    public MovementHandler(GameBoard board) {
        this.board = board;
        this.playerLayer = board.getLayer(PLAYER_LAYER);
        this.backupLayer = board.getLayer(BACKUP_LAYER);
        this.players = new ArrayList<>();
        this.collisionHandler = new CollisionHandler(board, players);
    }

    public MovementHandler() {
        this(new GameBoard());
    }

    public boolean add(Player player) {
        if (contains(player))
            return false;
        return players.add(player);
    }

    public boolean contains(Player player) {
        return players.contains(player);
    }

    public Player getPlayerByName(String playerName) {
        for (Player player: players)
            if (player.getName() == playerName)
                return player;
        return null;
    }

    public Player getPlayerById(int id) {
        for (Player player : players)
            if (player.getId() == id)
                return player;
        return null;
    }

    public Player getActive() {   return activePlayer;   }

    public void setActive(Player player) {
        if (!contains(player))
            throw new IllegalArgumentException("Unknown player");
        this.activePlayer = player;
    }

    private void clearLayer() {
        playerLayer.setCell(activePlayer.getX(), activePlayer.getY(), null);
    }

    public void update() {
        for (Player player : players)
            playerLayer.setCell(player.getX(), player.getY(), player.getCell());
    }

    public void setCheckpoint(){
        backupLayer.setCell(activePlayer.getX(), activePlayer.getY(), activePlayer.getCell());
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                moveForward(activePlayer);
                break;
            case Input.Keys.LEFT:
                activePlayer.rotateLeft();
                break;
            case Input.Keys.RIGHT:
                activePlayer.rotateRight();
                break;
            case Input.Keys.C:
                activePlayer.setBackup(activePlayer.getX(),activePlayer.getY());
                break;
            case Input.Keys.SPACE:
                activePlayer.resetPosition();
                break;
            case Input.Keys.S:
                // TODO switch active player
                // dette er egentlig utenfor oppgaven, da activePlayer settes til eieren av programkortet med høyest verdi
                break;
            default:
                return false;
        }
        return true;
    }

    /**
     * Moves the given player one unit in its current direction (both logially and graphically).
     * Any other players affected by this movement will also be moved in the same manner.
     *
     * NB: Will not handle wall collision (yet)
     *
     * @param playerToBeMoved
     */
    public void moveForward(Player playerToBeMoved) {
        List<Player> affectedPlayers = new ArrayList<>();
        collisionHandler.gatherAffectedPlayers(playerToBeMoved.getPositionCopy(), playerToBeMoved.getDirection(), affectedPlayers);

        // we need to move the players affected by activePlayer's move intent in the same direction
        for (Player affectedPlayer : affectedPlayers)
            moveUnchecked(affectedPlayer, playerToBeMoved.getDirection());

        moveForwardUnchecked(playerToBeMoved);
        handleOutOfBounds(playerToBeMoved);
    }
    
    /**
     * Moves the given player one unit in its current direction (both logically and graphically),
     * without any regard to the player's surroundings. Use with care.
     *
     * @param playerToBeMoved
     */
    private void moveForwardUnchecked(Player playerToBeMoved) {
        moveUnchecked(playerToBeMoved, playerToBeMoved.getDirection());
    }

    /**
     * Moves the given player one unit in the given direction (both logically and graphically),
     * without any regard to the player's surroundings. Use with care.
     *
     * @param playerToBeMoved
     * @param direction
     */
    private void moveUnchecked(Player playerToBeMoved, Direction direction) {
        playerLayer.setCell(playerToBeMoved.getX(), playerToBeMoved.getY(), null);                   // erase player (graphically)
        playerToBeMoved.move(direction);                                                                  // move player  (logically)
        playerLayer.setCell(playerToBeMoved.getX(), playerToBeMoved.getY(), playerToBeMoved.getCell());   // draw player  (graphically)
    }

    public void handleOutOfBounds(Player player) {
        if (!board.onBoard(player)) {
            player.resetPosition();
            player.setDirection(Direction.NORTH);
            player.getCell().setRotation(player.getDirection().getCellRotation());
        }
    }
}
