package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Direction;

import java.util.List;
import static inf112.core.tile.Attributes.*;

public class ConveyorTile extends AbstractTile implements MoverTile{
    private Direction output;
    private List<Direction> inputs;
    private TileId tileId;
    private int speed;


    public ConveyorTile(Vector2 coordinates, TileId tileId) {
        super(coordinates, tileId);
        readAttributes();
    }

    private void readAttributes() {
        for(Attributes att : tileId.getAttributes()) {
            if(att == FAST) {
                speed = 2;
                continue;
            }
            if(output == null)
                output = Attributes.translateDir(att);
            else
                inputs.add(Attributes.translateDir(att));
        }
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
}
