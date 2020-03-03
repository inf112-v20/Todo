package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;

public class SpawnTile extends AbstractTile {

    private final int playerNumber;    // corresponds to a Player's Id

    public SpawnTile(Vector2 coordinates, TileId tileId) {
        super(coordinates, tileId);
        this.playerNumber = tileIdToPlayerId(tileId);
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Translates a TileId representing the spawn of a certain player to that player's id
     *
     * @param tileId
     * @return
     */
    private int tileIdToPlayerId(TileId tileId) {
        switch (tileId) {
            case SPAWN_PLAYER1:
                return 1;
            case SPAWN_PLAYER2:
                return 2;
            case SPAWN_PLAYER3:
                return 3;
            case SPAWN_PLAYER4:
                return 4;
            case SPAWN_PLAYER5:
                return 5;
            case SPAWN_PLAYER6:
                return 6;
            case SPAWN_PLAYER7:
                return 7;
            case SPAWN_PLAYER8:
                return 8;
            default:
                throw new IllegalArgumentException("TileId must indicate a spawn");

        }
    }


}
