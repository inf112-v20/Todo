package inf112.core.board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public abstract class LayeredBoard {
    private TiledMap tiledmap;

    private TiledMapTileLayer background;
    private TiledMapTileLayer walls;
    private TiledMapTileLayer players;
    private TiledMapTileLayer backups;

    public void makeBoard(){
        this.tiledmap = new TmxMapLoader().load("maps/testmap3.tmx");

        this.background = (TiledMapTileLayer) tiledmap.getLayers().get("Floor");
        this.walls = (TiledMapTileLayer) tiledmap.getLayers().get("Wall");
        this.players = (TiledMapTileLayer) tiledmap.getLayers().get("Player");
        this.backups = (TiledMapTileLayer) tiledmap.getLayers().get("Backup");
    }
    public TiledMap getTiledmap(){
        return tiledmap;
    }

    public TiledMapTileLayer getBackground() {
        return background;
    }

    public TiledMapTileLayer getWalls() {
        return walls;
    }

    public TiledMapTileLayer getPlayers() {
        return players;
    }

    public TiledMapTileLayer getBackups() {
        return backups;
    }
}
