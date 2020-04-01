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
        //TODO mangler kort funksjonalitet
        for(Player player : players) {
            ;
        }
        //Wait for all players to lay down their program
        /*
        //TODO metode som setter en timer på 30 sekunder etter første program er lagt ned
        while(!playerProgramsReady()){
            ;
        }
        */
        //Handle powerdown
        //TODO powerdown funksjonalitet

        for(int i = 0; i < 5; i++) {
            runPhases();
        }

        //Cleanup
        //TODO reset playerCards
    }

    private boolean playerProgramsReady() {
        for(Player player : players){
            if(!player.programReady)
                return false;
        }
        return true;
    }

    /**
     * Function for running the phases of a round.
     */
    private void runPhases() {
        /**
         * Phase1
         * Show next playerCard
         */
        //Todo phase1

        /**
         * Phase2
         * Move Robots
         */

        /**
         * Phase3
         * Move Pushers, Conveyors then Gears.
         */
        movementHandler.pushPlayerInDirection();
        movementHandler.runConveyors();
        movementHandler.gearsRotate();

        /**
         * Phase4
         * Fire all lasers
         */
        movementHandler.fireAllLasers();
        movementHandler.removeLasers();

        /**
         * Phase5
         * Register checkpoints, repairs
         */
    }

}
