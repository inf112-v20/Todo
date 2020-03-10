package inf112.core.board;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Player;
import inf112.core.tile.ITile;
import java.util.Map;

import static inf112.core.board.MapLayer.*;

public class GameBoard extends LayeredBoard {

    Map<Vector2, ITile> collidablesMap, spawnsMap, flagsMap, voidMap;

    public GameBoard() {
        this(MapNames.TESTING_MAP);
    }

    public GameBoard(MapNames mapName) {
        makeBoard(mapName);
        collidablesMap = super.mapCollidables();
        spawnsMap = super.mapSpawns();
        flagsMap = super.mapFlags();
        voidMap = super.mapVoid();
    }

    public boolean onBoard(Player player){
        TiledMapTileLayer players = super.getLayer(PLAYER_LAYER);
        int playerX = player.getX();
        int playerY = player.getY();
        if (playerY < 0 || playerY >= players.getHeight()) { return false; }
        else if (playerX < 0 || playerX >= players.getWidth()) { return false; }
        else return true;
    }
    public Map<Vector2, ITile> getCollidables() { return collidablesMap; }

    public Map<Vector2, ITile> getSpawns() { return spawnsMap; }

    public Map<Vector2, ITile> getFlags() { return flagsMap; }
    public Map<Vector2, ITile> getVoids() { return voidMap; }
}
