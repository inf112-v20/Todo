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
    private Direction direction;
    private PlayerBackup backup;

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
        this.direction = Direction.NORTH;
        this.backup = new PlayerBackup(xPos, yPos);
    }


    public String getName() {   return name;   }

    public int getId() {   return id;   }

    public Cell getCell() {   return cell;   }


    public int getX() {   return (int) position.x;   }

    public int getY() {   return (int) position.y;   }

    public Direction getDirection() {   return direction;   }

    public void setDirection(Direction direction) {   this.direction = direction;   }

    public void resetPosition() {   this.position.set(this.backup.getX(),this.backup.getY());   }

    public void setBackup(int xPos, int yPos) { this.backup = new PlayerBackup(xPos, yPos); }

    public void moveForward() {
        move(direction);
    }

    public void move(Direction dir) {
        switch (dir) {
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
                throw new IllegalStateException("Illegal direction");
        }
    }

    /**
     * Rotates both the logical and the graphical representation of the player 90 degrees to the left
     * of current direction.
     */
    public void rotateLeft() {
        // cell rotation is a number in {0,1,2,3}
        // a rotation to the left means increasing that number by 1
        // this is following the logic given in TiledMapTileLayer.Cell
        int newCellRotation = (direction.getCellRotation() + 1) % 4;
        this.direction = Direction.getDirection(newCellRotation);
        this.cell.setRotation(direction.getCellRotation());
    }


    /**
     * Rotates both the logical and the graphical representation of the player 90 degrees to the right
     * of current direction.
     */
    public void rotateRight() {
        // cell rotation is a number in {0,1,2,3}
        // a rotation to the right means decreasing that number by 1
        // this is following the logic given in TiledMapTileLayer.Cell
        int newCellRotation = direction.getCellRotation() - 1;
        if (newCellRotation < 0)
            newCellRotation +=4;
        this.direction = Direction.getDirection(newCellRotation);
        this.cell.setRotation(direction.getCellRotation());
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
