package inf112.core.movement.util;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Player;
import inf112.core.tile.HoleTile;
import inf112.core.tile.ITile;

import java.util.Map;

public class VoidHandler {

    private GameBoard board;

    public VoidHandler(GameBoard gameBoard) {
        this.board = gameBoard;
    }

    public static boolean isOnVoid(Vector2 pos, GameBoard board) {
        return board.getVoids().containsKey(pos);
    }

    public boolean isOnVoid(Vector2 pos) {
        return isOnVoid(pos, this.board);
    }

    public boolean isOnVoid(Player player) {
        return isOnVoid(player.getPositionCopy());
    }
}
