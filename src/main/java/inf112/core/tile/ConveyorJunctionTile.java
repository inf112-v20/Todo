package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.util.VectorMovement;

import java.util.ArrayList;
import java.util.List;
import static inf112.core.tile.Attributes.*;

public class ConveyorJunctionTile extends AbstractTile implements JunctionTile {
    private MoverTile primary;
    private MoverTile secondary;
    private Direction output;
    private List<Direction> inputs;
    private int speed = 1;

    public ConveyorJunctionTile(Vector2 coordinates, TileId tileId) {
        super(coordinates, tileId);
        inputs = new ArrayList<>();
        readAttributes(super.getTileId().getAttributes());
        primary = (MoverTile) TileId.getConveyor(output, inputs.get(0)).instantiate(this.getPos());
        secondary = (MoverTile) TileId.getConveyor(output, inputs.get(1)).instantiate(this.getPos());
    }

    private void readAttributes(List<Attributes> attributes) {
        for(Attributes att : attributes) {
            if(att == FAST){
                speed = 2;
                continue;
            }
            if(output == null){
                output = translateToDir(att);
                continue;
            }
            inputs.add(translateToDir(att));
        }
    }

    @Override
    public MoverTile getPrimary() {
        return primary;
    }

    @Override
    public MoverTile getSecondary() {
        return secondary;
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
        return Rotation.NONE;
    }

    @Override
    public void rotate(Player player) {
        System.out.println(player.getPrevDir());
        if(player.getPrevDir() == Direction.invert(primary.getInputDirs().get(0)))
            player.rotate(primary.getRotation());
        else if(player.getPrevDir() == Direction.invert(secondary.getInputDirs().get(0)))
            player.rotate(secondary.getRotation());
        else
            player.rotate(getRotation());
    }

    @Override
    public Vector2 nextPosition() {
        return VectorMovement.generateNew(getPos(), getOutputDir());
    }

    @Override
    public void moveConveyor(Player player, MovementHandler movementHandler) {
        Direction outputDir = getOutputDir();
        movementHandler.attemptToMove(player, outputDir);
    }
}
