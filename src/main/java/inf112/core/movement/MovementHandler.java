package inf112.core.movement;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.game.MainGame;
import inf112.core.laser.LaserHandler;
import inf112.core.movement.util.*;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.programcards.MovementCard;
import inf112.core.programcards.ProgramCard;
import inf112.core.programcards.RotationCard;
import inf112.core.tile.*;
import inf112.core.util.LayerOperation;

import java.util.*;

import static inf112.core.board.MapLayer.*;

/**
 * A basic InputAdapter-implementation that is in control of moving the players logically as well
 * as graphically.
 *
 * @author eskil
 */
public class MovementHandler extends InputAdapter {
    private int phase = 0;   // TODO refaktorerer inn i MainGame
    private MainGame game;
    private GameBoard board;
    private List<Player> players;
    private Player activePlayer;                 // movement will affect this player. Should be changed actively
    private TiledMapTileLayer playerLayer;       // layer in which all player cells are placed (for graphics)
    private CollisionHandler collisionHandler;
    private SpawnHandler spawnHandler;
    private FlagHandler flagHandler;
    private VoidHandler voidHandler;
    private LaserHandler laserHandler;

    public MovementHandler(MainGame mainGame) {
        this.game = mainGame;
        this.board = mainGame.getBoard();
        this.playerLayer = board.getLayer(PLAYER_LAYER);
        this.players = mainGame.getPlayers();
        this.collisionHandler = new CollisionHandler(board, players);
        this.spawnHandler = new SpawnHandler(game);
        this.flagHandler = new FlagHandler(board);
        this.voidHandler = new VoidHandler(board);
        this.laserHandler = new LaserHandler(board, players);
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public List<Player> getPlayers() { return players; }

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
            case Input.Keys.T:
                runConveyors();
                gearsRotate();
                wrenchesRepair();
                pushPlayerInDirection(1);
                break;
            case Input.Keys.R:
                game.getRoundHandler().instantiateNextRoundPhase();
            case Input.Keys.L:
                fireAllLasers();
                break;
            default:
                return false;
        }
        game.removeLosers();    // strictly speaking unnecessary to call this upon every move
        game.attemptToAppointWinner();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.L:
                removeLasers();
                break;
            default:
                return false;
        }
        return true;
    }

    public void fireAllLasers(){
        laserHandler.updateLaserPositions();
        laserHandler.fireLasersVisually();
        laserHandler.dealDamageToAffectedPlayers();
        handlePossibleDeaths(laserHandler.getHitPlayers());
        laserHandler.resetHitPlayers();

    }

    public void removeLasers() {
        laserHandler.disableLasersVisually();
    }

    private void handlePossibleDeath(Player player) {
        if (player.isDead()) {
            player.reduceLifeTokens();

            if (game.hasLost(player)) {
                // remove loser physically
                System.out.println(player.getName() + " has lost");
                LayerOperation.removePlayer(playerLayer, player);
            }
            else {    // respawn
                player.resetDamageTokens();
                moveToBackup(player);
            }
        }
    }

    private void handlePossibleDeaths(Iterable<Player> possibleDeadPlayers) {
        for (Player player : possibleDeadPlayers)
                handlePossibleDeath(player);
    }

    /**
     *
     */

    public void cardMovement(Player player, int index) {
        if (!contains(player))
            throw new IllegalArgumentException("Unknown player");

        ProgramCard currentCard = player.getRegisters().get(index);
        System.out.println(currentCard.getName());
        if (currentCard instanceof MovementCard) {
            if (((MovementCard) currentCard).isForward()) {
                for (int i = 0; i < ((MovementCard) currentCard).getDistance(); i++) {
                    attemptToMoveForward(activePlayer);
                }
            } else {
                for (int i = 0; i < ((MovementCard) currentCard).getDistance(); i++) {
                    attemptToMoveBackward(activePlayer);
                }
            }
        } else if (currentCard instanceof RotationCard) {
            if (((RotationCard) currentCard).isClockwise()) {
                for (int i = 0; i < ((RotationCard) currentCard).getRotations(); i++) {
                    activePlayer.rotateRight();
                }
            } else {
                for (int i = 0; i < ((RotationCard) currentCard).getRotations(); i++) {
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
                affectedPlayer.setPrevDir(direction);
                handleOutOfBounds(affectedPlayer);                  // players outside map is moved to spawn
                handleVoidVisitation(affectedPlayer);               // players on a hole is moved to spawn
                handleFlagVisitation(affectedPlayer);
            }
        handlePossibleDeaths(affectedPlayers);
    }

    /**
     * Moves the given player one unit in the given direction (both logically and graphically),
     * without any regard to the player's surroundings. Use with care.
     *
     * @param playerToBeMoved
     * @param direction
     */
    private void moveUnchecked(Player playerToBeMoved, Direction direction) {
        LayerOperation.removePlayer(playerLayer, playerToBeMoved);     // erase player (graphically)
        playerToBeMoved.move(direction);                               // move player  (logically)
        playerToBeMoved.setPrevDir(direction);
        LayerOperation.drawPlayer(playerLayer, playerToBeMoved);       // draw player  (graphically)
    }

    /**
     * Moves the given player to the given vector position
     * with no regard to the players surroundings. Use with great care.
     *
     * @param playerToBeMoved
     * @param position
     */
    public void moveToPos(Player playerToBeMoved, Vector2 position) {
        LayerOperation.removePlayer(playerLayer, playerToBeMoved);
        playerToBeMoved.move(position);
        LayerOperation.drawPlayer(playerLayer, playerToBeMoved);
    }

    /**
     * Moves the Player to his/hers backup position, both logically and graphically.
     *
     * @param playerToBeMoved
     */
    private void moveToBackup(Player playerToBeMoved) {
        spawnHandler.initSpawning(playerToBeMoved);
    }

    /**
     * Checks if the player is outside the board dimensions, and if so, kills the player.
     *
     * WARNING: the process of moving dead players do not happen here
     *
     * @param recentlyMovedPlayer
     */
    private void handleOutOfBounds(Player recentlyMovedPlayer) {
        if (!board.onBoard(recentlyMovedPlayer)) {
            recentlyMovedPlayer.destroy();
        }
    }

    /**
     * Checks if the player is on a void (hole) on the board, and if so, kills the player.
     *
     * WARNING: the process of moving dead players do not happen here
     *
     * @param recentlyMovedPlayer
     */
    private void handleVoidVisitation(Player recentlyMovedPlayer){
        if (voidHandler.isOnVoid(recentlyMovedPlayer)){
            recentlyMovedPlayer.destroy();
        }
    }

    private void handlePusher(Player recentlyMovesPlayer){
        if (board.isOnEvenPusher(recentlyMovesPlayer)){
            attemptToMove(recentlyMovesPlayer, Direction.EAST);
        }
        if (board.isOnOddPusher(recentlyMovesPlayer)){
            attemptToMove(recentlyMovesPlayer, Direction.EAST);
        }
    }

    /**
     * Checks if the player is on the correct flag, and if so, increases his/hers flag count
     *
     * @param recentlyMovedPlayer
     */
    public void handleFlagVisitation(Player recentlyMovedPlayer) {
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
            Vector2 spawnPosition = spawnHandler.getInitialSpawnPosition(player);
            player.setBackup((int) spawnPosition.x, (int) spawnPosition.y);

            LayerOperation.removePlayer(playerLayer, player);
            player.resetPosition();
            LayerOperation.drawPlayer(playerLayer, player);
        }
    }

    public IFlagWinner getFlagWinnerChecker() {
        return flagHandler;
    }

    /**
     * Checks if a player is standing on a conveyorTile, and moves said player
     */
    public void runConveyors(){
        //only conveyors with players on them need to move
        Hashtable<Vector2, Player> queuedMoves = new Hashtable<>();
        for(Player player : players) {
            if(board.onConveyor(player)){
                queueMove(queuedMoves, player);
            }
        }
        conveyorMove(Collections.list(queuedMoves.elements()));
    }

    //This is to ensure that no two players are pushed to the same tile
    private void queueMove(Hashtable<Vector2, Player> queuedMoves, Player player) {
        MoverTile conveyor = (MoverTile) board.getConveyors().get(player.getPositionCopy());
        Vector2 nextPos =  conveyor.nextPosition();
        for(Vector2 move : queuedMoves.keySet()) {
            if(move.equals(nextPos)) {
                queuedMoves.remove(move);
                return;
            }
        }
        queuedMoves.put(nextPos, player);
    }

    private void conveyorMove(List<Player> players) {
        int count = 0;
        //Limits the while loop to the max amount of players repetitions.
        //This is a very crude fix for certain edge cases and should be fixed.
        while(!players.isEmpty() && count < game.MAX_PLAYER_LIMIT) {
            List<Player> moved = new ArrayList<>();
            for (Player player : players) {
                MovementHandler movementHandler = game.getMovementHandler();
                MoverTile conveyor = (MoverTile) board.getConveyors().get(player.getPositionCopy());
                if(board.playerOnLoc(conveyor.nextPosition()))
                    continue;
                conveyor.moveConveyor(player, movementHandler);
                MoverTile next = (MoverTile) board.getConveyors().get(player.getPositionCopy());
                if (next != null)
                    next.rotate(player);
                moved.add(player);
            }
            players.removeAll(moved);
            count++;
        }
    }

    public void gearsRotate(){
        for (Player player : players){
            if (board.onGear(player)){
                GearTile gear = (GearTile) board.getGears().get(player.getPositionCopy());
                Rotation rotation = gear.getRotation();
                if (rotation == Rotation.LEFT) player.rotateLeft();
                else player.rotateRight();
            }
        }
    }

    public void wrenchesRepair(){
        for (Player player : players){
            if (board.onWrench(player)){
                WrenchTile wrench = (WrenchTile) board.getWrenches().get(player.getPositionCopy());
                boolean single = wrench.getType();
                //Double should also give the player a optioncard, but optioncars aren't implemented
                if (!single) {
                    player.removeDamageTokens(1);
                    System.out.println(player.getName() +  " damage: " + player.getDamageTokens());
                }
                else {
                    player.removeDamageTokens(1);
                    System.out.println(player.getName() + " damage: " + player.getDamageTokens());
                }
            }
        }
    }

    public void pushPlayerInDirection(int round) {
        for (Player player : players) {
            MovementHandler movementHandler = game.getMovementHandler();
            if (board.isOnEvenPusher(player) && (round % 2 == 0)) {
                PusherTile pusherTile = (PusherTile) board.getPushers().get(player.getPositionCopy());
                Direction direction = pusherTile.getDirection();
                movementHandler.attemptToMove(player, direction);
            } else if (board.isOnOddPusher(player) && (round % 2 != 0)) {
                PusherTile pusherTile = (PusherTile) board.getPushers().get(player.getPositionCopy());
                Direction direction = pusherTile.getDirection();
                movementHandler.attemptToMove(player, direction);
            }
        }
    }
}
