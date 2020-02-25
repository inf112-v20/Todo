package inf112.core.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.TestGame;
import org.junit.Test;

public abstract class LayeredBoard {
    private TiledMap tiledmap;

    private TiledMapTileLayer background;
    private TiledMapTileLayer players;
    private TiledMapTileLayer backups;

    public void makeBoard(){
        this.tiledmap = new TmxMapLoader().load("maps/testmap3.tmx");

        this.background = (TiledMapTileLayer) tiledmap.getLayers().get("Floor");
        this.players = (TiledMapTileLayer) tiledmap.getLayers().get("Player");
        this.backups = (TiledMapTileLayer) tiledmap.getLayers().get("Backup");
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
