package inf112.core.tile;

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
     * @return id of TileMap Object
     */
    int getId();

    /**
     *
     * @return TileId of tile
     */
    TileId getTileId();

}
