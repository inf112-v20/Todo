package inf112.core.tile;

import inf112.core.movement.MovementHandler;
import inf112.core.player.Direction;
import inf112.core.player.Player;

import java.util.List;

/**
 * General interface for conveyor-type tiles
 * @author Alvar
 */
public interface MoverTile extends ITile{

    /**
     *
     * @return Output direction of a conveyor tile. Can only be one.
     */
    public Direction getOutputDir();

    /**
     *
     * @return Input directions of a conveyor tile. Could be one or two.
     */
    public List<Direction> getInputDirs();

    /**
     *
     * @return Speed of mover-tile, can be 1 or 2.
     */
    public int getSpeed();

    /**
     *
     * @return Rotation of mover-tile, can be left, right or none
     */
    public Rotation getRotation();

    /**
     *Rotates player
     * @return
     */
    public void rotate(Player player);

    /**
     * Function that moves a player standing on a conveyorTile
     * @param player
     * @param movementHandler
     */
    public void moveConveyor(Player player, MovementHandler movementHandler);
}
