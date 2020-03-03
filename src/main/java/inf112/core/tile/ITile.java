package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;

/**
 * Interface for a basic tile object
 */
public interface ITile {

    /**
     *
     * @return x-coordinate of tile on map
     */
    float getX();

    /**
     *
     * @return y-coordinate of tile on map
     */
    float getY();

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
