package inf112.core.tile;

public interface Tile {

    /**
     *
     * @return x-coordinate of tile on map
     */
    int getX();

    /**
     *
     * @return y-coordinate of tile on map
     */
    int getY();

    /**
     *
     * @return TileId of tile
     */
    TileId getTileId();

}
