package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;

public class HoleTile extends AbstractTile{
    /**
     * HoleTile is a Tile where entities moving onto it will fall to their death
     */
    public HoleTile(Vector2 coordinates, TileId tileId) {
        super(coordinates, tileId);
    }
}
