package inf112.core.movement.util;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Player;
import inf112.core.tile.ITile;

import java.util.Map;

public class GearHandler {

    private Map<Vector2, ITile> gearPosToTileMapping;

    public GearHandler(GameBoard gameBoard) { this.gearPosToTileMapping = gameBoard.getGears(); }
}