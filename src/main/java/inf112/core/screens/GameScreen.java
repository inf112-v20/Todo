package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.core.game.MainGame;
import inf112.core.player.Player;


public class GameScreen implements Screen {

    MainGame game;
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

        mapRenderer = game.getBoard().instantiateMapRenderer();
        camera = game.getBoard().instantiateCamera();

        game.createPlayers(3);
        game.setActivePlayerById(1);


        Gdx.input.setInputProcessor(game.getMovementHandler());

        stage = hudScreen.getStage();       // HUD

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
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        mapRenderer.setView(camera);
        mapRenderer.render();
        if (game.hasWon()) {
            Player.resetPlayerCount();
            gameStateSwitcher.initGameOver();
        }
        stage.draw();       // HUD
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
