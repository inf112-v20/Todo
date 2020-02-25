package inf112.core.tile;

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
     * @return TileId of tile
     */
    TileId getTileId();

}
