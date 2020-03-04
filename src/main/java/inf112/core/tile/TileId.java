package inf112.core.tile;

import com.badlogic.gdx.math.Vector2;
import inf112.core.player.Direction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
     * Wall-tiles. Walls have a list of directions, walls that cover multiple sides of a tile
     * will have multiple directions.
     */
    WALL_SOUTH(28, WallTile.class, SOUTH),
    WALL_NORTH(30, WallTile.class,NORTH),
    WALL_WEST(29, WallTile.class, WEST),
    WALL_EAST(22, WallTile.class, EAST),

    WALL_SOUTH_EAST(7, WallTile.class, SOUTH, EAST),
    WALL_NORTH_EAST(15, WallTile.class, NORTH, EAST),
    WALL_NORTH_WEST(23, WallTile.class, NORTH, WEST),
    WALL_SOUTH_WEST(31, WallTile.class, SOUTH, WEST),

    /**
     * Spawn-tiles. SpawnTiles are assigned to corresponding player and designate their original spawn-point
     */
    SPAWN_PLAYER1(120, SpawnTile.class),
    SPAWN_PLAYER2(121, SpawnTile.class),
    SPAWN_PLAYER3(122, SpawnTile.class),
    SPAWN_PLAYER4(123, SpawnTile.class),
    SPAWN_PLAYER5(128, SpawnTile.class),
    SPAWN_PLAYER6(129, SpawnTile.class),
    SPAWN_PLAYER7(130, SpawnTile.class),
    SPAWN_PLAYER8(131, SpawnTile.class),
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
                facingDirections.add(Attributes.translateDir(a));
        }
        return facingDirections;
    }

    /**
     *
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

    public int getId() { return this.id; }
}
