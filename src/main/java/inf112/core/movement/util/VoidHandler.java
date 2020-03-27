package inf112.core.movement.util;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Player;
import inf112.core.tile.ITile;

import java.util.Map;

public class VoidHandler {

    private Map<Vector2, ITile> voidPosToTileMapping;

    public VoidHandler(GameBoard gameBoard) {
        this.voidPosToTileMapping = gameBoard.getVoids();
    }

    public boolean isOnVoid(Player player) {
        return voidPosToTileMapping.get(player.getPositionCopy()) != null;
    }
}
