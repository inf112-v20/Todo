package inf112.core.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.core.cards.register.ProgramSheet;
import inf112.core.game.MainGame;
import inf112.core.cards.ProgramCard;
import inf112.core.tile.Rotation;
import inf112.core.util.VectorMovement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple representation of a logical as well as a graphical player/robot.
 *
 * @author eskil
 */
public class Player {

    private static int playerCount = 0;

    public boolean programReady = false;

    private String name;
    private int id;
    private int flagsVisited = 0;
    private Vector2 position;
    private Cell cell;
    private Direction direction;
    private Direction prevDir;
    private ArchiveMarker archiveMarker;
    private ProgramSheet programSheet;
    private int lifeTokens, damageTokens;


    public Player() {
        this("Player " + (playerCount + 1));
    }

    public Player(String name) {
        this(name, new TextureRegion());
    }

    public Player(String name, TextureRegion region) {
        this(name, region, 0, 0);
    }

    public Player(int xPos, int yPos) {
        this("Player " + (playerCount + 1), new TextureRegion(), xPos, yPos);
    }

    public Player(TextureRegion region) {
        this("Player " + (playerCount + 1), region, 0, 0);
    }

    public Player(String name, TextureRegion region, int xPos, int yPos) {
        this.id = ++playerCount;
        this.name = name;
        this.cell = new Cell();
        this.cell.setTile(new StaticTiledMapTile(region));
        this.position = new Vector2(xPos,yPos);
        this.direction = Direction.NORTH;
        this.archiveMarker = new ArchiveMarker(xPos, yPos);
        this.programSheet = new ProgramSheet();
        this.lifeTokens = 3;        // A robot can die 3 times before the player has lost
        this.damageTokens = 0;      // Starts off with 0 damage taken
    }

    public static void resetPlayerCount() {
        playerCount = 0;
    }

    public static int getPlayerCount() {
        return playerCount;
    }

    public void reduceLifeTokens() { this.lifeTokens--; }

    public int getLifeTokens() { return lifeTokens; }

    public void addDamageTokens(int amount) {
        this.damageTokens += amount;
        this.damageTokens = Math.min(damageTokens, MainGame.MAX_DAMAGE_TOKENS_LIMIT);
    }

    public void removeDamageTokens(int amount) {
        if (damageTokens <= 0) return;
        else damageTokens -= amount;
    }

    public void destroy() { this.damageTokens = MainGame.MAX_DAMAGE_TOKENS_LIMIT; }

    public void resetDamageTokens() { this.damageTokens = 0; }

    public int getDamageTokens(){ return damageTokens; }

    public String getName() { return name; }

    public int getId() { return id; }

    public Cell getCell() { return cell; }

    public int getX() { return (int) position.x; }

    public int getY() { return (int) position.y; }

    public Vector2 getPositionCopy() { return position.cpy(); }

    public boolean hasPosition(Vector2 position) { return this.position.equals(position); }

    public Direction getDirection() { return direction; }

    public void resetPosition() { this.position.set(archiveMarker.getX(), archiveMarker.getY()); }

    public void setArchiveMarker(int xPos, int yPos) { this.archiveMarker = new ArchiveMarker(xPos, yPos); }

    public void setArchiveMarkerHere() { setArchiveMarker((int) position.x, (int) position.y);}

    public Vector2 getArchiveMarkerCopy() { return archiveMarker.getPositionCopy(); }

    public int getFlagsVisited() { return flagsVisited; }

    public void setFlagsVisited(int numOfFlags) { this.flagsVisited = numOfFlags; }


    public boolean isDead() { return damageTokens >= MainGame.MAX_DAMAGE_TOKENS_LIMIT; }

    public boolean isOutOfLifeTokes() { return lifeTokens <= 0; }

    public List<ProgramCard> getRegisters(){
        return this.programSheet.getCardList();
    }

    /**
     * Moves the logical representation of the player one unit in the given direction.
     *
     * @param dir
     */
    public void move(Direction dir) {
        VectorMovement.go(position, dir);
        prevDir = dir;
    }

    /**
     * Moves the logical representation of the player to the given vector position.
     * @param pos
     */
    public void move(Vector2 pos) {
        this.position = pos.cpy();
    }


    /**
     * Rotates both the logical and the graphical representation of the player 90 degrees to the left
     * of current direction.
     */
    public void rotateLeft() {
        rotateTo(direction.rotateLeft());
    }


    /**
     * Rotates both the logical and the graphical representation of the player 90 degrees to the right
     * of current direction.
     */
    public void rotateRight() {
        rotateTo(direction.rotateRight());
    }

    public void rotateTo(Direction direction) {
        this.direction = direction;
        this.cell.setRotation(direction.getCellRotation());
    }

    public void rotate(Rotation rotation) {
        rotateTo(rotation.rotate(direction));
    }

    public void addToProgramSheet(ProgramCard card){
        this.programSheet.add(card);
    }

    public void addToHand(List<ProgramCard> programCards) {
        programSheet.addToHand(programCards);
    }

    public void clearProgramSheet() {
        programSheet.clear();
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

    public Direction getPrevDir() {
        return prevDir;
    }

    public void setPrevDir(Direction prevDir) {
        this.prevDir = prevDir;
    }

}
