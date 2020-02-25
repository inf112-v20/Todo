package inf112.core.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

import java.util.Objects;

/**
 * A simple representation of a logical as well as a graphical player/robot.
 *
 * @author eskil
 */
public class Player {
    private static int playerCount = 0;
    private String name;
    private int id;
    private Vector2 position;
    private Cell cell;
    private Rotation rotation;

    public Player() {
        this("Player");
    }

    public Player(String name) {
        this(name, new TextureRegion());
    }

    public Player(String name, TextureRegion region) {
        this(name, region, 0, 0);
    }

    public Player(String name, TextureRegion region, int xPos, int yPos) {
        this.name = name;
        this.id = ++playerCount;
        this.cell = new Cell();
        this.cell.setTile(new StaticTiledMapTile(region));
        this.position = new Vector2(xPos,yPos);
        this.rotation = Rotation.NORTH;
    }


    public String getName() {   return name;   }

    public int getId() {   return id;   }

    public Cell getCell() {   return cell;   }


    public int getX() {   return (int) position.x;   }

    public int getY() {   return (int) position.y;   }

    public Rotation getRotation() {   return rotation;   }

    public void setRotation(Rotation rotation) {   this.rotation = rotation;   }

    public void resetPosition() {   this.position.set(0f,0f);   }

    public void moveForward() {
        switch (rotation) {
            case NORTH:
                position.y += 1;
                break;
            case SOUTH:
                position.y -= 1;
                break;
            case WEST:
                position.x -= 1;
                break;
            case EAST:
                position.x += 1;
                break;
            default:
                throw new IllegalStateException("Illegal Rotation");
        }
    }

    public void rotateLeft() {
        switch (rotation) {
            case NORTH:
                this.rotation = Rotation.WEST;
                break;
            case SOUTH:
                this.rotation = Rotation.EAST;
                break;
            case WEST:
                this.rotation = Rotation.SOUTH;
                break;
            case EAST:
                this.rotation = Rotation.NORTH;
                break;
            default:
                throw new IllegalStateException("Illegal Rotation");
        }
        this.cell.setRotation(rotation.getCellRotation());
    }

    public void rotateRight() {
        switch (rotation) {
            case NORTH:
                this.rotation = Rotation.EAST;
                break;
            case SOUTH:
                this.rotation = Rotation.WEST;
                break;
            case WEST:
                this.rotation = Rotation.NORTH;
                break;
            case EAST:
                this.rotation = Rotation.SOUTH;
                break;
            default:
                throw new IllegalStateException("Illegal Rotation");
        }
        this.cell.setRotation(rotation.getCellRotation());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return id == player.id &&
                name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

}
