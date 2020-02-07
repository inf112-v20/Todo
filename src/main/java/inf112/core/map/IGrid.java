package inf112.core.map;

import java.util.ArrayList;

/**
 * Datatype for representing a 2d grid
 *
 * @param <E> Datatype to be stored in grid
 */
public interface IGrid<E> {

    /**
     *
     * @return Height of grid
     */
    public int getHeight();

    /**
     *
     * @return Width of grid
     */
    public int getWidth();

    /**
     *
     * @return List representation of a 2d grid
     */
    public ArrayList<E> getList();

    /**
     *
     * @param xVal - x coordinate
     * @param yVal - y coordinate
     * @return Value at coordinates (x, y)
     */
    public E get(int xVal, int yVal);

}
