package inf112.core.movement;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.core.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic InputAdapter-implementation that is in control of moving the players logically as well
 * as graphically.
 *
 * @author eskil
 */
public class MovementHandler extends InputAdapter {

    private List<Player> players;
    private Player activePlayer;                 // movement will affect this player. Should be changed actively
    private TiledMapTileLayer playerLayer;       // layer in which all player cells are placed (for graphics)
    private TiledMapTileLayer checkpointLayer;

    public MovementHandler() {
        this(new TiledMapTileLayer(0,0,0,0));
    }

    public MovementHandler(TiledMapTileLayer playerLayer) {
        this.playerLayer = playerLayer;
        this.players = new ArrayList<>();
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
        checkpointLayer.setCell(activePlayer.getX(), activePlayer.getY(), activePlayer.getCell());
    }

    @Override
    public boolean keyDown(int keycode) {
        clearLayer();
        switch (keycode) {
            case Input.Keys.UP:
                activePlayer.moveForward();
                break;
            case Input.Keys.LEFT:
                activePlayer.rotateLeft();
                break;
            case Input.Keys.RIGHT:
                activePlayer.rotateRight();
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

        activePlayer.moveForward();
    }
}
