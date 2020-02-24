package inf112.core.player;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

/**
 * A rotation enum specifically designed for integrating the cardinal directions of a robot with
 * TiledMapTileLayer.Cell's representation of rotation.
 *
 * @author eskil
 */
public enum Rotation {
    NORTH(Cell.ROTATE_0), WEST(Cell.ROTATE_90), SOUTH(Cell.ROTATE_180), EAST(Cell.ROTATE_270);

    private int cellRotation;

    Rotation(int cellRotation) {   this.cellRotation = cellRotation;   }

    public int getCellRotation() {
        return this.cellRotation;
    }
}