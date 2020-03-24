package inf112.core.game;

import inf112.core.board.GameBoard;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import inf112.core.tile.*;

import java.util.ArrayList;
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
    public void runConveyors(){
        //only conveyors with players on them need to move
        List<Player> playersOnJunction = new ArrayList<>();
        List<Player> playersOnConveyor = new ArrayList<>();
        for(Player player : players) {
            if(isOnJunction(player))
                playersOnJunction.add(player);
            else if(isOnConveyor(player))
                playersOnConveyor.add(player);
        }
        //JunctionTiles should move first
        conveyorMove(playersOnJunction);
        conveyorMove(playersOnConveyor);
    }

    private void conveyorMove(List<Player> players) {
        for(Player player : players) {
            MovementHandler movementHandler = game.getMovementHandler();
            MoverTile conveyor = (MoverTile) board.getConveyors().get(player.getPositionCopy());
            conveyor.moveConveyor(player, movementHandler);
            MoverTile next = (MoverTile) board.getConveyors().get(player.getPositionCopy());
            if (next != null)
                next.rotate(player);
        }
    }

    private List<MoverTile> findConveyors() {
        List<MoverTile> conveyors = new ArrayList<>();
        for(Player player : players) {
            if(isOnConveyor(player)) {
               conveyors.add((MoverTile) board.getConveyors().get(player.getPositionCopy()));
            }
        }
        return conveyors;
    }

    private boolean isOnConveyor(Player player) {
        return board.getConveyors().get(player.getPositionCopy()) != null;
    }
    private boolean isOnJunction(Player player) {
        MoverTile tile = (MoverTile) board.getConveyors().get(player.getPositionCopy());
        return tile != null && tile instanceof JunctionTile;
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

    private boolean onWrench(Player player) { return board.getWrenches().get(player.getPositionCopy()) != null; }

    public void wrenchesRepair(){
        for (Player player : players){
            if (onWrench(player)){
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

}
