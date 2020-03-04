package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Direction;

import java.util.List;

public class WallTile extends AbstractTile implements CollidableTile {
    public WallTile(Vector2 coordinates, TileId tileId) {
        super(coordinates, tileId);
    }

    @Override
    public List<Direction> getDirections() {
        return getTileId().getFacingDirections();
    }

    /**
     * Function for checking if a Tile moving in a certain direction collides with this wall
     * Because of implementation in collisionHandler willCollide will only be called for tiles that moving Tile
     * is currently on, or going to.
     * @param pos position some ITile is moving from
     * @param direction Direction ITile is moving
     * @return True if moving Tile collides with wall
     */
    @Override
    public boolean willCollide(Vector2 pos, Direction direction) {
        if(getPos().equals(pos)) {
            if(getDirections().contains(direction)) {
                return true;
            }
        } else {
            if(getDirections().contains(Direction.invert(direction))) {
                return true;
            }
        }
        return false;
    }
}
