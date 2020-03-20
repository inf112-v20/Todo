package inf112.core.movement.util;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.tile.CollidableTile;
import inf112.core.util.VectorMovement;
import java.util.List;

/**
 * A utility class that provides tools necessary to avoid player-player and player-wall collision.
 *
 * NB: This class does NOT have the responsibility of executing actual player movement. It should rather be utilized by
 * class(es) responsible for executing such movements.
 *
 * @author eskil, alvar
 */
public class CollisionHandler {
    private GameBoard board;
    private List<Player> players;

    public CollisionHandler(GameBoard gameBoard, List<Player> players) {
        this.board = gameBoard;
        this.players = players;
    }

    /**
     * Recursively collects all adjacent Players positioned at the same line in the given direction, including
     * the Player positioned at the initial Vector2.
     *
     * NB: this does NOT check for walls etc. separating the players. Two adjacent players separated by a wall will be
     * collected.
     *
     * @param position, a position COPY of the player initialising the move
     * @param direction of the planned Player movement
     * @param affectedPlayers, the accumulated collection of Players in reverse order
     */
    public void gatherAffectedPlayers(Vector2 position, Direction direction, List<Player> affectedPlayers) {
        for (Player player : players)
            if (player.getX() == position.x && player.getY() == position.y) {
                affectedPlayers.add(0, player);
                VectorMovement.go(position, direction);
                gatherAffectedPlayers(position, direction, affectedPlayers);
            }
    }

    /**
     * @param startPosition
     * @param direction of the suggested movement
     * @return false if the suggested movement meets a collidable tile, and true otherwise
     */
    public static boolean canGo(Vector2 startPosition, Direction direction, GameBoard gameBoard) {
        Vector2 endPosition = VectorMovement.generateNew(startPosition, direction);
        CollidableTile startTile = (CollidableTile) gameBoard.getCollidables().get(startPosition);
        CollidableTile endTile = (CollidableTile) gameBoard.getCollidables().get(endPosition);
        if(startTile != null)
            if(startTile.willCollide(startPosition, direction))
                return false;
        if(endTile != null)
            if(endTile.willCollide(startPosition, direction))
                return false;

        return true;
    }

    public boolean canGo(Vector2 startPosition, Direction direction) {
        return CollisionHandler.canGo(startPosition,direction, this.board);
    }
}
