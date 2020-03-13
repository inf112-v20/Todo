package inf112.core.tile;

import inf112.core.player.Direction;

/**
 * Attributes enum to represent every important attribute a Tile can have
 *
 * @author Alvar
 */
public enum Attributes {
    /**
     * Facing directions
     */
    NORTH,
    SOUTH,
    EAST,
    WEST,
    /**
     * Tiles with SHOOTS_LASER attribute will shoot a laser towards their facing direction
     * during the shooting phase of a round
     */
    SHOOTS_LASER,
    /**
     * Tiles with COLLIDABLE attribute will block movement from their facing direction.
     */
    COLLIDABLE,
    /**
     * Tiles with VOID attribute will cause a player-robot to fall if stepped on
     */
    VOID,
    /**
     * ConveyorTiles with Fast attribute will move a player two tiles every phase.
     */
    FAST

    ;

    /**
     *
     * temporary solution to compare directional attributes with the Direction enum
     *
     * @param attribute
     * @return direction
     */
    public static Direction translateDir(Attributes attribute) {
        switch (attribute) {
            case NORTH:
                return Direction.NORTH;
            case SOUTH:
                return Direction.SOUTH;
            case WEST:
                return Direction.WEST;
            case EAST:
                return Direction.EAST;
        }
        throw new IllegalArgumentException("can only be used with directional attributes");
    }
}
