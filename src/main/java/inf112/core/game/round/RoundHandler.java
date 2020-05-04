package inf112.core.game.round;

import com.badlogic.gdx.utils.Timer;
import inf112.core.game.GameRule;
import inf112.core.game.MainGame;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import inf112.core.player.PlayerAI;
import inf112.core.player.PlayerHandler;
import inf112.core.screens.GameScreen;
import org.lwjgl.Sys;

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
        resetDelay();

        GameScreen screen = (GameScreen) game.getGameScreen();


        //ProgramSheets are cleared
        //playerHandler.clearAllProgramsheets();
        //All players receive a new set of cards
        playerHandler.giveAllPlayersCards();

        playerHandler.makeAIPrograms();
        for (Player player : playerHandler.getPlayers()) {
            if (!(player instanceof PlayerAI)) {
                System.out.println(player.getId());
                //player.setRandomProgram();
                continue;
            }
        }
        if(!playerHandler.areProgramsReady()){
            throw new IllegalStateException("All programs must be ready at this stage");
        }


        //Remove player Control
        GameScreen.blockControls();
        System.out.println("Controls locked");
        //Generate round
        Round round = GameRule.generateDefaultRound(playerHandler, movementHandler);
        //1 second delay before rounds start
        totalDelay += 1f;
        //Repeat base round for the amount of rounds
        for(int i = 0; i < round.getAmountOfRounds(); i++) {
            screen.getUi().drawPlayerCondition(game.getActivePlayer());
            round.roundStart(totalDelay);
            //delay is incremented by runtime of round
            totalDelay += round.getRoundRuntime();
            //1 second delay between each round
            totalDelay += 1f;
        }
        //extra margin for last round to end
        totalDelay += 1f;
        //Schedule unblockControlls to after last round is completed
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                System.out.println("Controls unblocked");
                GameScreen.unblockControls();
            }
        }, totalDelay);

    }

    public void resetDelay() {
        this.totalDelay = 0f;
    }

}
