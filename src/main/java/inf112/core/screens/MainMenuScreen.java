package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import inf112.core.util.AssMan;
import inf112.core.util.ButtonFactory;

public class MainMenuScreen implements Screen {

    private IGameStateSwitcher gameStateSwitcher;
    private Stage stage;

    public MainMenuScreen(IGameStateSwitcher gameStateSwitcher){
        this.gameStateSwitcher = gameStateSwitcher;
    }

    @Override
    public void show() {
        stage = new Stage();

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        TextButton play = ButtonFactory.createCustomButton("SinglePlayer", 8);
        play.setPosition(width/2 - play.getWidth()/2, height*0.6f);
        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStateSwitcher.initSelectScreen();
            }
        });
        stage.addActor(play);

        TextButton multiplayer = ButtonFactory.createCustomButton("Multiplayer", 8);
        multiplayer.setPosition(width/2 - play.getWidth()/2, height*0.4f);
        multiplayer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStateSwitcher.initMultiplayerPlayername();
            }
        });
        stage.addActor(multiplayer);

        TextButton exit = ButtonFactory.createCustomButton("Exit", 4);
        exit.setPosition(width/2-exit.getWidth()/2, height*0.2f);
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStateSwitcher.closeApplication();
            }
        });
        stage.addActor(exit);

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