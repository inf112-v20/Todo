package inf112.core.flag;

import com.badlogic.gdx.math.Vector2;
import inf112.core.tile.FlagTile;
import inf112.core.tile.ITile;
import inf112.core.tile.TileId;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FlagTest {

    ITile flagTile;
    Vector2 position = new Vector2(0,0);

    @Test
    public void flagTileWithTileIdFlag1ShouldYieldId1Test() {
        this.flagTile = new FlagTile(position, TileId.FLAG_1);
        assertTrue(((FlagTile) flagTile).getFlagNumber() == 1);
    }

    @Test
    public void flagTileWithTileIdFlag2ShouldYieldId2Test() {
        this.flagTile = new FlagTile(position, TileId.FLAG_2);
        assertTrue(((FlagTile) flagTile).getFlagNumber() == 2);
    }

    @Test
    public void flagTileWithTileIdFlag3ShouldYieldId3Test() {
        this.flagTile = new FlagTile(position, TileId.FLAG_3);
        assertTrue(((FlagTile) flagTile).getFlagNumber() == 3);
    }

    @Test
    public void flagTileWithTileIdFlag4ShouldYieldId4Test() {
        this.flagTile = new FlagTile(position, TileId.FLAG_4);
        assertTrue(((FlagTile) flagTile).getFlagNumber() == 4);
    }
}
