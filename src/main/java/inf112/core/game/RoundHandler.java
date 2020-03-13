package inf112.core.game;

import inf112.core.board.GameBoard;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import inf112.core.tile.MoverTile;

import java.util.List;

public class RoundHandler {
    MainGame game;
    GameBoard board;
    List<Player> players;

    /**
     * Class that handles a gameRound. Every round has several phases, and every phase has different actions.
     * @param game
     */
    public RoundHandler(MainGame game, List<Player> players) {
        this.game = game;
        this.board = game.getBoard();
        this.players = players;
    }

    /**
     * Checks if a player is standing on a conveyorTile, and moves said player
     */
    public void conveyorMove(){
        //only conveyors with players on them need to move
        for(Player player : players) {
            if(conveyorOnTile(player)) {
                MoverTile conveyor = (MoverTile) board.getConveyors().get(player.getPositionCopy());
                MovementHandler movementHandler = game.getMovementHandler();
                switch(conveyor.getSpeed()){
                    case 1:
                        conveyor.moveConveyor(player, movementHandler);
                        break;
                    case 2:
                        conveyor.moveConveyor(player, movementHandler);
                        MoverTile next = (MoverTile) board.getConveyors().get(player.getPositionCopy());
                        if(next == null) {
                            //there is no new conveyorTile on next position, player wil carry forward in the same direction as the last move
                            movementHandler.attemptToMove(player, player.getLastDir());
                        } else {
                            //there is a new conveyor on the next position, player will be moved by next conveyor
                            next.moveConveyor(player, movementHandler);
                        }
                        break;
                }
            }
        }
    }

    private boolean conveyorOnTile(Player player) {
        if(board.getConveyors().get(player.getPositionCopy()) != null)
            return true;
        return false;
    }

}
