package inf112.core.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

public class Player extends InputAdapter {
    private Vector2 position;
    private Cell cell;
    private TiledMapTileLayer layer;
    private TextureRegion region;

    public Player(TiledMapTileLayer playerLayer, TextureRegion region) {
        this.layer = playerLayer;
        this.region = region;
        this.cell = new Cell();
        this.cell.setTile(new StaticTiledMapTile(region));
        this.position = new Vector2(0,0);
    }

    @Override
    public boolean keyDown(int keycode) {
        clearLayer();
        switch (keycode) {
            case (Input.Keys.UP):
                position.y += 1;
                break;
            case (Input.Keys.DOWN):
                position.y -= 1;
                break;
            case (Input.Keys.RIGHT):
                position.x += 1;
                break;
            case (Input.Keys.LEFT):
                position.x -= 1;
                break;
            case (Input.Keys.SPACE):
                position.set(0f, 0f);
                break;
            default:
                return false;
        }
        return true;
    }

    private void clearLayer() {
        layer.setCell((int) position.x, (int) position.y, null);
    }

    public void updateMyPosition() {
        layer.setCell((int)position.x, (int)position.y, cell);
    }
}
