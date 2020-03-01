package inf112.core.tile;

import inf112.core.player.Direction;

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
    WALL_SOUTH(28, Attributes.SOUTH),
    WALL_NORTH(30, Attributes.NORTH),
    WALL_WEST(29, Attributes.WEST),
    WALL_EAST(22, Attributes.EAST),

    WALL_SOUTH_EAST(7, Attributes.SOUTH, Attributes.EAST),
    WALL_NORTH_EAST(15, Attributes.NORTH, Attributes.EAST),
    WALL_NORTH_WEST(23, Attributes.NORTH, Attributes.WEST),
    WALL_SOUTH_WEST(31, Attributes.SOUTH, Attributes.WEST),

    SPAWN_PLAYER1(120),
    SPAWN_PLAYER2(121),
    SPAWN_PLAYER3(122),
    SPAWN_PLAYER4(123),
    SPAWN_PLAYER5(128),
    SPAWN_PLAYER6(129),
    SPAWN_PLAYER7(130),
    SPAWN_PLAYER8(131),
    ;

    private int id;
    private ArrayList<Attributes> attributes;
    private static Map<Integer, TileId> idTable = createIdTable();


    TileId(int id, Attributes... attributes) {
        //id of tiles are shifted by 1 for some reason
        this.id = id + 1;
        this.attributes = new ArrayList<>();
        Collections.addAll(this.attributes, attributes);
    }

    private static Map<Integer, TileId> createIdTable() {
        Map<Integer, TileId> idTable = new Hashtable<>();
        for(TileId tileId : TileId.values()) {
            idTable.put(tileId.id, tileId);
        }
        return idTable;
    }

    private static Map<Integer, TileId> getIdTable() {
        return idTable;
    }

    public static TileId getTileId(int id) {
        return getIdTable().get(id);
    };

    public List<Direction> getFacingDirections() {
        List<Direction> facingDirections = new ArrayList<>();
        for(Attributes a : attributes){
            if(a.equals(NORTH) || a.equals(SOUTH) || a.equals(WEST) || a.equals(EAST))
                facingDirections.add(Attributes.translateDir(a));
        }
        return facingDirections;
    }

    public List<Attributes> getAttributes() { return attributes; }

    public int getId() { return this.id; }
}
