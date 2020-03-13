package inf112.core.movement;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.programcards.MovementCard;
import inf112.core.programcards.ProgramCard;
import inf112.core.programcards.RotationCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static inf112.core.board.MapLayer.*;

/**
 * A basic InputAdapter-implementation that is in control of moving the players logically as well
 * as graphically.
 *
 * @author eskil
 */
public class MovementHandler extends InputAdapter {
    private int phase = 0;
    private GameBoard board;
    private List<Player> players;
    private Player activePlayer;                 // movement will affect this player. Should be changed actively
    private TiledMapTileLayer playerLayer;       // layer in which all player cells are placed (for graphics)
    private CollisionHandler collisionHandler;
    private SpawnHandler spawnHandler;
    private FlagHandler flagHandler;
    private VoidHandler voidHandler;

    public MovementHandler(GameBoard board) {
        this.board = board;
        this.playerLayer = board.getLayer(PLAYER_LAYER);
        this.players = new ArrayList<>();
        this.collisionHandler = new CollisionHandler(board, players);
        this.spawnHandler = new SpawnHandler(board);
        this.flagHandler = new FlagHandler(board, 4);    // should probably not be in movementHandler
        this.voidHandler = new VoidHandler(board);
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public boolean add(Player player) {
        if (contains(player))
            return false;
        return players.add(player);
    }

    public boolean add(Player... playersToBeAdded) {
        return players.addAll(Arrays.asList(playersToBeAdded));
    }

    public boolean contains(Player player) {
        return players.contains(player);
    }

    public void setActive(Player player) {
        if (!contains(player))
            throw new IllegalArgumentException("Unknown player");
        this.activePlayer = player;
    }

    public void drawPlayers() {
        for (Player player : players)
            playerLayer.setCell(player.getX(), player.getY(), player.getCell());
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                attemptToMoveForward(activePlayer);
                break;
            case Input.Keys.DOWN:
                attemptToMoveBackward(activePlayer);
                break;
            case Input.Keys.LEFT:
                activePlayer.rotateLeft();
                break;
            case Input.Keys.RIGHT:
                activePlayer.rotateRight();
                break;
            case Input.Keys.C:
                activePlayer.setBackupHere();
                break;
            case Input.Keys.SPACE:
                moveToBackup(activePlayer);
                break;
            case Input.Keys.M:
                cardMovement(activePlayer);
                phase++;
                phase = phase % 5;
                break;
            default:
                return false;
        }
        return true;
    }

    /**
     *
     */
    public void cardMovement(Player player){
        ProgramCard currentCard = player.getSelected().get(phase);
        if (currentCard instanceof MovementCard){
            if (((MovementCard) currentCard).isForward()){
                for (int i = 0; i < ((MovementCard) currentCard).getDistance(); i++){
                    attemptToMoveForward(activePlayer);
                }
            }
            else{
                for (int i = 0; i < ((MovementCard) currentCard).getDistance(); i++){
                    attemptToMoveBackward(activePlayer);
                }
            }
        }
        else if (currentCard instanceof RotationCard){
            if (((RotationCard) currentCard).getClockwise()){
                for (int i = 0; i < ((RotationCard) currentCard).getRotations(); i++){
                    activePlayer.rotateRight();
                }
            }
            else {
                for (int i = 0; i < ((RotationCard) currentCard).getRotations(); i++){
                    activePlayer.rotateLeft();
                }
            }
        }
    }

    /**
     * Moves the given player one unit in its current direction (both logially and graphically).
     * Any other players affected by this movement will also be moved in the same manner.
     *
     *
     * @param playerToBeMoved
     */
    public void attemptToMoveForward(Player playerToBeMoved) {
        attemptToMove(playerToBeMoved, playerToBeMoved.getDirection());
    }

