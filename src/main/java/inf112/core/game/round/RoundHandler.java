package inf112.core.game.round;

import com.badlogic.gdx.utils.Timer;
import inf112.core.game.GameRule;
import inf112.core.game.MainGame;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import inf112.core.player.PlayerAI;
import inf112.core.player.PlayerHandler;

import java.util.List;

public class RoundHandler {
    private float totalDelay = 0f;

    private MainGame game;
    private PlayerHandler playerHandler;
    private MovementHandler movementHandler;
    private List<Player> players;

    public RoundHandler(MainGame game) {
        this.game = game;
        this.playerHandler = game.getPlayerHandler();
        this.movementHandler = game.getMovementHandler();
        this.players = game.getPlayers();
    }

    /**
     * main function for starting a new round
     */
    public void instantiateNextRound() {
        //ProgramSheets are cleared
        playerHandler.clearAllProgramsheets();
        //All players receive a new set of cards

        playerHandler.giveAllPlayersCards();
        playerHandler.makeAIPrograms();
        for (Player player : playerHandler.getPlayers()) {
            if (!(player instanceof PlayerAI)) {
                player.setRandomProgram();
            }
        }
        if(!playerHandler.areProgramsReady())
            throw new IllegalStateException("All programs must be ready at this stage");

        //Remove player Control

        //Generate round
        Round round = GameRule.generateDefaultRound(playerHandler, movementHandler);
        //1 second delay before rounds start
        totalDelay += 1f;
        //Repeat base round for the amount of rounds
        for(int i = 0; i < round.getAmountOfRounds(); i++) {
            round.roundStart(totalDelay);
            //delay is incremented by runtime of round
            totalDelay += round.getRoundRuntime();
            //1 second delay between each round
            totalDelay += 1f;
        }

    }

    /**
     * Function for running the phases of a round.
     */
    private void runPhases(int round, float delay) {
        MovementHandler movementHandler = game.getMovementHandler();
        /**
         * Phase1
         * Show next playerCard
         */
        playerHandler.nextCard();

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
        }, (float) (delay += 1));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                movementHandler.runConveyors();
            }
        }, (float) (delay += 1));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                movementHandler.gearsRotate();
            }
        }, (float) (delay++));

        /**
         * Phase4
         * Fire all lasers
         */
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                movementHandler.fireAllLasers();
            }
        }, (float) (delay += 1));
        /**
         * Phase5
         * Register checkpoints, repairs
         */
        for(Player player : players) {
            movementHandler.handleFlagVisitation(player);
        }
        movementHandler.wrenchesRepair();
    }

    public void resetDelay() {
        this.totalDelay = 0f;
    }

}
