package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import inf112.core.input.OrthographicCameraController;
import inf112.core.game.MainGame;
import inf112.core.player.Player;
import inf112.core.util.AssMan;
import inf112.core.util.ButtonFactory;


public class GameScreen implements Screen {

    private static MainGame game;
    private IGameStateSwitcher gameStateSwitcher;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Stage stage;

    public GameScreen(RoboRally gameStateSwitcher) {
        this.gameStateSwitcher = gameStateSwitcher;
    }

    @Override
    public void show() {
        stage = new Stage();
        game = new MainGame();
        game.getBoard().instantiateMapRenderer();

        mapRenderer = game.getBoard().getTiledMapRenderer();
        camera = game.getBoard().instantiateCamera();

        game.createPlayers(3);
        game.setActivePlayerById(1);

        ImageButton button1 = ButtonFactory.createImageButton(AssMan.manager.get(AssMan.FORWARD1), -50);
        button1.setPosition(40,-50);
        stage.addActor(button1);

        ImageButton button2 = ButtonFactory.createImageButton(AssMan.manager.get(AssMan.FORWARD1), -50);
        button2.setPosition(150,-50);
        stage.addActor(button2);

        ImageButton button3 = ButtonFactory.createImageButton(AssMan.manager.get(AssMan.FORWARD1), -50);
        button3.setPosition(260,-50);
        stage.addActor(button3);

        ImageButton button4 = ButtonFactory.createImageButton(AssMan.manager.get(AssMan.FORWARD1), -50);
        button4.setPosition(370,-50);
        stage.addActor(button4);

        ImageButton button5 = ButtonFactory.createImageButton(AssMan.manager.get(AssMan.FORWARD1), -50);
        button5.setPosition(480,-50);
        stage.addActor(button5);


        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        OrthographicCameraController cameraController = new OrthographicCameraController(game);
        inputMultiplexer.addProcessor(game.getDefaultInputProcessor());
        inputMultiplexer.addProcessor(cameraController);
        Gdx.input.setInputProcessor(inputMultiplexer);

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
