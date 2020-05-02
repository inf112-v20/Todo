package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.core.input.InputBlocker;
import inf112.core.input.OrthographicCameraController;
import inf112.core.game.MainGame;
import inf112.core.player.Player;
import inf112.core.player.PlayerHandler;


public class GameScreen implements Screen {

    public static OrthographicCameraController cameraController;

    private static MainGame game;
    private static InputProcessor inputBlocker;
    private IGameStateSwitcher gameStateSwitcher;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private HUDScreen hudScreen;
    private Stage stage;

    public GameScreen(RoboRally gameStateSwitcher) {
        this.gameStateSwitcher = gameStateSwitcher;
        this.hudScreen = new HUDScreen(this);
        this.inputBlocker = new InputBlocker();
    }

    @Override
    public void show() {
        game = new MainGame();
        game.getBoard().instantiateMapRenderer();
        game.createDeck();
        game.getPlayerHandler().giveAllPlayersCards();

        mapRenderer = game.getBoard().getTiledMapRenderer();
        camera = game.getBoard().instantiateCamera();

        game.getPlayerHandler().setupPlayers(1);
        game.setActivePlayerById(1);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        cameraController = new OrthographicCameraController(game);
        inputMultiplexer.addProcessor(game.getDefaultInputProcessor());
        //CameraControlles should always be on index 0 of the multiplexer, as the player should always be able to move their camera
        inputMultiplexer.addProcessor(0, cameraController);
        Gdx.input.setInputProcessor(inputMultiplexer);

        //HUDoverlay screen
        //hudScreen.setMovementHandler(game.getMovementHandler());
        //hudScreen.createButtons();    //For manual testing purposes only atm
        //stage = hudScreen.getStage();
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
        //stage.draw();       // HUD
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
