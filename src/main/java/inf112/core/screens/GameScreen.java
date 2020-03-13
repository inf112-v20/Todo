package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.core.game.MainGame;


public class GameScreen implements Screen {

    MainGame game;
    private IGameStateSwitcher gameStateSwitcher;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    public GameScreen(RoboRally gameStateSwitcher) {
        this.gameStateSwitcher = gameStateSwitcher;
    }

    @Override
    public void show() {

        game = new MainGame();

        mapRenderer = game.getBoard().instantiateMapRenderer();
        camera = game.getBoard().instantiateCamera();

        game.createPlayers(2);
        game.setActivePlayerById(1);

        Gdx.input.setInputProcessor(game.getMovementHandler());
    }

    @Override
    public void dispose() {
        mapRenderer.dispose();
        game.dispose();
    }

    @Override
    public void hide() {
        dispose();         // temporary solution
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        mapRenderer.setView(camera);
        mapRenderer.render();
        if (game.hasWon()) {
            gameStateSwitcher.initGameOver();
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
