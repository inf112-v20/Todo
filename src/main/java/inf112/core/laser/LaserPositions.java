package inf112.core.laser;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.movement.util.CollisionHandler;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.tile.LaserCannonTile;
import inf112.core.util.VectorMovement;

import java.util.*;

public class LaserPositions {

    private GameBoard board;
    private Map<Vector2, Direction> cannonDirectionMap;     // map all cannon positions to their associated direction
    private List<Player> players;
    private Map<Player, Integer> hitPlayersMap;                // hit players goes here, val indicates number of beam hits
    private Set<Vector2> affectedVerticals, affectedHorizonals, affectedCrosses;

    public LaserPositions(GameBoard board, List<Player> players) {
        this.board = board;
        this.players = players;

        hitPlayersMap = new HashMap<>();

        this.affectedVerticals = new HashSet<>();
        this.affectedHorizonals = new HashSet<>();
        this.affectedCrosses = new HashSet<>();

        this.cannonDirectionMap = mapStartPositionsToBeamDirections();
    }

    public void gatherAllLaserPositions() {
        // firstly clear all sets
        affectedHorizonals.clear();
        affectedVerticals.clear();
        affectedCrosses.clear();

        // gather from wall laser cannons
        for (Vector2 initPos : cannonDirectionMap.keySet()) {
            assembleAffectedPositions(initPos.cpy(), Direction.invert(cannonDirectionMap.get(initPos)));
        }
        // gather from player laser cannons
        for (Player shooter : players) {
            assembleAffectedPositionsFromPlayer(shooter.getPositionCopy(), shooter.getDirection());
        }
        // positions both in vertical and horizontal sets must be removes therefrom and added to the crossed set
        handleCrossedLasers();
    }

    public void resetHitPlayers() {
        this.hitPlayersMap.clear();
    }

    public Set<Vector2> getAllVerticalLaserPositions() {
        return affectedVerticals;
    }

    public Set<Vector2> getAllHorizontalLaserPositions() {
        return affectedHorizonals;
    }

    public Set<Vector2> getAllCrossedLaserPositions() {
        return affectedCrosses;
    }

    public Map<Player, Integer> getHitPlayersMap() {
        return hitPlayersMap;
    }

    private void handleCrossedLasers() {
        // make affectedCrosses the intersection between the horizontal and vertical set
        this.affectedCrosses.addAll(affectedHorizonals);
        affectedCrosses.retainAll(affectedVerticals);

        // remove the intersection position from horizontal and vertical
        for (Vector2 pos : affectedCrosses) {
            affectedHorizonals.remove(pos);
            affectedVerticals.remove(pos);
        }
    }

    /**
     * Recursively assembles the positions a laser beam sweeps over.
     * Stops once something is hit. Includes the hit player's position.
     * Hit players are registered.
     *
     * WARNING: Position state will be changed
     *
     * @param playerPos
     * @param playerDir
     */
    private void assembleAffectedPositionsFromPlayer(Vector2 playerPos, Direction playerDir) {
        if (CollisionHandler.canGo(playerPos, playerDir, board))
            assembleAffectedPositions(VectorMovement.go(playerPos, playerDir), playerDir);
    }

    /**
     * Recursively assembles the positions a laser beam sweeps over.
     * Stops once something is hit. Includes the hit player's position.
     * Hit players are registered.
     *
     * WARNING:
     * (1) On the initial call this assumes that the step TO the given position is not blocked by a wall.
     * (2) Position state will be changed
     *
     * @param pos initial position of accumulation
     * @param dir direction of the
     */
    private void assembleAffectedPositions(Vector2 pos, Direction dir) {
        // first add this position to the appropriate set
        if (dir == Direction.NORTH || dir == Direction.SOUTH)
            affectedVerticals.add(pos.cpy());
        else
            // Horizontal direction
            affectedHorizonals.add(pos.cpy());

        // case: a player located on this position
        for (Player hitCandidate : players) {
            if (hitCandidate.getPositionCopy().equals(pos)) {
                registerPlayerHit(hitCandidate);
                return;
            }
        }

        // case: no wall between this position and the next
        if (CollisionHandler.canGo(pos, dir, board)) {
            VectorMovement.go(pos, dir);
            if (board.onBoard(pos))
                assembleAffectedPositions(pos, dir);
        }

    }

    private void registerPlayerHit(Player hitPlayer) {
        if (!hitPlayersMap.containsKey(hitPlayer))
            hitPlayersMap.put(hitPlayer, 1);
        else
            hitPlayersMap.put(hitPlayer, hitPlayersMap.get(hitPlayer) + 1);
    }

    /**
     * Maps all positions where a beam should start (from laser cannons only for now) to the direction
     * of that beam.
     *
     * Should be called in constructor
     *
     * Positions is where the beams starts
     * Direction is the direction of the beam
     * @return
     */
    private Map<Vector2, Direction> mapStartPositionsToBeamDirections() {
        Map<Vector2, Direction> mapping = new HashMap<>();

        for (Vector2 pos : board.getLaserCannons().keySet()) {
            LaserCannonTile tile = (LaserCannonTile) board.getLaserCannons().get(pos);
            Direction dir = tile.getDirections().get(0);    // laser cannons should only have one direction
            if (dir == null)
                throw new IllegalStateException(
                        "Laser cannon does not have a direction. Something is seriously messed up."
                );
            mapping.put(pos, dir);
        }
        return mapping;
    }
}
