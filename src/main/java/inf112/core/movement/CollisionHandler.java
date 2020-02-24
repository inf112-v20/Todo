package inf112.core.movement;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.core.player.Direction;
import inf112.core.player.Player;

import java.util.List;

public class CollisionHandler {

    private List<Player> players;
    private TiledMapTileLayer playerLayer;

    public CollisionHandler(TiledMapTileLayer playerLayer) {
        this.playerLayer = playerLayer;
    }

    public boolean add(Player player) {
        return players.add(player);
    }

    public boolean handleMoveBy(Player player, Direction direction) {
        return true;
    }
}
