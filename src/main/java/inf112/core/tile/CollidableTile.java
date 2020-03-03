package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Direction;

/**
 * Tiles that are collidable
 */
public interface CollidableTile extends DirectionalTile {

    /**
     *
     * @param pos position some ITile is moving from
     * @param direction Direction ITile is moving
     * @return True if ITile collides with this tile, false if not
     */
    public boolean willCollide(Vector2 pos, Direction direction);
}
