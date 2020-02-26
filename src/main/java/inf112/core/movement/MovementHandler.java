package inf112.core.movement;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.tile.TileId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Boolean onBoard(Player player){
        int playerX = player.getX();
        int playerY = player.getY();
        if (playerY < 0 || playerY >= playerLayer.getHeight()) { return false; }
        else if (playerX < 0 || playerX >= playerLayer.getWidth()) { return false; }
        else return true;
    }

    public void setCheckpoint(){
        backupLayer.setCell(activePlayer.getX(), activePlayer.getY(), activePlayer.getCell());
    }

    @Override
    public boolean keyDown(int keycode) {
        clearLayer();
        switch (keycode) {
            case Input.Keys.UP:
                moveForward();
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

    private void moveForward() {
        ArrayList<Player> playerList = new ArrayList<>();
        playerList = collisionHandler.playerCollide(activePlayer.getPosition(), activePlayer.getDirection(), playerList);

        for (Player player : playerList) {
            playerLayer.setCell(player.getX(), player.getY(), null);    // clear layer
            player.move(activePlayer.getDirection());
            playerLayer.setCell(player.getX(), player.getY(), player.getCell());
        }
        playerLayer.setCell(activePlayer.getX(), activePlayer.getY(), null);
        activePlayer.moveForward();
        playerLayer.setCell(activePlayer.getX(), activePlayer.getY(), activePlayer.getCell());
        checkOutOfBounds(activePlayer);

    }

    public void checkOutOfBounds(Player player) {
        if (!onBoard(player)) {
            player.resetPosition();
            player.setDirection(Direction.NORTH);
            player.getCell().setRotation(player.getDirection().getCellRotation());
        }
    }
}
