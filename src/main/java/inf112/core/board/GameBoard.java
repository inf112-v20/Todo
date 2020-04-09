package inf112.core.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.tile.*;
import inf112.core.util.VectorMovement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameBoard extends LayeredBoard {
    //Camera
    OrthographicCamera camera;
    private float defaultWidth;
    private float defaultHeight;
    private float currentHeight;
    private float currentWidth;
    private float zoomSens;
    private float zoomMax;
    private float zoomMin;
    //TieleLayer maps
    Map<Vector2, ITile> collidablesMap, spawnsMap, flagsMap, voidMap, conveyorMap, laserCannonMap, gearMap, wrenchMap, pusherMap, playerMap;
    MapProperties properties;
    OrthogonalTiledMapRenderer tiledMapRenderer;
    List<Player> players;

    public GameBoard() {
        this(MapNames.TESTING_MAP, new ArrayList<>());
    }

    public GameBoard(List<Player> players) {this(MapNames.TESTING_MAP, players);}

    public GameBoard(MapNames mapName, List<Player> players) {
        makeBoard(mapName);
        this.players = players;
        //Camera
        zoomSens = tiledMap.getProperties().get("zoomsensitivity", 1f, float.class);
        zoomSens = tiledMap.getProperties().get("maxzoom", 10f, float.class);
        zoomSens = tiledMap.getProperties().get("minzoom", 2f, float.class);
        //TileLayer Maps
        this.collidablesMap = super.mapCollidables();
        this.spawnsMap = super.mapSpawns();
        this.flagsMap = super.mapFlags();
        this.voidMap = super.mapVoid();
        this.conveyorMap = super.mapConveyors();
        this.gearMap = super.mapGear();
        this.wrenchMap = super.mapWrench();
        this.playerMap = super.mapPlayers();
        this.laserCannonMap = this.filterOnAttribute(collidablesMap, Attributes.SHOOTS_LASER);
        this.pusherMap = this.filterOnAttribute(collidablesMap, Attributes.PUSHER);

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

    public OrthogonalTiledMapRenderer getTiledMapRenderer() {
        return tiledMapRenderer;
    }

    public void renderLayer(MapLayer mapLayer) {
        tiledMapRenderer.renderTileLayer(getLayer(mapLayer));
    }

    public Map<Vector2, ITile> getCollidables() { return collidablesMap; }

    public Map<Vector2, ITile> getPushers() {return pusherMap;}

    public Map<Vector2, ITile> getLaserCannons() { return laserCannonMap; }

    public Map<Vector2, ITile> getSpawns() { return spawnsMap; }

    public Map<Vector2, ITile> getFlags() { return flagsMap; }

    public Map<Vector2, ITile> getVoids() { return voidMap; }

    public Map<Vector2, ITile> getConveyors() { return conveyorMap; }

    public Map<Vector2, ITile> getGears() { return gearMap; }

    public Map<Vector2, ITile> getWrenches() { return wrenchMap; }

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

    public Boolean playerOnLoc(Vector2 position) {
        for(Player player : players) {
            if (player.getPositionCopy().equals(position))
                return true;
        }
        return false;
    }

    public boolean onBoard(Player player){
        return player.getX() >= 0 && player.getY() >= 0 &&
               player.getX() < getMapWidth() && player.getY() < getMapHeight();
    }

    public boolean onBoard(Vector2 pos) {
        return pos.x >= 0 && pos.y >= 0 && pos.x < getMapWidth() && pos.y < getMapHeight();
    }

    public void instantiateMapRenderer() {
        // set unit scale, how many pixels per world unit (1 unit == tilePixelHeight pixels)
        float unitScale = (float) 1/getTileHeightInPixels();
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(super.tiledMap, unitScale);
    }

    public OrthographicCamera instantiateCamera() {
        defaultWidth = Gdx.graphics.getWidth();
        defaultHeight = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        //camera.setToOrtho(false, getMapWidth(), getMapHeight());                           // show this many units of the world
        //camera.position.set((float) getMapWidth() / 2, (float) getMapHeight() / 2,0);    // centers the camera
        camera.update();
        return camera;
    }

    public void resize(int width, int height) {
        if(camera == null) {
            return;
        }
        currentWidth = width;
        currentHeight = height;
        camera.setToOrtho(false, width, height);

        camera.zoom = zoomMax;
        camera.position.x = getMapWidth() * getTileWidthInPixels() / 2f;
        camera.position.y = getMapHeight() * getTileHeightInPixels() / 2f;
    }

    public boolean onConveyor(Player player) {
        return getConveyors().get(player.getPositionCopy()) != null;
    }

    private List<MoverTile> findConveyors(List<Player> players) {
        List<MoverTile> conveyors = new ArrayList<>();
        for(Player player : players) {
            if(onConveyor(player)) {
                conveyors.add((MoverTile) getConveyors().get(player.getPositionCopy()));
            }
        }
        return conveyors;
    }

    public boolean onGear(Player player) { return getGears().get(player.getPositionCopy()) != null; }

    public boolean onWrench(Player player) { return getWrenches().get(player.getPositionCopy()) != null; }

    public boolean isOnEvenPusher (Player player){
        return (getPushers().get(player.getPositionCopy()) != null &&
                getPushers().get(player.getPositionCopy()).getTileId().hasAttribute(Attributes.EVEN));
    }

    public boolean isOnOddPusher (Player player){
        return (getPushers().get(player.getPositionCopy()) != null &&
                getPushers().get(player.getPositionCopy()).getTileId().hasAttribute(Attributes.ODD));
    }
    public boolean checkIfFacingAnotherPlayerWithin3squares (Player player) {
        Vector2 checkingASquare = player.getPositionCopy();
        for (int i = 0; i < 3; i++) {
            if (playerOnLoc(VectorMovement.go(checkingASquare, player.getDirection()))) {
                return true;
            }
        }
            return false;
    }

}
