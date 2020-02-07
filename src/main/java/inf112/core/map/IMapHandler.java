package inf112.core.map;

import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Handler for IMap and TiledMap
 * Translates between our IMap and the gdx TileMap.
 * Necessary for separating Gui and game logic.
 */
public interface IMapHandler {

    /**
     *
     * @return TiledMap
     */
    public TiledMap getTiledMap();

    /**
     *
     * @return IMap
     */
    public IMap getMap();

    /**
     *
     * @param map - IMap with game logic
     * @return TiledMap representation of IMap
     */
    public TiledMap convertIMap(IMap map);

    /**
     *
     * @param tiledMap - TiledMap with graphical representation of map
     * @return IMap representation of TiledMap
     */
    public IMap convertTiledMap(TiledMap tiledMap);

}
