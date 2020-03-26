package inf112.core.movement.util;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Player;
import inf112.core.tile.Attributes;
import inf112.core.tile.ITile;

import java.util.Map;

public class PusherHandler {
    private GameBoard board;
    private Map<Vector2, ITile> pusherPosToTileMapping;

    public PusherHandler(GameBoard gameBoard) {
        this.pusherPosToTileMapping = gameBoard.getPushers();
        this.board = gameBoard;
    }
    public boolean isOnEvenPusher(Player player){
        return (pusherPosToTileMapping.get(player.getPositionCopy()) != null &&
                pusherPosToTileMapping.get(player.getPositionCopy()).getTileId().hasAttribute(Attributes.EVEN));
    }
    public boolean isOnOddPusher(Player player){
        return (pusherPosToTileMapping.get(player.getPositionCopy()) != null &&
                pusherPosToTileMapping.get(player.getPositionCopy()).getTileId().hasAttribute(Attributes.ODD));
    }
}

