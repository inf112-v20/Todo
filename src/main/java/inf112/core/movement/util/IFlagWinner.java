package inf112.core.movement.util;

import inf112.core.player.Player;

public interface IFlagWinner {

    /**
     * Should be used to test if Player has won
     *
     * @param player
     * @return true if player has all flags on the board, otherwise false
     */
    boolean hasVisitedAllFlags(Player player);

}
