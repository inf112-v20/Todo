package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class MainMenuScreen implements Screen {

    private IGameStateSwitcher gameStateSwitcher;
    private ImageButton playButton, exitButton;
    private Stage stage;

    public MainMenuScreen(IGameStateSwitcher gameStateSwitcher){
        this.gameStateSwitcher = gameStateSwitcher;
    }

    @Override
    public void show() {
        stage = new Stage();

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        Sprite button = new Sprite(new Texture("img/playButton.png"));
        playButton = new ImageButton(new SpriteDrawable(button));
        playButton.setPosition(width/2 - button.getWidth()/2, height/2);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStateSwitcher.initMainGame();
            }
        });
        stage.addActor(playButton);

        button = new Sprite((new Texture("img/exitButton.png")));
        exitButton = new ImageButton(new SpriteDrawable(button));
        exitButton.setPosition(width/2-button.getWidth()/2, height/6);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStateSwitcher.closeApplication();
            }
        });
        stage.addActor(exitButton);

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