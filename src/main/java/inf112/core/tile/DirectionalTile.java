package inf112.core.tile;

import inf112.core.player.Direction;

import java.util.List;

/**
 * Tile that has one or more direction
 */
public interface DirectionalTile extends ITile {

    /**
     *
     * @return list of Directions of tile
     */
    public List<Direction> getDirections();
}
