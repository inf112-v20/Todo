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

    public Player(TiledMapTileLayer playerLayer, TextureRegion region) {
        this(playerLayer, region, 0, 0);
    }

    public Player(TiledMapTileLayer playerLayer, TextureRegion region, int xPos, int yPos) {
        this.layer = playerLayer;
        this.cell = new Cell();
        this.cell.setTile(new StaticTiledMapTile(region));
        this.position = new Vector2(xPos,yPos);
    }

    @Override
    public boolean keyDown(int keycode) {
        clearLayer();
        switch (keycode) {
            case (Input.Keys.UP):
                moveUp();
                break;
            case (Input.Keys.DOWN):
                moveDown();
                break;
            case (Input.Keys.RIGHT):
                moveRight();
                break;
            case (Input.Keys.LEFT):
                moveLeft();
                break;
            case (Input.Keys.SPACE):
                resetPosition();
                break;
            default:
                return false;
        }
        return true;
    }

    private void clearLayer() {
        layer.setCell((int) position.x, (int) position.y, null);
    }

    public void updatePosition() {
        if (layer.getCell((int) position.x, (int) position.y) == null)
            layer.setCell((int)position.x, (int)position.y, cell);
    }

    public void moveUp() {   this.position.y += 1;   }

    public void moveDown() {   this.position.y -= 1;   }

    public void moveRight() {   this.position.x += 1;   }

    public void moveLeft() {   this.position.x -= 1;   }

    public void resetPosition() {   this.position.set(0f, 0f);   }

}
