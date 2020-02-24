package inf112.core.player;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class PlayerCheckpoint {
    private Vector2 position;
    private Cell cell;


    public PlayerCheckpoint(int xPos, int yPos){
        this.position = new Vector2(xPos,yPos);
        this.cell = new TiledMapTileLayer.Cell();
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Cell getCell(){
        return cell;
    }

    public void setPosition(int xPos, int yPos){
        this.position = new Vector2(xPos,yPos);
    }
}
