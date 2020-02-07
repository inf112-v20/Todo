package inf112.core.map;

/**
 * Map Class should represent a complete game board
 */
public interface IMap {

    /**
     *
     * @return Grid containing Locations
     */
    public IGrid<ILocation> getGrid();

    /**
     *
     * @param xVal x-coordinate
     * @param yVal y-coordinate
     * @return Location at coordinates (x, y)
     */
    public ILocation getLocation(int xVal, int yVal);

}
