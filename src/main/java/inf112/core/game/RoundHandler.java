package inf112.core.game;

import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.player.Player;
import java.util.List;

public class RoundHandler {
    MainGame game;
    GameBoard board;
    List<Player> players;

    /**
     * Class that handles a gameRound. Every round has several phases, and every phase has different actions.
     * @param game
     * @author Alvar
     */
    public RoundHandler(MainGame game, List<Player> players) {
        this.game = game;
        this.board = game.getBoard();
        this.players = players;
    }


}
