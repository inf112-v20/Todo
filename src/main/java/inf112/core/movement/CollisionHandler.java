package inf112.core.movement;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.tile.TileId;
import inf112.core.util.VectorMovement;

import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {
    private GameBoard gameBoard;
    private List<Player> players;

    public CollisionHandler(GameBoard gameBoard, List<Player> players) {
        this.gameBoard = gameBoard;
        this.players = players;
    }

    public CollisionHandler() {
        this(new GameBoard(), new ArrayList<Player>());
    }

    public void gatherAffectedPlayers(Vector2 position, Direction direction, List<Player> affectedPlayers) {
        VectorMovement.go(position, direction);
        for (Player player : players)
            if (player.getX() == position.x && player.getY() == position.y) {
                affectedPlayers.add(0, player);
                gatherAffectedPlayers(position, direction, affectedPlayers);
            }
    }

    public boolean canGo(Vector2 startPos, Direction direction) {
        Vector2 newPosition = VectorMovement.generateNew(startPos, direction);
        return false;
    }

    public TileId getTileId(Vector2 position, String layerName) {
        int id = ((TiledMapTileLayer) gameBoard.getTiledmap().getLayers().get(layerName)).getCell((int) position.x, (int) position.y).getTile().getId();
        return TileId.getTileId(id);
    }
}
