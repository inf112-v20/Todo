package inf112.core.tile;

import inf112.core.player.Direction;

/**
 * Enum for rotations. To be used with conveyors
 * @author Alvar
 */
public enum Rotation {
    LEFT,
    RIGHT,
    NONE
    ;

    public Direction rotate(Direction dir){
        if(this == LEFT)
            return dir.rotateLeft();
        else if(this == RIGHT)
            return dir.rotateRight();
        else
            return dir;
    }
}
