package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;
import inf112.core.util.TranslateTileId;

public class FlagTile extends AbstractTile {

    private final int flagNumber;

    public FlagTile(Vector2 coordinates, TileId tileId) {
        super(coordinates, tileId);
        flagNumber = TranslateTileId.toFlagNumber(tileId);
    }

    public int getFlagNumber() {
        return flagNumber;
    }


}
