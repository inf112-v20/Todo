package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.cards.Card;
import inf112.core.cards.Deck;
import inf112.core.cards.ProgramCard;
import inf112.core.input.InputBlocker;
import inf112.core.input.OrthographicCameraController;
import inf112.core.game.MainGame;
import inf112.core.player.Player;
import inf112.core.player.PlayerHandler;
import inf112.core.screens.userinterface.UserInterface;
import inf112.core.util.ButtonFactory;

import java.util.List;

import static inf112.core.game.MainGame.playerHandler;


public class GameScreen implements Screen {

    public static OrthographicCameraController cameraController;

    private static MainGame game;
    private static InputProcessor inputBlocker;
    private static UserInterface ui;
    private static Stage stage;
    private IGameStateSwitcher gameStateSwitcher;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    public GameScreen(RoboRally gameStateSwitcher) {
        this.gameStateSwitcher = gameStateSwitcher;
        this.inputBlocker = new InputBlocker();
    }


    @Override
    public void show() {
        this.game = new MainGame();
        this.stage = new Stage();

        this.ui = new UserInterface(this, game);
        stage.addActor(ui.getTable());

        game.getBoard().instantiateMapRenderer();
        mapRenderer = game.getBoard().getTiledMapRenderer();
        camera = game.getBoard().instantiateCamera();

        game.getPlayerHandler().setupPlayers(1);
        game.setActivePlayerById(1);
        game.getPlayerHandler().giveAllPlayersCards();
        game.setGameScreen(this);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        cameraController = new OrthographicCameraController(game);
        inputMultiplexer.addProcessor(game.getDefaultInputProcessor());
        inputMultiplexer.addProcessor(cameraController);
        inputMultiplexer.addProcessor(stage);
        //CameraControlles should always be on index 0 of the multiplexer, as the player should always be able to move their camera
        inputMultiplexer.addProcessor(0, cameraController);
        Gdx.input.setInputProcessor(inputMultiplexer);

        // Creates button which starts the rounds once cards have been selected
        createLockSelectionButton();
    }

    public UserInterface getUi(){
        return ui;
    }


    public Stage getStage(){
        return stage;
    }


    public static void createLockSelectionButton(){

        ui.showSelectionCards(game.getActivePlayer().getProgramSheet().getHand());

        TextButton button = ButtonFactory.createCustomButton("Confirm", 3);
        button.setPosition(1100, 550);
        stage.addActor(button);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                List<ProgramCard> selected = ui.getSelectionButtons().lockSelection();
                if (selected != null){
                    for(ProgramCard card : selected){
                        game.getActivePlayer().addToProgramSheet(card);
                        //System.out.println(card.getName());
                    }
                    if(game.getActivePlayer().getProgramSheet().isFull()) { game.getActivePlayer().programReady = true; }
                    else { throw new IllegalStateException(); }


                    ui.drawPlayerCondition(game.getActivePlayer());
                    game.getRoundHandler().instantiateNextRound();
                    button.remove();

                }
            }
        });
    }



    @Override
    public void dispose() {
        mapRenderer.dispose();
        game.dispose();
    }

    public static void blockControls() {
        //Blocker is inserted into index 1, so that camera controls still work
        ((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(1, inputBlocker);
    }

    public static void unblockControls() {
        ((InputMultiplexer) Gdx.input.getInputProcessor()).removeProcessor(inputBlocker);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        if(game.getBoard().playerCamera)
            game.getBoard().centerCameraOnPlayer(game.getActivePlayer());

        game.getBoard().render();

        if (game.hasWon()) {
            PlayerHandler.playerCount = 0;
            gameStateSwitcher.initGameOver();
        }
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //game.getBoard().resize(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    public static MainGame getGame() {
        return game;
    }

}
