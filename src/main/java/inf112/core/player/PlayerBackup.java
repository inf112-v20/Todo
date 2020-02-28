package inf112.core.player;

import com.badlogic.gdx.math.Vector2;

/**
 * A simple class for keeping track of the player backup location.
 *
 * @author pål
 */
public class PlayerBackup {
    private Vector2 position;

    public PlayerBackup(int xPos, int yPos){
        this.position = new Vector2(xPos,yPos);
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getX(){
        return this.position.x;
    }

    public float getY(){
        return this.position.y;
    }
}
