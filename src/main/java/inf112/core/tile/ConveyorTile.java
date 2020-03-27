package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Direction;
import inf112.core.player.Player;

import java.util.ArrayList;
import java.util.List;

public class ConveyorTile extends AbstractTile implements MoverTile{
    private Direction output;
    private List<Direction> inputs;
    private int speed = 1;
    private Rotation rotation;


    public ConveyorTile(Vector2 coordinates, TileId tileId) {
        super(coordinates, tileId);
        inputs = new ArrayList<>();
        readAttributes(super.getTileId().getAttributes());
    }

    private void readAttributes(List<Attributes> attributes) {
        for(Attributes att : attributes) {
            switch(att) {
                case FAST:
                    speed = 2;
                    break;
                case ROTATION_LEFT:
                    rotation = Rotation.LEFT;
                    break;
                case ROTATION_RIGHT:
                    rotation = Rotation.RIGHT;
                    break;
                default:
                    if(output == null)
                        output = Attributes.translateToDir(att);
                    else
                        inputs.add(Attributes.translateToDir(att));
            }
        }
        if(rotation == null)
            rotation = Rotation.NONE;
    }

    @Override
    public Direction getOutputDir() {
        return output;
    }

    @Override
    public List<Direction> getInputDirs() {
        return inputs;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public Rotation getRotation() {
        return rotation;
    }

    @Override
    public void rotate(Player player) {
        player.rotate(this.rotation);
    }

    @Override
    public void moveConveyor(Player player, MovementHandler movementHandler) {
        Direction outputDir = getOutputDir();
        movementHandler.attemptToMove(player, outputDir);
    }
}
