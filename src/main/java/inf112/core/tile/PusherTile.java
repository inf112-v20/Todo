package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Direction;

import java.util.List;

public class PusherTile extends  WallTile{
    /**
     * PusherTile is a Tile where the players will be pushed one tile in the opposite direction if:
     * Their on the right register which is defined by the pusher
     * The pusher is faced towards the player
     */
    private Direction direction;
    public PusherTile(Vector2 coordinates, TileId tileId) {
        super(coordinates, tileId);
        readAttributes(super.getTileId().getAttributes());
    }
    private void readAttributes(List<Attributes> attributes){
        for (Attributes att : attributes){
            switch (att) {
                case NORTH:
                    direction = Direction.SOUTH;
                    break;
                case WEST:
                    direction = Direction.EAST;
                    break;
                case EAST:
                    direction = Direction.WEST;
                    break;
                case SOUTH:
                    direction = Direction.NORTH;
                    break;
            }
        }
    }
    public Direction getDirection(){return direction;}
}
