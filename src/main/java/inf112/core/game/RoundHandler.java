package inf112.core.game;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Player;
import inf112.core.tile.ITile;

import java.util.List;
import java.util.NoSuchElementException;

public class RoundHandler {
    MainGame game;
    GameBoard board;
    List<Player> players;

    /**
     * Class that handles a gameRound. Every round has several phases, and every phase has different actions.
     * @param game
     * @param players
     */
    public RoundHandler(MainGame game, List<Player> players) {
        this.game = game;
        this.board = game.getBoard();
    }

    /**
     * moves all conveyors
     */
    public void conveyorMove(){
        for(ITile conveyor : board.getConveyors().values()) {
            if(hasPlayer(conveyor.getPos())) {
            }
        }
    }

    private boolean hasPlayer(Vector2 pos) {
        for(Player player : players) {
            if(player.getPositionCopy().equals(pos))
                return true;
        }
        return false;
    }

    private Player getPlayer(Vector2 pos) {
        for(Player player : players) {
            if(player.getPositionCopy().equals(pos))
                return player;
        }
        throw new NoSuchElementException("No player at given position, make sure to check with hasPlayer before using this function");
    }
}
