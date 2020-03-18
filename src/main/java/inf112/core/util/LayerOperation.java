package inf112.core.util;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import inf112.core.player.Player;

public class LayerOperation {

    public static void clear(TiledMapTileLayer layer) {
        for (int x = 0; x < layer.getWidth(); x++)
            for (int y = 0; y < layer.getHeight(); y++)
                layer.setCell(x, y, null);
    }

    public static void clear(TiledMapTileLayer layer, int x, int y) {
        layer.setCell(x, y, null);
    }

    public static void draw(TiledMapTileLayer layer, int x, int y, Cell cell) {
        layer.setCell(x, y, cell);
    }

    public static void removePlayer(TiledMapTileLayer layer, Player player) {
        clear(layer, player.getX(), player.getY());
    }

    public static void drawPlayer(TiledMapTileLayer layer, Player player) {
        draw(layer, player.getX(), player.getY(), player.getCell());
    }
}
