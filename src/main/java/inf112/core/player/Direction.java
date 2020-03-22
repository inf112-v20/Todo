package inf112.core.player;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

/**
 * A direction enum specifically designed to two-way map the cardinal directions of a robot with
 * TiledMapTileLayer.Cell's representation of directions (rotation).
 *
 * @author eskil
 */
public enum Direction {
    NORTH(Cell.ROTATE_0), WEST(Cell.ROTATE_90), SOUTH(Cell.ROTATE_180), EAST(Cell.ROTATE_270);

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
        for (Direction direction : Direction.values())
            if (direction.getCellRotation() == cellRotation)
                return direction;
        throw new IllegalArgumentException("Invalid Cell rotation, see TiledMapTileLayer.Cell");
    }

    public static Direction invert(Direction direction) {
        switch (direction) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case WEST:
                return EAST;
            case EAST:
                return WEST;
        }
        return direction;
    }

    public Direction rotateLeft(){
        // cell rotation is a number in {0,1,2,3}
        // a rotation to the left means increasing that number by 1
        // this is following the logic given in TiledMapTileLayer.Cell
        int newCellRotation = (getCellRotation() + 1) % 4;
        return(getDirection(newCellRotation));
    }

    public Direction rotateRight() {
        // a rotation to the right means decreasing that number by 1
        int newCellRotation = (getCellRotation() - 1);
        if(newCellRotation < 0)
            newCellRotation = 3;
        return(getDirection(newCellRotation));
    }
}