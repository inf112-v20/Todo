package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Direction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import static inf112.core.tile.Attributes.*;

/**
 * TileId enum used for storing the attributes of all the different tiles that we can put on a tiledMap
 * with the getTileId function we can quickly translate the id of a TiledMapTile into a TileId with attributes
 * and functions we can use
 *
 * @author Alvar
 */
public enum TileId {

    /**
     * WallTiles. Walls have a list of directions, walls that cover multiple sides of a tile
     * will have multiple directions.
     */
    WALL_SOUTH(28, WallTile.class, COLLIDABLE, SOUTH),
    WALL_NORTH(30, WallTile.class, COLLIDABLE, NORTH),
    WALL_WEST(29, WallTile.class, COLLIDABLE, WEST),
    WALL_EAST(22, WallTile.class, COLLIDABLE, EAST),

    WALL_SOUTH_EAST(7, WallTile.class, COLLIDABLE, SOUTH, EAST),
    WALL_NORTH_EAST(15, WallTile.class, COLLIDABLE, NORTH, EAST),
    WALL_NORTH_WEST(23, WallTile.class, COLLIDABLE, NORTH, WEST),
    WALL_SOUTH_WEST(31, WallTile.class, COLLIDABLE, SOUTH, WEST),

    /**
     * Pusher_wallTiles. They are wall-tiles but with a pusher facing the opposite direction of the wall.
     * Odd pushers will push the player in their direction on registers 1, 3, and 5.
     */
    PUSHER_WALL_ODD_NORTH(8, PusherTile.class, COLLIDABLE, PUSHER, ODD, NORTH),
    PUSHER_WALL_ODD_SOUTH(2, PusherTile.class, COLLIDABLE, PUSHER, ODD, SOUTH),
    PUSHER_WALL_ODD_EAST(9, PusherTile.class, COLLIDABLE, PUSHER, ODD, EAST),
    PUSHER_WALL_ODD_WEST(11, PusherTile.class, COLLIDABLE, PUSHER, ODD, WEST),

    /**
     * Pusher_wallTiles. They are wallTiles but with a pusher facing the opposite direction of the wall.
     * Even pushers will push the player in their direction on registers 2 and 4.
     */
    PUSHER_WALL_EVEN_NORTH(0, PusherTile.class, COLLIDABLE, PUSHER, EVEN, NORTH),
    PUSHER_WALL_EVEN_SOUTH(10, PusherTile.class, COLLIDABLE, PUSHER, EVEN, SOUTH),
    PUSHER_WALL_EVEN_EAST(1, PusherTile.class, COLLIDABLE, PUSHER, EVEN, EAST),
    PUSHER_WALL_EVEN_WEST(3, PusherTile.class, COLLIDABLE, PUSHER, EVEN, WEST),

    /**
     * Laser_wallTiles. They are wallTiles but with a laser-shooter facing the opposite direction of the wall.
     * Laser-shooters will shoot a laser in its direction during the laser-phase of every round.
     */
    LASER_WALL_NORTH(44, LaserCannonTile.class, COLLIDABLE, SHOOTS_LASER, NORTH),
    LASER_WALL_SOUTH(36, LaserCannonTile.class, COLLIDABLE, SHOOTS_LASER, SOUTH),
    LASER_WALL_WEST(37, LaserCannonTile.class, COLLIDABLE, SHOOTS_LASER, WEST),
    LASER_WALL_EAST(45, LaserCannonTile.class, COLLIDABLE, SHOOTS_LASER, EAST),

    /**
     * double_laser_wallTiles. They are wallTiles but with a double laser-shooter facing the opposite direction of the wall.
     * Laser-shooters will shoot a laser in its direction during the laser-phase of every round.
     */
    LASER_WALL_DOUBLE_NORTH(93, LaserCannonTile.class, COLLIDABLE, SHOOTS_LASER, NORTH),
    LASER_WALL_DOUBLE_SOUTH(86, LaserCannonTile.class, COLLIDABLE, SHOOTS_LASER, SOUTH),
    LASER_WALL_DOUBLE_WEST(92, LaserCannonTile.class, COLLIDABLE, SHOOTS_LASER, WEST),
    LASER_WALL_DOUBLE_EAST(94, LaserCannonTile.class, COLLIDABLE, SHOOTS_LASER, EAST),

