
package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class GearTile extends AbstractTile{
    /**
     * A base for all Tiles that implements the base necessities for a fully functioning Tile-object
     *
     * @param coordinates Position on TileMap
     * @param tileId      TileId representing Tile
     */

    private Rotation rotation;

    public GearTile(Vector2 coordinates, TileId tileId) {
        super(coordinates, tileId);
        readAttributes(super.getTileId().getAttributes());
    }

    private void readAttributes(List<Attributes> attributes){
        for (Attributes att : attributes){
            switch (att) {
                case RIGHT:
                    rotation = Rotation.RIGHT;
                    break;
                case LEFT:
                    rotation = Rotation.LEFT;
                    break;
                default:
                    rotation = Rotation.NONE;
            }

        }
    }

    public Rotation getRotation() { return rotation; }
}