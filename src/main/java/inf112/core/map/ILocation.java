package inf112.core.map;

import java.util.List;

public interface ILocation {

    /**
     *
     * @return X value of location
     */
    public int getX();

    /**
     *
     * @return Y value of location
     */
    public int getY();

    /**
     *
     * @return FloorTile of Location
     */
    public ITile getTile();

    /**
     * @return List of items
     */
    public List<IItem> getItems();
}
