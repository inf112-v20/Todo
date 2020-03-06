package inf112.core.util;

import inf112.core.tile.TileId;

/**
 * Utility class for translating a TileId enum into something more useful.
 * Do this until a better solution is found.
 *
 * @author eskil
 */
public class TranslateTileId {

    /**
     * Translate a TileId representing a flag to the corresponding flag number.
     *
     * @param tileId
     * @return
     */
    public static int toFlagNumber(TileId tileId) {
        switch (tileId) {
            case FLAG_1:
                return 1;
            case FLAG_2:
                return 2;
            case FLAG_3:
                return 3;
            case FLAG_4:
                return 4;
            default:
                throw new IllegalArgumentException("TiledId must map to a flag");
        }
    }

    /**
     * Translates a TileId representing the spawn of a certain player to that player's id
     *
     * @param tileId
     * @return
     */
    public static int toPlayerId(TileId tileId) {
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
                throw new IllegalArgumentException("TileId must map to a spawn");

        }
    }
}