    /**
     * HoleTiles. A player that steps on a holeTile will fall to their death.
     */
    HOLE_WARNING_ROUND(5, HoleTile.class, VOID),
    HOLE_WARNING_ROUND_2(90, HoleTile.class, VOID),
    HOLE_NO_WARNING(91, HoleTile.class, VOID),
    HOLE_WARNING_NORTH_WEST(104, HoleTile.class, VOID),
    HOLE_WARNING_NORTH(105, HoleTile.class, VOID),
    HOLE_WARNING_NORTH_EAST(106, HoleTile.class, VOID),
    HOLE_WARNING_EAST(107, HoleTile.class, VOID),
    HOLE_WARNING_WEST(115, HoleTile.class, VOID),
    HOLE_WARNING_SOUTH_WEST(112, HoleTile.class, VOID),
    HOLE_WARNING_SOUTH(113, HoleTile.class, VOID),
    HOLE_WARNING_SOUTH_EAST(114, HoleTile.class, VOID),
    HOLE_WARNING_NORTH_EAST_SOUTH(108, HoleTile.class, VOID),
    HOLE_WARNING_NORTH_WEST_SOUTH(116, HoleTile.class, VOID),
    HOLE_WARNING_SOUTH_WEST_EAST(109, HoleTile.class, VOID),
    HOLE_WARNING_NORTH_WEST_EAST(117, HoleTile.class, VOID),

    /**
     * ConveyorTiles. ConveyorTiles will move a player towards their output during the conveyor phase of every round
     * ConveyorTiles should only have the optional FAST attribute, followed by the single output directionalAttribute,
     * then lastly op to two input directionalAttributes.
     * All conveyorTiles must be structured like this: CONVEYOR((fast), (rotation), outputdirection, inputdirections ... )
     */
    CONVEYOR_SOUTH_TO_NORTH(48, ConveyorTile.class, NORTH, SOUTH),
    CONVEYOR_NORTH_TO_SOUTH(49, ConveyorTile.class, SOUTH, NORTH),
    CONVEYOR_EAST_TO_WEST(50, ConveyorTile.class, WEST, EAST),
    CONVEYOR_WEST_TO_EAST(51, ConveyorTile.class, EAST, WEST),

    CONVEYOR_FAST_SOUTH_TO_NORTH(12, ConveyorTile.class, FAST, NORTH, SOUTH),
    CONVEYOR_FAST_NORTH_TO_SOUTH(20, ConveyorTile.class, FAST, SOUTH, NORTH),
    CONVEYOR_FAST_EAST_TO_WEST(21, ConveyorTile.class, FAST, WEST, EAST),
    CONVEYOR_FAST_WEST_TO_EAST(13, ConveyorTile.class, FAST, EAST, WEST),

    /**
     * ConveyorTiles with rotation
     */
    CONVEYOR_EAST_TO_SOUTH(32, ConveyorTile.class, ROTATION_LEFT, SOUTH, EAST),
    CONVEYOR_NORTH_TO_EAST(40, ConveyorTile.class, ROTATION_LEFT, EAST, NORTH),
    CONVEYOR_WEST_TO_NORTH(41, ConveyorTile.class, ROTATION_LEFT, NORTH, WEST),
    CONVEYOR_SOUTH_TO_WEST(33, ConveyorTile.class, ROTATION_LEFT, WEST, SOUTH),

    CONVEYOR_SOUTH_TO_EAST(34, ConveyorTile.class, ROTATION_RIGHT, EAST, SOUTH),
    CONVEYOR_WEST_TO_SOUTH(35, ConveyorTile.class, ROTATION_RIGHT, SOUTH, WEST),
    CONVEYOR_NORTH_TO_WEST(43, ConveyorTile.class, ROTATION_RIGHT, WEST, NORTH),
    CONVEYOR_EAST_TO_NORTH(42, ConveyorTile.class, ROTATION_RIGHT, NORTH, EAST),

    CONVEYOR_FAST_EAST_TO_SOUTH(16, ConveyorTile.class, FAST, ROTATION_LEFT, SOUTH, EAST),
    CONVEYOR_FAST_NORTH_TO_EAST(24, ConveyorTile.class, FAST, ROTATION_LEFT, EAST, NORTH),
    CONVEYOR_FAST_WEST_TO_NORTH(25, ConveyorTile.class, FAST, ROTATION_LEFT, NORTH, WEST),
    CONVEYOR_FAST_SOUTH_TO_WEST(17, ConveyorTile.class, FAST, ROTATION_LEFT, WEST, SOUTH),

    CONVEYOR_FAST_SOUTH_TO_EAST(18, ConveyorTile.class, FAST, ROTATION_RIGHT, EAST, SOUTH),
    CONVEYOR_FAST_WEST_TO_SOUTH(19, ConveyorTile.class, FAST, ROTATION_RIGHT, SOUTH, WEST),
    CONVEYOR_FAST_NORTH_TO_WEST(27, ConveyorTile.class, FAST, ROTATION_RIGHT, WEST, NORTH),
    CONVEYOR_FAST_EAST_TO_NORTH(26, ConveyorTile.class, FAST, ROTATION_RIGHT, NORTH, EAST),

