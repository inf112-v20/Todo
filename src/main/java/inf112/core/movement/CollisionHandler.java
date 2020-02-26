package inf112.core.movement;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.tile.Attributes;
import inf112.core.tile.TileId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public ArrayList<Player> playerCollide(Vector2 position, Direction direction, ArrayList<Player> playerList) {
        Vector2 newPosition = go(position, direction);
        for (Player player : players)
            if (player.getX() == newPosition.x && player.getY() == newPosition.y) {
                playerList.add(0, player);
                playerCollide(new Vector2(player.getX(), player.getY()), direction, playerList);
            }
        return playerList;
    }

    /**
     * very un-elegant solution to colliding with walls
     *
     * @param startPosition
     * @param direction
     * @return boolean
     */
    public boolean canGo(Vector2 startPosition, Direction direction) {
        //TODO make good
        Vector2 endPosition = go(startPosition, direction);
        TileId tileStart = gameBoard.getCollidables().get(startPosition);
        TileId tileEnd = gameBoard.getCollidables().get(endPosition);
        if(tileStart != null){
            System.out.println(tileStart.getFacingDirections());
            for(Direction dir : tileStart.getFacingDirections()){
                if(dir.equals(direction))
                    return false;
            }
        }
        if(tileEnd != null){
            System.out.println(tileEnd.getFacingDirections());
            for(Direction dir : tileEnd.getFacingDirections()){
                if(dir.equals(Direction.invert(direction)))
                    return false;
            }
        }
        return true;
    }


    public static Vector2 go(Vector2 position, Direction direction) {
        switch (direction) {
            case NORTH:
                return new Vector2(position.x, position.y + 1);
            case EAST:
                return new Vector2(position.x + 1, position.y);
            case SOUTH:
                return new Vector2(position.x, position.y - 1);
            case WEST:
                return new Vector2(position.x - 1, position.y);
            default:
                throw new IllegalArgumentException("Illegal direction given.");
        }
    }

    public TileId getTileId(Vector2 position, String layerName) {
        int id = ((TiledMapTileLayer) gameBoard.getTiledmap().getLayers().get(layerName)).getCell((int) position.x, (int) position.y).getTile().getId();
        return TileId.getTileId(id);
    }
}
