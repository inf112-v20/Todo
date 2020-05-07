package inf112.core.movement;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.audio.SoundStore;
import inf112.core.board.GameBoard;
import inf112.core.cards.register.ProgramSheet;
import inf112.core.cards.Deck;
import inf112.core.game.MainGame;
import inf112.core.laser.LaserHandler;
import inf112.core.movement.util.*;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.cards.MovementCard;
import inf112.core.cards.ProgramCard;
import inf112.core.cards.RotationCard;
import inf112.core.screens.GameScreen;
import inf112.core.screens.userinterface.UserInterface;
import inf112.core.tile.*;
import inf112.core.util.ButtonFactory;
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
    private List<Player> players;              // movement will affect this player. Should be changed actively
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
        return game.getActivePlayer();
    }

    public List<Player> getPlayers() { return players; }

    public boolean add(Player player) {
        if (contains(player))
            return false;
        return game.getPlayers().add(player);
    }

    public SpawnHandler getSpawnHandler() {
        return spawnHandler;
    }

    public boolean add(Player... playersToBeAdded) {
        return game.getPlayers().addAll(Arrays.asList(playersToBeAdded));
    }

    public boolean contains(Player player) {
        return game.getPlayers().contains(player);
    }

    public void setActive(Player player) {
        if (!contains(player))
            throw new IllegalArgumentException("Unknown player");
        game.setActivePlayer(player);
    }

    public void fireAllLasers(){
        laserHandler.updateLaserPositions();
        laserHandler.fireLasersVisually();
        laserHandler.dealDamageToAffectedPlayers();
        handlePossibleDeaths(laserHandler.getHitPlayers());
        laserHandler.resetHitPlayers();
        MainGame.soundHandler.playSound(SoundStore.LASER_FIRE_1);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                removeLasers();
            }
        }, (float) 0.4);
    }

    public void removeLasers() {
        laserHandler.disableLasersVisually();
    }

    private void handlePossibleDeath(Player player) {
        if (player.isDead()) {
            player.reduceLifeTokens();
            game.getPlayerHandler().destroyPlayer(player);
            LayerOperation.removePlayer(playerLayer, player);
        }
    }

    private void handlePossibleDeaths(Iterable<Player> possibleDeadPlayers) {
        for (Player player : possibleDeadPlayers)
                handlePossibleDeath(player);
    }

    public void cardMovement(Player player, ProgramCard programCard) {
        if(game.getPlayerHandler().getDisabledPlayers().contains(player))
            return;
        if (!contains(player))
            throw new IllegalArgumentException("Unknown player");

        if (programCard instanceof MovementCard) {
            if (((MovementCard) programCard).isForward()) {
                for (int i = 0; i < ((MovementCard) programCard).getDistance(); i++) {
                    attemptToMoveForward(player);
                }
            } else {
                for (int i = 0; i < ((MovementCard) programCard).getDistance(); i++) {
                    attemptToMoveBackward(player);
                }
            }
        } else if (programCard instanceof RotationCard) {
            for(int i = 0; i < ((RotationCard) programCard).getAmount(); i++){
                player.rotate(((RotationCard) programCard).getRotation());
            }
        }
    }

    public void cardMovement(Player player, int index) {
        assert(index < ProgramSheet.NUM_OF_REGISTERS);
        cardMovement(player, player.getProgramSheet().get(index));
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
        if(affectedPlayers.isEmpty())
            return;

        Player last = affectedPlayers.get(0);
        if(collisionHandler.canGo(last.getPositionCopy(), direction)) {
            for (Player affectedPlayer : affectedPlayers) {
                moveUnchecked(affectedPlayer, direction);
                affectedPlayer.setPrevDir(direction);
                handleOutOfBounds(affectedPlayer);                  // players outside map is moved to spawn
                handleVoidVisitation(affectedPlayer);               // players on a hole is moved to spawn
                handleFlagVisitation(affectedPlayer);
            }
            MainGame.soundHandler.playSound(SoundStore.ROBOT_MOVE);
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
    public void moveToBackup(Player playerToBeMoved) {
        spawnHandler.initSpawning(playerToBeMoved);
    }

    /**
     * s if the player is outside the board dimensions, and if so, kills the player.
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
            MainGame.soundHandler.playSound(SoundStore.ROBOT_FALL);
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
            recentlyMovedPlayer.setArchiveMarkerHere();
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
            player.setArchiveMarker((int) spawnPosition.x, (int) spawnPosition.y);

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
        while(!players.isEmpty() && count < game.playerLimit) {
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
                MainGame.soundHandler.playSound(SoundStore.ROBOT_ROTATE);
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
