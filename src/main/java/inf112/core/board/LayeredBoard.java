package inf112.core.board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.core.tile.TileId;

import java.util.Hashtable;
import java.util.Map;

import static inf112.core.board.MapLayer.*;

public abstract class LayeredBoard {
    protected TiledMap tiledmap;

    public void makeBoard(){
        this.tiledmap = new TmxMapLoader().load("maps/testingMap.tmx");
    }

    public TiledMapTileLayer getLayer(MapLayer mapLayer) {
        return (TiledMapTileLayer) tiledmap.getLayers().get(mapLayer.getName());
    }

    public TiledMap getTiledmap(){
        return tiledmap;
    }

    /**
     * @return Hashtable containing TileId's with a position vector as key
     */
    public Map<Vector2, TileId> mapCollidables() {
        Map<Vector2, TileId> collidables = new Hashtable<>();
        TiledMapTileLayer layer = getLayer(COLLIDABLE_LAYER);
        for(int i = 0; i < layer.getWidth(); i++) {
            for(int j = 0; j < layer.getHeight(); j++) {
                if(layer.getCell(i, j) == null)
                    continue;
                int id = layer.getCell(i, j).getTile().getId();
                TileId tileId  = TileId.getTileId(id);
                if(tileId != null)
                    collidables.put(new Vector2(i, j), tileId);
            }
        }
        return collidables;
    }

    public void dispose(){
        tiledmap.dispose();
    }

}
