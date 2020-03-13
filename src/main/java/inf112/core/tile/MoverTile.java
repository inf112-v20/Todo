package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Direction;

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
}
