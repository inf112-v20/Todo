package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;

public class GearTile extends AbstractTile{
    /**
     * A base for all Tiles that implements the base necessities for a fully functioning Tile-object
     *
     * @param coordinates Position on TileMap
     * @param tileId      TileId representing Tile
     */
    public GearTile(Vector2 coordinates, TileId tileId) {
        super(coordinates, tileId);
    }
}
