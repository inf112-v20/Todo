package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.core.camera.OrthographicCameraController;
import inf112.core.game.MainGame;
import inf112.core.player.Player;
import inf112.core.util.AssMan;


public class GameScreen implements Screen {

    private static MainGame game;
    private IGameStateSwitcher gameStateSwitcher;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private HUDScreen hudScreen;
    private Stage stage;

    public GameScreen(RoboRally gameStateSwitcher) {
        this.gameStateSwitcher = gameStateSwitcher;
        this.hudScreen = new HUDScreen(this);
    }

    @Override
    public void show() {
        game = new MainGame();
        game.getBoard().instantiateMapRenderer();
        game.createDeck();
        game.givePlayerCards();

        mapRenderer = game.getBoard().getTiledMapRenderer();
        camera = game.getBoard().instantiateCamera();

        game.createPlayers(3);
        game.setActivePlayerById(1);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        OrthographicCameraController cameraController = new OrthographicCameraController(game);
        inputMultiplexer.addProcessor(game.getDefaultInputProcessor());
        inputMultiplexer.addProcessor(cameraController);
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

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        game.getBoard().render();
        if (game.hasWon()) {
            Player.resetPlayerCount();
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
