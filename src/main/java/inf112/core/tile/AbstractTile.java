package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;

public abstract class AbstractTile implements ITile{

    private float xVal, yVal;
    private int id;

    public AbstractTile(Vector2 coordinates, int id) {
        this.xVal = coordinates.x;
        this.yVal = coordinates.y;
        this.id = id;
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
    public int getId() { return id; }

    @Override
    public TileId getTileId() {
        return TileId.getTileId(id);
    }
}
