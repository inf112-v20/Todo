package inf112.core.movement;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Player;
import inf112.core.tile.HoleTile;
import inf112.core.tile.ITile;

import java.util.Map;

public class VoidHandler {
    private Map<Vector2, ITile> voidPosToTileMapping;

    public VoidHandler(GameBoard gameBoard) {
        this.voidPosToTileMapping = gameBoard.getVoids();
    }
    public boolean isOnVoid(Player player) {
        HoleTile voidTile = (HoleTile) voidPosToTileMapping.get(player.getPositionCopy());
        if (voidTile == null)    // player not on void
            return false;
        else return true;
    }
}
