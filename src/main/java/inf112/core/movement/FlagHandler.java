package inf112.core.movement;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Player;
import inf112.core.tile.ITile;

import java.util.Map;

public class FlagHandler {

    private Map<Vector2, ITile> flagPosToTileMapping;

    public FlagHandler(GameBoard gameBoard) {
        this.flagPosToTileMapping = gameBoard.getFlags();
    }

    public boolean isOnFlag(Player player) {
        return flagPosToTileMapping.get(player.getPositionCopy()) != null;
    }
}
