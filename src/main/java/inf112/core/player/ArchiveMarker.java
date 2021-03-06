package inf112.core.player;

import com.badlogic.gdx.math.Vector2;

/**
 * A simple class for keeping track of the player backup location.
 *
 * @author pål
 */
public class ArchiveMarker {
    private Vector2 position;

    public ArchiveMarker(int xPos, int yPos){
        this.position = new Vector2(xPos,yPos);
    }

    public Vector2 getPositionCopy() {
        return position.cpy();
    }

    public float getX(){
        return this.position.x;
    }

    public float getY(){
        return this.position.y;
    }
}
