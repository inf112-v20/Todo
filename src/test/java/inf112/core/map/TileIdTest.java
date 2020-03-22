package inf112.core.map;

import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Direction;
import inf112.core.tile.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TileIdTest {

    @Test
    public void getTileIdReturnsCorrectTileId() {
        assertEquals(TileId.WALL_SOUTH, TileId.getTileId(29));
        assertEquals(TileId.WALL_NORTH_EAST, TileId.getTileId(16));
        for(TileId id : TileId.values()) {

        }
    }

    @Test
    public void WALL_SOUTHShouldHaveDirectionSOUTH(){
        Direction dir = TileId.WALL_SOUTH.getFacingDirections().get(0);
        assertEquals(dir, Direction.SOUTH);
    }

    @Test
    public void WALL_NORTHShouldHaveDirectionNORTH(){
        Direction dir = TileId.WALL_NORTH.getFacingDirections().get(0);
        assertEquals(dir, Direction.NORTH);
    }

    @Test
    public void WALL_EASTShouldHaveDirectionEAST(){
        Direction dir = TileId.WALL_EAST.getFacingDirections().get(0);
        assertEquals(dir, Direction.EAST);
    }

    @Test
    public void WALL_WESTShouldHaveDirectionWEST(){
        Direction dir = TileId.WALL_WEST.getFacingDirections().get(0);
        assertEquals(dir, Direction.WEST);
    }

    @Test
    public void WALL_SOUTH_WESTShouldHaveDirectionsSOUTHAndWEST() {
        for(Direction dir : TileId.WALL_SOUTH_WEST.getFacingDirections())
            assert(dir.equals(Direction.SOUTH) || dir.equals(Direction.WEST));
    }

    @Test
    public void WALL_NORTH_WESTShouldHaveAttributesNORTHAndWEST() {
        for(Direction dir : TileId.WALL_NORTH_WEST.getFacingDirections())
            assert(dir.equals(Direction.NORTH) || dir.equals(Direction.WEST));
    }

    @Test
    public void WALL_NORTH_EASTShouldHaveAttributesNORTHAndEAST() {
        for(Direction dir : TileId.WALL_NORTH_EAST.getFacingDirections())
        assert(dir.equals(Direction.NORTH) || dir.equals(Direction.EAST));
    }

    @Test
    public void WALL_SOUTH_EASTShouldHaveAttributesSOUTHAndEAST() {
        for(Direction dir : TileId.WALL_SOUTH_EAST.getFacingDirections())
            assert(dir.equals(Direction.SOUTH) || dir.equals(Direction.EAST));
    }

    @Test
    public void instantiateOnTileIdWallsShouldReturnAWallTileObject() {
        assertThat(TileId.WALL_NORTH.instantiate(new Vector2(1, 1)), instanceOf(WallTile.class));
    }

    @Test
    public void instantiateOnTileIdConveyorsShouldReturnAConveyorTileObject() {
        assertThat(TileId.CONVEYOR_EAST_TO_WEST.instantiate(new Vector2(1, 1)), instanceOf(ConveyorTile.class));
    }

    @Test
    public void instantiateOnTileIdJunctionsShouldReturnAJunctionTileObject() {
        assertThat(TileId.CONVEYOR_JUNCTION_NORTH_AND_SOUTH_TO_WEST.instantiate(new Vector2(1, 1)), instanceOf(JunctionTile.class));
    }

    @Test
    public void instantiateOnTileIdLasersShouldReturnALaserCannonTileObject() {
        assertThat(TileId.LASER_WALL_EAST.instantiate(new Vector2(1, 1)), instanceOf(LaserCannonTile.class));
    }

    @Test
    public void instantiateOnTileIdSpawnsShouldReturnASpawnTileObject() {
        assertThat(TileId.SPAWN_PLAYER1.instantiate(new Vector2(1, 1)), instanceOf(SpawnTile.class));
    }

    @Test
    public void instantiateOnTileIdFlagsShouldReturnAFlagTileObject() {
        assertThat(TileId.FLAG_1.instantiate(new Vector2(1, 1)), instanceOf(FlagTile.class));
    }

    @Test
    public void instantiateOnTileIdLasersShouldReturnALaserTileObject() {
        assertThat(TileId.SINGLE_LASER_VERTICAL.instantiate(new Vector2(1, 1)), instanceOf(LaserTile.class));
    }
}
