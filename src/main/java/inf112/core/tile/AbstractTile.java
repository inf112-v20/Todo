package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;

public abstract class AbstractTile implements ITile{

    private float xVal, yVal;
    private TileId tileId;

    public AbstractTile(Vector2 coordinates, TileId tileId) {
        this.xVal = coordinates.x;
        this.yVal = coordinates.y;
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
    public int getId() { return tileId.getId(); }

    @Override
    public TileId getTileId() {
        return tileId;
    }
}
