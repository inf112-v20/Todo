package inf112.core.board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.core.tile.ITile;
import inf112.core.tile.TileId;

import java.util.Hashtable;
import java.util.Map;

import static inf112.core.board.MapLayer.*;
import static inf112.core.board.MapNames.*;

public abstract class LayeredBoard {
    protected TiledMap tiledMap;

    public void makeBoard(){
        this.tiledMap = new TmxMapLoader().load(TESTING_MAP.getName());
    }

    public TiledMapTileLayer getLayer(MapLayer mapLayer) {
        return (TiledMapTileLayer) tiledMap.getLayers().get(mapLayer.getName());
    }

    public TiledMap getTiledMap(){
        return tiledMap;
    }

    /**
     * @return Hashtable containing ITile objects with a position vector as key
     */
    protected Map<Vector2, ITile> mapCollidables() {
         return mapPositionToTile(MapLayer.COLLIDABLE_LAYER);
    }

    protected Map<Vector2, ITile> mapSpawns() {
        return mapPositionToTile(MapLayer.SPAWN_LAYER);
    }

    protected Map<Vector2, ITile> mapFlags() {
        return mapPositionToTile(FLAG_LAYER);
    }


    /**
     * Scans through a mapLayer and converts every TiledMapTile into its ITile object representation, then it creates a
     * hashTable that maps every ITile object to its Vector2 position on the TiledMap.
     * @param layerToBeScanned MapLayer. Every layer on our tiledMaps should be present in the MapLayer enum
     * @return hashTable
     * */
    private Map<Vector2, ITile> mapPositionToTile(MapLayer layerToBeScanned) {
        Map<Vector2, ITile> table = new Hashtable<>();
        TiledMapTileLayer layer = getLayer(layerToBeScanned);

        for (int x = 0; x < layer.getWidth(); x++)
            for (int y = 0; y < layer.getHeight(); y++) {
                if (layer.getCell(x, y) == null)
                    continue;
                TileId tileId = TileId.getTileId(layer.getCell(x, y).getTile().getId());
                if(tileId == null)
                    continue;
                ITile tile = tileId.instantiate(new Vector2(x, y));
                table.put(new Vector2(x, y), tile);
            }
        return table;
    }

    public void dispose(){
        tiledMap.dispose();
    }
}
