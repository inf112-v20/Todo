package inf112.core.board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import static inf112.core.board.MapLayers.*;

public abstract class LayeredBoard {
    private TiledMap tiledmap;

    private TiledMapTileLayer background;
    private TiledMapTileLayer players;
    private TiledMapTileLayer backups;

    public void makeBoard(){
        this.tiledmap = new TmxMapLoader().load("maps/testmap3.tmx");

        this.background = (TiledMapTileLayer) tiledmap.getLayers().get(BASE_LAYER.getName());
        this.players = (TiledMapTileLayer) tiledmap.getLayers().get(PLAYER_LAYER.getName());
        this.backups = (TiledMapTileLayer) tiledmap.getLayers().get(BACKUP_LAYER.getName());
    }
    public TiledMap getTiledmap(){
        return tiledmap;
    }

    public TiledMapTileLayer getBackground() {
        return background;
    }

    public TiledMapTileLayer getPlayers() {
        return players;
    }

    public TiledMapTileLayer getBackups() {
        return backups;
    }

    public void dispose(){
        tiledmap.dispose();
    }

}
