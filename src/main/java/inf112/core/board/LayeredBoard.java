package inf112.core.board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import static inf112.core.board.MapLayer.*;

public abstract class LayeredBoard {
    protected TiledMap tiledmap;

    protected TiledMapTileLayer background;
    protected TiledMapTileLayer players;
    protected TiledMapTileLayer backups;

    public void makeBoard(){
        this.tiledmap = new TmxMapLoader().load("maps/testmap3.tmx");
    }

    public TiledMapTileLayer getLayer(MapLayer mapLayer) {
        return (TiledMapTileLayer) tiledmap.getLayers().get(mapLayer.getName());
    }

    public TiledMap getTiledmap(){
        return tiledmap;
    }

    public void dispose(){
        tiledmap.dispose();
    }

}
