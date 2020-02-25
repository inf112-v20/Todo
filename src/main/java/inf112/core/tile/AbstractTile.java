package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;

public class AbstractTile implements ITile{

    private float xVal, yVal;
    private int id;
    private TileGroup group;
    private TileId tileId;

    public AbstractTile(Vector2 coordinates, int id) {
        this.xVal = coordinates.x;
        this.yVal = coordinates.y;
        this.id = id;

        //this.tileId = TileId.
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
    public TileId getTileId() {
        return null;
    }
}
