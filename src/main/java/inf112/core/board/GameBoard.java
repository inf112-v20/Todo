package inf112.core.board;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Player;
import inf112.core.tile.TileId;
import java.util.Map;

import static inf112.core.board.MapLayer.*;

public class GameBoard extends LayeredBoard {

    Map<Vector2, TileId> collidablesMap;

    public GameBoard(){
        makeBoard();
        collidablesMap = super.mapCollidables();
    }

    public Boolean onBoard(Player player){
        TiledMapTileLayer players = getLayer(PLAYER_LAYER);
        int playerX = player.getX();
        int playerY = player.getY();
        if (playerY < 0 || playerY >= players.getHeight()) { return false; }
        else if (playerX < 0 || playerX >= players.getWidth()) { return false; }
        else return true;
    }
    public Map<Vector2, TileId> getCollidables() { return collidablesMap; }
}
