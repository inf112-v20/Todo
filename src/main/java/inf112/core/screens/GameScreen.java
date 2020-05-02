package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.core.input.OrthographicCameraController;
import inf112.core.game.MainGame;
import inf112.core.player.Player;
import inf112.core.screens.userinterface.UserInterface;


public class GameScreen implements Screen {

    private static MainGame game;
    private IGameStateSwitcher gameStateSwitcher;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Stage stage;
    private UserInterface ui;

    public GameScreen(RoboRally gameStateSwitcher) {
        this.gameStateSwitcher = gameStateSwitcher;
    }


    @Override
    public void show() {
        this.game = new MainGame();
        this.stage = new Stage();

        this.ui = new UserInterface(this);
        stage.addActor(ui.getTable());
        ui.getTable().setColor(Color.CHARTREUSE);

        game.getBoard().instantiateMapRenderer();

        mapRenderer = game.getBoard().getTiledMapRenderer();
        camera = game.getBoard().instantiateCamera();

        game.getPlayerHandler().createPlayers(3);
        game.setActivePlayerById(1);
        game.setGameScreen(this);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        OrthographicCameraController cameraController = new OrthographicCameraController(game);
        inputMultiplexer.addProcessor(game.getDefaultInputProcessor());
        inputMultiplexer.addProcessor(cameraController);
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

  //      ui.initializeSelectionPhase(game.getDeck().getCards(9));

    }

    public UserInterface getUi(){
        return ui;
    }

    public Stage getStage(){
        return stage;
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

        if(game.getBoard().playerCamera)
            game.getBoard().centerCameraOnPlayer(game.getActivePlayer());

        game.getBoard().render();

        if (game.hasWon()) {
            Player.resetPlayerCount();
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
