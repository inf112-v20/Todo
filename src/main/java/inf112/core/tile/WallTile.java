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
