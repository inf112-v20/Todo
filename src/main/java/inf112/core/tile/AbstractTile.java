package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;

public abstract class AbstractTile implements ITile{

    private float xVal, yVal;
    private Vector2 pos;
    private TileId tileId;

    /**
     * A base for all Tiles that implements the base necessities for a fully functioning Tile-object
     * @param coordinates Position on TileMap
     * @param tileId TileId representing Tile
     */
    public AbstractTile(Vector2 coordinates, TileId tileId) {
        this.xVal = coordinates.x;
        this.yVal = coordinates.y;
        pos = coordinates.cpy();
        this.tileId = tileId;
    }

    @Override
    public float getX() {
        return xVal;
    }

    @Override
    public float getY() {
        return yVal;
    }

    @Override
    public Vector2 getPos() {
        return pos;
    }

    @Override
    public int getId() { return tileId.getId(); }

    @Override
    public TileId getTileId() {
        return tileId;
    }
}