    /**
     * ConveyorJunctions, a combination of two different conveyorTiles
     */
    CONVEYOR_JUNCTION_SOUTH_AND_WEST_TO_NORTH(56, ConveyorJunctionTile.class, NORTH, SOUTH, WEST),
    CONVEYOR_JUNCTION_NORTH_AND_WEST_TO_EAST(57, ConveyorJunctionTile.class, EAST, NORTH, WEST),
    CONVEYOR_JUNCTION_NORTH_AND_EAST_TO_SOUTH(58, ConveyorJunctionTile.class, SOUTH, NORTH, EAST),
    CONVEYOR_JUNCTION_SOUTH_AND_EAST_TO_WEST(59, ConveyorJunctionTile.class, WEST, SOUTH, EAST),

    CONVEYOR_JUNCTION_NORTH_AND_SOUTH_TO_EAST(60, ConveyorJunctionTile.class, EAST, NORTH, SOUTH),
    CONVEYOR_JUNCTION_WEST_AND_EAST_TO_SOUTH(61, ConveyorJunctionTile.class, SOUTH, WEST, EAST),
    CONVEYOR_JUNCTION_WEST_AND_EAST_TO_NORTH(68, ConveyorJunctionTile.class, NORTH, WEST, EAST),
    CONVEYOR_JUNCTION_NORTH_AND_SOUTH_TO_WEST(69, ConveyorJunctionTile.class, WEST, NORTH, SOUTH),

    CONVEYOR_JUNCTION_SOUTH_AND_EAST_TO_NORTH(64, ConveyorJunctionTile.class, NORTH, SOUTH, EAST),
    CONVEYOR_JUNCTION_SOUTH_AND_WEST_TO_EAST(65, ConveyorJunctionTile.class, EAST, SOUTH, WEST),
    CONVEYOR_JUNCTION_NORTH_AND_WEST_TO_SOUTH(66, ConveyorJunctionTile.class, SOUTH, NORTH, WEST),
    CONVEYOR_JUNCTION_NORTH_AND_EAST_TO_WEST(67, ConveyorJunctionTile.class, WEST, NORTH, EAST),

    CONVEYOR_JUNCTION_FAST_SOUTH_AND_WEST_TO_NORTH(72, ConveyorJunctionTile.class, FAST, NORTH, SOUTH, WEST),
    CONVEYOR_JUNCTION_FAST_NORTH_AND_WEST_TO_EAST(73, ConveyorJunctionTile.class, FAST, EAST, NORTH, WEST),
    CONVEYOR_JUNCTION_FAST_NORTH_AND_EAST_TO_SOUTH(74, ConveyorJunctionTile.class, FAST, SOUTH, NORTH, EAST),
    CONVEYOR_JUNCTION_FAST_SOUTH_AND_EAST_TO_WEST(75, ConveyorJunctionTile.class, FAST, WEST, SOUTH, EAST),

    CONVEYOR_JUNCTION_FAST_NORTH_AND_SOUTH_TO_EAST(80, ConveyorJunctionTile.class, FAST, EAST, NORTH, SOUTH),
    CONVEYOR_JUNCTION_FAST_WEST_AND_EAST_TO_SOUTH(81, ConveyorJunctionTile.class, FAST, SOUTH, WEST, EAST),
    CONVEYOR_JUNCTION_FAST_WEST_AND_EAST_TO_NORTH(83, ConveyorJunctionTile.class, FAST, NORTH, WEST, EAST),
    CONVEYOR_JUNCTION_FAST_NORTH_AND_SOUTH_TO_WEST(82, ConveyorJunctionTile.class, FAST, WEST, NORTH, SOUTH),

    CONVEYOR_JUNCTION_FAST_SOUTH_AND_EAST_TO_NORTH(76, ConveyorJunctionTile.class, FAST, NORTH, SOUTH, EAST),
    CONVEYOR_JUNCTION_FAST_SOUTH_AND_WEST_TO_EAST(77, ConveyorJunctionTile.class, FAST, EAST, SOUTH, WEST),
    CONVEYOR_JUNCTION_FAST_NORTH_AND_WEST_TO_SOUTH(85, ConveyorJunctionTile.class, FAST, SOUTH, NORTH, WEST),
    CONVEYOR_JUNCTION_FAST_NORTH_AND_EAST_TO_WEST(84, ConveyorJunctionTile.class, FAST, WEST, NORTH, EAST),



