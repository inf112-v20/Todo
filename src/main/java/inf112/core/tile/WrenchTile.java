package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class WrenchTile extends AbstractTile {
    /**
     * A base for all Tiles that implements the base necessities for a fully functioning Tile-object
     *
     * @param coordinates Position on TileMap
     * @param tileId      TileId representing Tile
     */

    private boolean single;

    public WrenchTile(Vector2 coordinates, TileId tileId) {
        super(coordinates, tileId);
        readAttributes(super.getTileId().getAttributes());
    }

    private void readAttributes(List<Attributes> attributes){
        for (Attributes att : attributes){
            switch (att) {
                case SINGLE:
                    single = true;
                    break;
                case DOUBLE:
                    single = false;
                    break;
            }
        }
    }

    public boolean getType() { return single; }
}
