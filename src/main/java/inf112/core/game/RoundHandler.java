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
            game.getGameScreen().render(0);
            int finalRound = round;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    runPhases(finalRound);
                }
            }, (float) 0.5);
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
        game.getGameScreen().render(0);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                movementHandler.pushPlayerInDirection(round);
            }
        }, (float) 0.4);
        game.getGameScreen().render(0);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                movementHandler.runConveyors();
            }
        }, (float) 0.4);
        game.getGameScreen().render(0);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                movementHandler.gearsRotate();
            }
        }, (float) 0.4);
        game.getGameScreen().render(0);

        /**
         * Phase4
         * Fire all lasers
         */
        movementHandler.fireAllLasers();
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