    /**
     * Moves the given player one unit in the direction opposite of its current direction (both logially and graphically).
     * Any other players affected by this movement will also be moved in the same manner.
     *
     *
     * @param playerToBeMoved
     */
    public void attemptToMoveBackward(Player playerToBeMoved) {
        attemptToMove(playerToBeMoved, Direction.invert(playerToBeMoved.getDirection()));
    }

    /**
     * Attempts to move the given player one unit in given direction (both logically and graphically).
     * Any other players affected by this movement will also be moved in the same manner.
     * However, if the movement would result in a player moving through something collidable, it will NOT happen.
     *
     * @param playerToBeMoved
     * @param direction
     */
    public void attemptToMove(Player playerToBeMoved, Direction direction) {
        List<Player> affectedPlayers = new ArrayList<>();
        collisionHandler.gatherAffectedPlayers(playerToBeMoved.getPositionCopy(), direction, affectedPlayers);

        Player last = affectedPlayers.get(0);
        if(collisionHandler.canGo(last.getPositionCopy(), direction))
            for (Player affectedPlayer : affectedPlayers) {
                moveUnchecked(affectedPlayer, direction);
                handleOutOfBounds(affectedPlayer);
                handleFlagVisitation(affectedPlayer);
                handleVoid(affectedPlayer);
            }
    }

    /**
     * Moves the given player one unit in the given direction (both logically and graphically),
     * without any regard to the player's surroundings. Use with care.
     *
     * @param playerToBeMoved
     * @param direction
     */
    private void moveUnchecked(Player playerToBeMoved, Direction direction) {
        playerLayer.setCell(playerToBeMoved.getX(), playerToBeMoved.getY(), null);                   // erase player (graphically)
        playerToBeMoved.move(direction);                                                                  // move player  (logically)
        playerLayer.setCell(playerToBeMoved.getX(), playerToBeMoved.getY(), playerToBeMoved.getCell());   // draw player  (graphically)
    }

    /**
     * Moves the Player to his/hers backup position, both logically and graphically.
     *
     * @param playerToBeMoved
     */
    private void moveToBackup(Player playerToBeMoved) {
        playerLayer.setCell(playerToBeMoved.getX(), playerToBeMoved.getY(), null);
        playerToBeMoved.resetPosition();
        playerLayer.setCell(playerToBeMoved.getX(), playerToBeMoved.getY(), playerToBeMoved.getCell());
    }

    /**
     * Checks if the player is outside the board dimensions, and if so, resets the players
     * position and rotation both logically and graphically.
     *
     * @param recentlyMovedPlayer
     */
    private void handleOutOfBounds(Player recentlyMovedPlayer) {
        if (!board.onBoard(recentlyMovedPlayer)) {
            moveToBackup(recentlyMovedPlayer);
        }
    }
    private void handleVoid(Player recentlyMovedPlayer){
        if (voidHandler.isOnVoid(recentlyMovedPlayer)){
            moveToBackup(recentlyMovedPlayer);
        }
    }
    /**
     * Checks if the player is on the correct flag, and if so, increases his/hers flag count
     *
     * @param recentlyMovedPlayer
     */
    private void handleFlagVisitation(Player recentlyMovedPlayer) {
        if (flagHandler.isOnCorrectFlag(recentlyMovedPlayer)) {
            flagHandler.incrementFlagsVisited(recentlyMovedPlayer);
            recentlyMovedPlayer.setBackupHere();
            System.out.println(recentlyMovedPlayer.getName() + " just visited flag " + recentlyMovedPlayer.getFlagsVisited());
        }
    }

    /**
     * Moves all players to their associated spawn point.
     *
     * Should be called exactly once; when the game initiates.
     */
    public void moveAllToSpawn() {
        for (Player player : players) {
            // first set all player's backup position to their associated spawn point
            Vector2 spawnPosition = spawnHandler.getSpawnPosition(player);
            player.setBackup((int) spawnPosition.x, (int) spawnPosition.y);
            moveToBackup(player);
        }
    }
}
