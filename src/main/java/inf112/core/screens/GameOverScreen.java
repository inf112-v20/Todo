package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class GameOverScreen implements Screen {

    private IGameStateSwitcher gameStateSwitcher;
    private Image gameOver;
    private ImageButton playAgainButton, closeButton;
    private Sprite text, button;
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
        this.text = new Sprite(new Texture("img/gameOver.png"));
        this.gameOver = new Image(text);
        gameOver.setPosition(width/2 - text.getWidth()/2, height*0.7f);
        stage.addActor(gameOver);

        // play again button
        this.button = new Sprite(new Texture("buttons/playAgainButton.png"));
        this.playAgainButton = new ImageButton(new SpriteDrawable(button));
        playAgainButton.setPosition(width/2 - button.getWidth()/2, height*0.35f);
        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStateSwitcher.initMainMenu();
            }
        });
        stage.addActor(playAgainButton);

        // exit button
        this.button = new Sprite((new Texture("buttons/exitButton.png")));
        this.closeButton = new ImageButton(new SpriteDrawable(button));
        closeButton.setPosition(width/2-button.getWidth()/2, height*0.20f);
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
