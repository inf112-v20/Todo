package inf112.core.game;

import inf112.core.board.GameBoard;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import inf112.core.tile.GearTile;
import inf112.core.tile.MoverTile;
import inf112.core.tile.Rotation;

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

    /**
     * Checks if a player is standing on a conveyorTile, and moves said player
     */
    public void conveyorMove(){
        //only conveyors with players on them need to move
        for(Player player : players) {
            if(isOnConveyor(player)) {
                MovementHandler movementHandler = game.getMovementHandler();
                MoverTile conveyor = (MoverTile) board.getConveyors().get(player.getPositionCopy());
                conveyor.moveConveyor(player, movementHandler);
                MoverTile next = (MoverTile) board.getConveyors().get(player.getPositionCopy());
                if(next != null)
                    next.rotate(player);
            }
        }
    }

    private boolean isOnConveyor(Player player) {
        return board.getConveyors().get(player.getPositionCopy()) != null;
    }

    public void gearsRotate(){
        for (Player player : players){
            if (onGear(player)){
                GearTile gear = (GearTile) board.getGears().get(player.getPositionCopy());
                Rotation rotation = gear.getRotation();
                if (rotation == Rotation.LEFT) player.rotateLeft();
                else player.rotateRight();
            }
        }
    }

    private boolean onGear(Player player) { return board.getGears().get(player.getPositionCopy()) != null; }

}
