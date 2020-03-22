package inf112.core.util;

import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Direction;

public class VectorMovement {

    //TODO: Her er det noe jævlig rart som skjer:
    //For at Vector posisjonene skal stemme over med de faktiske posisjonene på kartet så må North være x+1 South være x-1 osv.
    //Selv om det er det helt motsatte av hva vi ser på TiledMap programmet.


    public static Vector2 generateNew(Vector2 vector, Direction direction) {
        switch (direction) {
            case NORTH:
                return vector.cpy().add(Vector2.Y);
            case EAST:
                return vector.cpy().add(Vector2.X);
            case SOUTH:
                return vector.cpy().sub(Vector2.Y);
            case WEST:
                return vector.cpy().sub(Vector2.X);
            default:
                throw new IllegalArgumentException("Illegal direction given.");
        }
    }

    public static Vector2 go(Vector2 vector, Direction direction) {
        switch (direction) {
            case NORTH:
                vector.add(Vector2.Y);
                break;
            case EAST:
                vector.add(Vector2.X);
                break;
            case SOUTH:
                vector.sub(Vector2.Y);
                break;
            case WEST:
                vector.sub(Vector2.X);
                break;
            default:
                throw new IllegalArgumentException("Illegal direction given.");
        }
        return vector;
    }
}
