package inf112.core.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import inf112.core.cards.Deck;
import inf112.core.cards.ProgramCard;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import inf112.core.screens.GameScreen;
import inf112.core.screens.userinterface.UserInterface;
import inf112.core.util.AssMan;

import java.util.ArrayList;
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
        GameScreen screen = (GameScreen) game.getGameScreen();
//        Stage stage = screen.getStage();
//        UserInterface ui = screen.getUi();//.initializeSelectionPhase(game.getDeck().getCards(9));

        //TODO mangler kort funksjonalitet
        for(Player player : players) {


            //screen.drawPlayerCondition(player);  EVT stage
            //screen.getStage().addActor();

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
