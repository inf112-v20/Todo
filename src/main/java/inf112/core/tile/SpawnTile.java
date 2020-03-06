package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;
import inf112.core.util.TranslateTileId;

public class SpawnTile extends AbstractTile {

    private final int playerNumber;    // corresponds to a Player's Id

    public SpawnTile(Vector2 coordinates, TileId tileId) {
        super(coordinates, tileId);
        this.playerNumber = TranslateTileId.toPlayerId(tileId);
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
