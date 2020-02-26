package inf112.core.board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Player;
import inf112.core.tile.TileId;
import inf112.skeleton.app.TestGame;

import java.util.Hashtable;
import java.util.Map;

import static inf112.core.board.MapLayer.*;

public class GameBoard extends LayeredBoard {

    Map<Vector2, TileId> collidablesMap;

    public GameBoard(){
        makeBoard();
        collidablesMap = super.mapCollidables();
    }

    public Map<Vector2, TileId> getCollidables() { return collidablesMap; }
}
