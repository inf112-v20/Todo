package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;

import java.util.ArrayList;
import java.util.List;
import static inf112.core.tile.Attributes.*;

public class ConveyorTile extends AbstractTile implements MoverTile{
    private Attributes output;
    private List<Attributes> inputs;
    private int speed = 1;


    public ConveyorTile(Vector2 coordinates, TileId tileId) {
        super(coordinates, tileId);
        inputs = new ArrayList<>();
        readAttributes(super.getTileId().getAttributes());
    }

    private void readAttributes(List<Attributes> attributes) {
        for(Attributes att : attributes) {
            if(att == FAST) {
                speed = 2;
                continue;
            }
            if(output == null)
                output = att;
            else
                inputs.add(att);
        }
    }

    @Override
    public Attributes getOutputDir() {
        return output;
    }

    @Override
    public List<Attributes> getInputDirs() {
        return inputs;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void moveConveyor(Player player, MovementHandler movementHandler) {
        movementHandler.attemptToMove(player, Attributes.translateToDir(getOutputDir()));
    }
}
