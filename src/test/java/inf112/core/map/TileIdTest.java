package inf112.core.map;

import inf112.core.tile.Attributes;
import inf112.core.tile.TileId;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TileIdTest {

    @Test
    public void getTileIdReturnsCorrectTileId() {
        assertEquals(TileId.WALL_SOUTH, TileId.getTileId(29));
        assertEquals(TileId.WALL_NORTH_EAST, TileId.getTileId(16));
    }

    @Test
    public void WALL_SOUTH_WESTShouldHaveAttributesSOUTHAndWEST() {
        List<Attributes> attributes = TileId.WALL_SOUTH_WEST.getAttributes();
        for(Attributes attribute : attributes)
            assert(attribute.equals(Attributes.WEST) || attribute.equals(Attributes.SOUTH));
    }
}
