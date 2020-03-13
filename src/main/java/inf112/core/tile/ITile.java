package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;

/**
 * Interface for a basic tile object
 * @author Alvar
 */
public interface ITile {

    /**
     *
     * @return x-coordinate of tile on map
     */
    default float getX() {
        return getPos().x;
    };

    /**
     *
     * @return y-coordinate of tile on map
     */
    default float getY() {
        return getPos().y;
    };

    /**
     *
     * @return Vector2 representing position of Tile
     */
    Vector2 getPos();

    /**
     *
     * @return id of TileMap Object
     */
    int getId();

    /**
     *
     * @return TileId of tile
     */
    TileId getTileId();
}
