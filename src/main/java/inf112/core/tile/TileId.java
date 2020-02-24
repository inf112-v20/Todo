package inf112.core.tile;

import java.util.ArrayList;
import java.util.Collections;

public enum TileId {

    /**
     * Wall-tiles. Walls have a list of directions, walls that cover multiple sides of a tile
     * will have multiple directions.
     */
    WALL_SOUTH(28, Direction.SOUTH),
    WALL_NORTH(30, Direction.NORTH),
    WALL_WEST(29, Direction.WEST),
    WALL_EAST(22, Direction.EAST),

    WALL_SOUTH_EAST(7, Direction.SOUTH, Direction.EAST),
    WALL_NORTH_EAST(15, Direction.NORTH, Direction.EAST),
    WALL_NORTH_WEST(23, Direction.NORTH, Direction.WEST),
    WALL_SOUTH_WEST(31, Direction.SOUTH, Direction.WEST)
    ;

    private int id;

    private ArrayList<Direction> directions;

    TileId(int id, Direction ... directions) {
        this.id = id;
        Collections.addAll(this.directions, directions);
    }

    public ArrayList<Direction> getDirections() { return this.directions; }

    public int getId() { return this.id; }
}
