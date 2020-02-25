package inf112.core.player;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

/**
 * A direction enum specifically designed for integrating the cardinal directions of a robot with
 * TiledMapTileLayer.Cell's representation of directions (rotation).
 *
 * @author eskil
 */
public enum Direction {
    NORTH(Cell.ROTATE_0), WEST(Cell.ROTATE_90), SOUTH(Cell.ROTATE_180), EAST(Cell.ROTATE_270);

    public static final Direction[] DIRECTIONS = {NORTH, WEST, SOUTH, EAST};
    private int cellRotation;

    Direction(int cellRotation) {
        this.cellRotation = cellRotation;
    }

    public int getCellRotation() {
        return this.cellRotation;
    }

    /**
     * @param cellRotation, integer representing a certain right angled rotation
     * @return the Direction corresponding to the cellRotation
     * @throws IllegalArgumentException when cellRotation doesn't map to a certain Direction
     */
    public static Direction getDirection(int cellRotation) {
        for (Direction direction : DIRECTIONS)
            if (direction.getCellRotation() == cellRotation)
                return direction;
        throw new IllegalArgumentException("Invalid Cell rotation, see TiledMapTileLayer.Cell");
    }
}