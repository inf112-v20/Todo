package inf112.core.game;

import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import java.util.List;

public class RoundHandler {
    MainGame game;
    List<Player> players;
    MovementHandler movementHandler;

    /**
     * Class that handles a gameRound. Every round has several phases, and every phase has different actions.
     * @param game
     * @author Alvar
     */
    public RoundHandler(MainGame game) {
        this.game = game;
        this.players = game.getPlayers();
        this.movementHandler = game.getMovementHandler();
    }

    /**
     * main function for starting a new round
     */
    public void instantiateNewRound() {
        //Deal out new cards for the players
        for(Player player : players) {
            ;
        }
        //Wait for all players to lay down their program
        /*
        while(!playerProgramsReady()){
            ;
        }
        */
        //Handle powerdown

    }

    private boolean playerProgramsReady() {
        for(Player player : players){
            if(!player.programReady)
                return false;
        }
        return true;
    }

    private void runPhaseOne() {
        movementHandler.runConveyors();
    }

}
