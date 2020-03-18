package inf112.core.board;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Player;
import inf112.core.tile.Attributes;
import inf112.core.tile.ITile;
import inf112.core.tile.TileId;

import java.util.HashMap;
import java.util.Map;

import static inf112.core.board.MapLayer.*;

public class GameBoard extends LayeredBoard {

    Map<Vector2, ITile> collidablesMap, spawnsMap, flagsMap, voidMap, conveyorMap, laserCannonMap;
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

        this.laserCannonMap = this.filterOnAttribute(collidablesMap, Attributes.SHOOTS_LASER);

        this.properties = super.tiledMap.getProperties();
    }

    private Map<Vector2, ITile> filterOnAttribute(Map<Vector2, ITile> mapping, Attributes atr) {
        if (mapping == null)
            throw new IllegalArgumentException("Provided mapping cannot be null");

        Map<Vector2, ITile> whiteList = new HashMap<>();

        for (Vector2 pos : mapping.keySet()) {
            ITile candidateTile = mapping.get(pos);
            if (candidateTile.getTileId().hasAttribute(atr))
                whiteList.put(pos, candidateTile);
        }
        return whiteList;
    }

    public Map<Vector2, ITile> getCollidables() { return collidablesMap; }

    public Map<Vector2, ITile> getLaserCannons() { return laserCannonMap; }

    public Map<Vector2, ITile> getSpawns() { return spawnsMap; }

    public Map<Vector2, ITile> getFlags() { return flagsMap; }

    public Map<Vector2, ITile> getVoids() { return voidMap; }

    public Map<Vector2, ITile> getConveyors() { return conveyorMap; }

    public TiledMapTile getTile(TileId tileId) {
        return super.tiledMap.getTileSets().getTile(tileId.getId());
    }

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

    public boolean onBoard(Player player){
        TiledMapTileLayer players = super.getLayer(PLAYER_LAYER);
        int playerX = player.getX();
        int playerY = player.getY();
        if (playerY < 0 || playerY >= players.getHeight()) { return false; }
        else if (playerX < 0 || playerX >= players.getWidth()) { return false; }
        else return true;
    }

    public OrthogonalTiledMapRenderer instantiateMapRenderer() {
        // set unit scale, how many pixels per world unit (1 unit == tilePixelHeight pixels)
        float unitScale = (float) 1/getTileHeightInPixels();
        return new OrthogonalTiledMapRenderer(super.tiledMap, unitScale);
    }

    public OrthographicCamera instantiateCamera() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, getMapWidth(), getMapHeight());                           // show this many units of the world
        camera.position.set((float) getMapWidth() / 2, (float) getMapHeight() / 2,0);    // centers the camera
        camera.update();
        return camera;
    }

}
