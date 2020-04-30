package inf112.core.game;

import com.badlogic.gdx.utils.Timer;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;

import java.util.List;

public class RoundHandler {
    MainGame game;
    List<Player> players;

    /**
     * Class that handles a gameRound. Every round has several phases, and every phase has different actions.
     * @param game
     * @author Alvar
     */
    public RoundHandler(MainGame game) {
        this.game = game;
        this.players = game.getPlayers();
    }

    /**
     * main function for starting a new round
     */
    public void instantiateNextRoundPhase() {
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

        for(int round = 1; round <= 5; round++) {
            runPhases(round);
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
    private void runPhases(int round) {
        MovementHandler movementHandler = game.getMovementHandler();
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
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                movementHandler.pushPlayerInDirection(round);
            }
        }, (float) (round - 0.8));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                movementHandler.runConveyors();
            }
        }, (float)(round - 0.7));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                movementHandler.gearsRotate();
            }
        }, (float) (round - 0.4));

        /**
         * Phase4
         * Fire all lasers
         */
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                movementHandler.fireAllLasers();
            }
        }, (float) (round - 0.3));
        /**
         * Phase5
         * Register checkpoints, repairs
         */
        for(Player player : players) {
            movementHandler.handleFlagVisitation(player);
        }
        movementHandler.wrenchesRepair();
    }

}
