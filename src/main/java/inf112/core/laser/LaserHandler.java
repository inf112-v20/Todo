package inf112.core.laser;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.board.MapLayer;
import inf112.core.movement.util.CollisionHandler;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.tile.ITile;
import inf112.core.tile.LaserCannonTile;
import inf112.core.util.LayerOperation;
import inf112.core.util.VectorMovement;

import java.util.*;

public class LaserHandler {

    private GameBoard board;
    private TiledMapTileLayer laserLayer;   // refactor
    private Map<Vector2, ITile> laserCannonMap, collidablesMap;
    private Map<Vector2, Direction> cannonDirectionMap;
    private List<Player> players;
    private Map<Player, Integer> hitPlayers;    // hit players goes here, val indicates number of beam hits
    private Set<Vector2> affectedVerticals, affectedHorizonals, affectedCrosses;

    public LaserHandler(GameBoard board, List<Player> players) {
        this.board = board;
        this.laserLayer = board.getLayer(MapLayer.LASER_LAYER);    // refactor
        this.laserCannonMap = board.getLaserCannons();
        this.collidablesMap = board.getCollidables();
        this.players = players;

        this.affectedVerticals = new HashSet<>();
        this.affectedHorizonals = new HashSet<>();
        this.affectedCrosses = new HashSet<>();

        this.cannonDirectionMap = new HashMap<>();
        this.mapStartPositionsToBeamDirections(cannonDirectionMap);
    }

    public void fireLasersVisually() {    // refactor
        return;
    }

    public void dealDamageToAffectedPlayers() {    // refactor
        return;
    }

    public void disableLasersVisually() {    // refactor
        LayerOperation.clear(laserLayer);
    }

    public Set<Vector2> gatherAllVerticalLaserPositions() {
        affectedVerticals.clear();
        // TODO
        return affectedVerticals;
    }

    public Set<Vector2> gatherAllHorizontalLaserPositions() {
        affectedHorizonals.clear();
        // TODO
        return affectedHorizonals;
    }

    public Set<Vector2> gatherAllCrossedLaserPositions() {
        affectedCrosses.clear();
        // TODO
        return affectedCrosses;
    }


    /**
     * Maps all positions where a beam should start (from laser cannons only for now) to the direction
     * of that beam.
     *
     * Positions is where the beams starts
     * Direction is the direction of the beam
     * @return
     */
    private void mapStartPositionsToBeamDirections(Map<Vector2, Direction> mapping) {
        for (Vector2 pos : laserCannonMap.keySet()) {
            LaserCannonTile tile = (LaserCannonTile) laserCannonMap.get(pos);
            Direction dir = tile.getDirections().get(0);    // laser cannons should only have one direction
            if (dir == null)
                throw new IllegalStateException(
                        "Laser cannon does not have a direction. Something is seriously messed up."
                );

            mapping.put(pos, dir);
        }
    }

    /**
     * Recursively accumulates the positions a wall laser beam sweeps over.
     * Stops once a collidable tile or a Player is hit.
     *
     * WARNING: Position state will be changed
     */
    private void accumulatePositionsFromWallCannon(Vector2 pos, Direction dir) {
        // case: a player located on this position
        for (Player hitCandidate : players) {
            if (hitCandidate.getPositionCopy().equals(pos)) {
                registerPlayerHit(hitCandidate);
                return;
            }
        }

        // this position is clear. Add to the appropriate set
        if (dir == Direction.NORTH || dir == Direction.SOUTH)
            affectedVerticals.add(pos);
        else
            affectedHorizonals.add(pos);

        // case: no wall between this position and the next
        if (CollisionHandler.canGo(pos, dir, board)) {
            VectorMovement.go(pos, dir);
            accumulatePositionsFromWallCannon(pos, dir);
        }

    }

    private void registerPlayerHit(Player hitPlayer) {
        if (!hitPlayers.containsKey(hitPlayer))
            hitPlayers.put(hitPlayer, 1);
        else
            hitPlayers.put(hitPlayer, hitPlayers.get(hitPlayer) + 1);
    }



}
