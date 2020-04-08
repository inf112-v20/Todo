package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.util.AssMan;
import inf112.core.util.ButtonFactory;

public class GameOverScreen implements Screen {

    private IGameStateSwitcher gameStateSwitcher;
    private Image gameOver;
    private TextButton playAgainButton, closeButton;
    private Sprite gameOverText;
    private Stage stage;

    public GameOverScreen(RoboRally gameStateSwitcher) {
        this.gameStateSwitcher = gameStateSwitcher;
    }

    @Override
    public void show() {
        this.stage = new Stage();

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        // game over text
        this.gameOverText = new Sprite(AssMan.manager.get(AssMan.GAME_OVER_TEXT));
        this.gameOver = new Image(gameOverText);
        gameOver.scaleBy(2);
        gameOver.setPosition(width/4 - gameOverText.getWidth()/4, height*0.7f);
        stage.addActor(gameOver);


        this.playAgainButton = ButtonFactory.createCustomButton("Play Again", 3);
        playAgainButton.setPosition(width/2-playAgainButton.getWidth()/2, height*0.3f);
        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStateSwitcher.initMainMenu();
            }
        });
        stage.addActor(playAgainButton);

        // exit button
        this.closeButton = ButtonFactory.createCustomButton("Exit Game", 3);
        closeButton.setPosition(width/2-closeButton.getWidth()/2, height*0.20f);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStateSwitcher.closeApplication();
            }
        });
        stage.addActor(closeButton);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
