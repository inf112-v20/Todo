package inf112.core.board;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Player;
import inf112.core.tile.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameBoard extends LayeredBoard {

    Map<Vector2, ITile> collidablesMap, spawnsMap, flagsMap, voidMap, conveyorMap, laserCannonMap, gearMap, wrenchMap, pusherMap, playerMap;
    MapProperties properties;
    List<Player> players;

    public GameBoard() {
        this(MapNames.TESTING_MAP, new ArrayList<>());
    }

    public GameBoard(List<Player> players) {this(MapNames.TESTING_MAP, players);}

    public GameBoard(MapNames mapName, List<Player> players) {
        makeBoard(mapName);
        this.players = players;
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

}
