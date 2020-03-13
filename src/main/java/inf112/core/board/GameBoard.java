package inf112.core.board;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Player;
import inf112.core.tile.ITile;
import java.util.Map;

import static inf112.core.board.MapLayer.*;

public class GameBoard extends LayeredBoard {

    Map<Vector2, ITile> collidablesMap, spawnsMap, flagsMap, voidMap, conveyorMap;
    MapProperties properties;

    public GameBoard() {
        this(MapNames.TESTING_MAP);
    }

    public GameBoard(MapNames mapName) {
        makeBoard(mapName);
        this.collidablesMap = super.mapCollidables();
        this.spawnsMap = super.mapSpawns();
        this.flagsMap = super.mapFlags();
        this.voidMap = super.mapVoid();
        this.conveyorMap = super.mapConveyors();

        this.properties = super.tiledMap.getProperties();
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

    public Map<Vector2, ITile> getConveyors() { return conveyorMap; }

    public int getMapWidth() {
        return properties.get("width", Integer.class);
    }

    public int getMapHeight() {
        return properties.get("height", Integer.class);
    }

    public int getTileWidthInPixels() {
        return properties.get("tilewidth", Integer.class);
    }

    public int getTileHeightInPixels() {
        return properties.get("tileheight", Integer.class);
    }

    public OrthogonalTiledMapRenderer instantiateMapRenderer() {
        // set unit scale, how many pixels per world unit (1 unit == tilePixelHeight pixels)
        float unitScale = (float) 1/getTileHeightInPixels();
        return new OrthogonalTiledMapRenderer(super.tiledMap, unitScale);
    }

    public OrthographicCamera instantiateCamera() {
        OrthographicCamera camera = new OrthographicCamera();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, getMapWidth(), getMapHeight());                           // show this many units of the world
        camera.position.set((float) getMapWidth() / 2, (float) getMapHeight() / 2,0);    // centers the camera
        camera.update();
        return camera;
    }

}
