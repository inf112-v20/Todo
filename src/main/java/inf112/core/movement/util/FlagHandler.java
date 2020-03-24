package inf112.core.movement.util;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Player;
import inf112.core.tile.FlagTile;
import inf112.core.tile.ITile;

import java.util.Map;

/**
 * A utility class containing the appropriate actions regarding player flag/checkpoint visitation.
 * Should be utilized after a player move is over.
 *
 * @author eskil
 */
public class FlagHandler implements IFlagWinner {

    private Map<Vector2, ITile> flagPosToTileMapping;

    private final int numOfFlagsOnBoard;

    public FlagHandler(GameBoard gameBoard) {
        this.flagPosToTileMapping = gameBoard.getFlags();
        this.numOfFlagsOnBoard = flagPosToTileMapping.size();
    }

    /**
     * @param player
     * @return false if player isn't on a flag or if player is on the wrong flag according to game rules, and
     *         otherwise true
     */
    public boolean isOnCorrectFlag(Player player) {
        FlagTile flagTile = (FlagTile) flagPosToTileMapping.get(player.getPositionCopy());
        if (flagTile == null)    // player not on flag
            return false;

        return flagTile.getFlagNumber() == player.getFlagsVisited() + 1;
    }

    /**
     * Mindlessly updates the number of flags the player has visited.
     * Should not be called without validating the update as legal/appropriate according to game rules.
     *
     * @param player
     */
    public void incrementFlagsVisited(Player player) {
        player.setFlagsVisited(player.getFlagsVisited() + 1);
    }


    @Override
    public boolean hasVisitedAllFlags(Player player) {
        if(numOfFlagsOnBoard == 0)
            return false;
        return player.getFlagsVisited() == numOfFlagsOnBoard;
    }
}
