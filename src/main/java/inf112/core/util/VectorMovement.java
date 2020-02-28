package inf112.core.util;

import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Direction;

public class VectorMovement {

    public static Vector2 generateNew(Vector2 vector, Direction direction) {
        switch (direction) {
            case NORTH:
                return new Vector2(vector.x, vector.y + 1);
            case EAST:
                return new Vector2(vector.x + 1, vector.y);
            case SOUTH:
                return new Vector2(vector.x, vector.y - 1);
            case WEST:
                return new Vector2(vector.x - 1, vector.y);
            default:
                throw new IllegalArgumentException("Illegal direction given.");
        }
    }

    public static void go(Vector2 vector, Direction direction) {
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
    }
}
