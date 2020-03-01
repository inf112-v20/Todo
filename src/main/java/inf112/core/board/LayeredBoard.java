package inf112.core.board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.core.tile.TileId;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

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
     *
     *
     * @return Hashtable containing TileId's with a position vector as key
     */
    protected Map<Vector2, TileId> mapCollidables() {
        return mapPositionToTile(COLLIDABLE_LAYER);
    }

    protected Map<Vector2, TileId> mapSpawns() {
        return mapPositionToTile(SPAWN_LAYER);
    }

    private Map<Vector2, TileId> mapPositionToTile(MapLayer layerToBeScanned) {
        Map<Vector2, TileId> table = new Hashtable<>();
        TiledMapTileLayer layer = getLayer(layerToBeScanned);

        for (int x = 0; x < layer.getWidth(); x++)
            for (int y = 0; y < layer.getHeight(); y++) {
                if (layer.getCell(x, y) == null)
                    continue;
                int id = layer.getCell(x, y).getTile().getId();
                TileId tileId = TileId.getTileId(id);
                if (tileId != null)
                    table.put(new Vector2(x, y), tileId);
            }
        return table;
    }

    public void dispose(){
        tiledmap.dispose();
    }
}
