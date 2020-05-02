package inf112.core.game.round;

import com.badlogic.gdx.utils.Timer;
import inf112.core.cards.ProgramCard;
import inf112.core.game.MainGame;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import inf112.core.player.PlayerAI;
import inf112.core.player.PlayerHandler;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RoundHandler {
    public static final int ROUND_COUNT = 5;
    private float totalDelay = (float) 0;

    MainGame game;
    PlayerHandler playerHandler;
    List<Player> players;

    /**
     * Class that handles a gameRound. Every round has several phases, and every phase has different actions.
     * @param game
     * @author Alvar
     */
    public RoundHandler(MainGame game) {
        this.game = game;
        this.playerHandler = game.getPlayerHandler();
        this.players = game.getPlayers();
    }

    /**
     * main function for starting a new round
     */
    public void instantiateNextRoundPhase() {

        /**
         * Card phase
         */
        //ProgramSheets are cleared
        playerHandler.clearAllProgramsheets();
        //All players receive a new set of cards
        playerHandler.giveAllPlayersCards();
        //Wait for all players to lay down their program

        playerHandler.makeAIPrograms();
        for(Player player : playerHandler.getPlayers()) {
            if(!(player instanceof PlayerAI)) {
                player.setRandomProgram();
            }
            System.out.println(player.programReady);
        }

        if(!playerHandler.areProgramsReady())
            throw new AssertionError("programs should be ready");
        /*
        //TODO metode som setter en timer på 30 sekunder etter første program er lagt ned
        while(!playerProgramsReady()){
            ;
        }
        */
        //Handle powerdown
        //TODO powerdown funksjonalitet
        for(int round = 1; round <= ROUND_COUNT; round++) {
            System.out.println("initiate Round " + round);
            runPhases(round, totalDelay);
        }
        totalDelay = 0;
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
        List<Pair<Player, ProgramCard>> moves = getSortedMoves();
        for(Pair<Player, ProgramCard> move : moves) {
            float
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    movementHandler.cardMovement(move.getValue0(), move.getValue1());
                }
            }, );
        }
        game.getBoard().playerCamera = false;


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

}