    /**
     * SpawnTiles. SpawnTiles are assigned to corresponding player and designate their original spawn-point
     */
    SPAWN_PLAYER1(120, SpawnTile.class),
    SPAWN_PLAYER2(121, SpawnTile.class),
    SPAWN_PLAYER3(122, SpawnTile.class),
    SPAWN_PLAYER4(123, SpawnTile.class),
    SPAWN_PLAYER5(128, SpawnTile.class),
    SPAWN_PLAYER6(129, SpawnTile.class),
    SPAWN_PLAYER7(130, SpawnTile.class),
    SPAWN_PLAYER8(131, SpawnTile.class),

    /**
     * FlagTiles. When a Player ends his/hers move on a FlagTile, it should be registered.
     */
    FLAG_1(54, FlagTile.class),
    FLAG_2(62, FlagTile.class),
    FLAG_3(70, FlagTile.class),
    FLAG_4(78, FlagTile.class),

    /**
     * LaserTiles
     */
    SINGLE_LASER_VERTICAL(46, LaserTile.class, VERTICAL),
    SINGLE_LASER_HORIZONTAL(38, LaserTile.class, HORIZONTAL),
    SINGLE_LASER_CROSS(39, LaserTile.class, VERTICAL, HORIZONTAL),

    /**
     * GearTiles
     */

    GEAR_LEFT(52, GearTile.class, LEFT),
    GEAR_RIGHT(53, GearTile.class, RIGHT),

    /**
     *WrenchTiles
     */

    WRENCH_SINGLE(14, WrenchTile.class, SINGLE),
    WRENCH_DOUBLE(6, WrenchTile.class, DOUBLE)
    ;

    private int id;
    private Class<? extends ITile> implementationClass;
    private ArrayList<Attributes> attributes;
    private static Map<Integer, TileId> idTable = createIdTable();

    /**
     * @param id id of tile corresponding to the id of its graphical representation in the TileMap TileSheet
     * @param implementationClass ITile object that represents a given TileId
     * @param attributes Attributes of Tile
     */
    TileId(int id, Class<? extends ITile> implementationClass , Attributes... attributes) {
        //id of tiles are shifted by 1 for some reason
        this.id = id + 1;
        this.implementationClass = implementationClass;
        this.attributes = new ArrayList<>();
        Collections.addAll(this.attributes, attributes);
    }

    /**
     * Creates a lookup Table for TileIds
     * @return hashtable
     */
    private static Map<Integer, TileId> createIdTable() {
        Map<Integer, TileId> idTable = new Hashtable<>();
        for(TileId tileId : TileId.values()) {
            idTable.put(tileId.id, tileId);
        }
        return idTable;
    }

    public Class<? extends ITile> getImplementationClass() {return implementationClass;}

    private static Map<Integer, TileId> getIdTable() {
        return idTable;
    }

    public static TileId getTileId(int id) {
        return getIdTable().get(id);
    };

    /**
     * Converts Attribute directions to playerDirections for comparisons
     * @return List of Directions
     */
    public List<Direction> getFacingDirections() {
        List<Direction> facingDirections = new ArrayList<>();
        for(Attributes a : attributes){
            if(a.equals(NORTH) || a.equals(SOUTH) || a.equals(WEST) || a.equals(EAST))
                facingDirections.add(Attributes.translateToDir(a));
        }
        return facingDirections;
    }

    /**
     *
     * @return List of Conveyors
     */
    private static List<TileId> getConveyors() {
        return Arrays.stream(TileId.values()).filter(s -> s.getImplementationClass() == ConveyorTile.class).collect(Collectors.toList());
    }

    /**
     * Returns the TileId of Conveyor that matches given input and output directions
     * @param outputDir
     * @param inputDir
     * @return TileId of Conveyor
     */
    public static TileId getConveyor(Direction outputDir, Direction inputDir) {
        for(TileId id : getConveyors()) {
            List<Direction> directions = id.getFacingDirections();
            if(directions.get(0) == outputDir && directions.get(1) == inputDir)
                return id;
        }
        throw new IllegalArgumentException("No such Conveyor");
    }

    /**
     *
     * @param pos position of ITile to be instantiated
     * @return Correct ITile object depending on TileId it is created from
     */
    public ITile instantiate(Vector2 pos) {
        if(implementationClass == null)
            return null;

        try {
            Constructor<? extends ITile> constructor = implementationClass.getDeclaredConstructor(Vector2.class, TileId.class);
            return constructor.newInstance(pos.cpy(), this);
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<Attributes> getAttributes() { return attributes; }

    public boolean hasAttribute(Attributes atr) {
        return this.attributes.contains(atr);
    }

    public int getId() { return this.id; }
}
