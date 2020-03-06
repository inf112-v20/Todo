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
import inf112.core.roborally.RoboRally;

public class MainMenuScreen implements Screen {

    private RoboRally game;
    private float width;
    private float height;


    private ImageButton playButton;
    private ImageButton exitButton;
    private Stage stage;

    public MainMenuScreen(RoboRally game){
        this.game = game;
        this.height = 720;
        this.width = 1280;
    }

    @Override
    public void show() {
        stage = new Stage();



        Sprite button = new Sprite(new Texture("img/playButton.png"));
        playButton = new ImageButton(new SpriteDrawable(button));
        playButton.setPosition(1280/2 - 120/2, 720/2);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new GameScreen());
            }
        });
        stage.addActor(playButton);

        button = new Sprite((new Texture("img/exitButton.png")));
        exitButton = new ImageButton(new SpriteDrawable(button));
        exitButton.setPosition(1280/2-120/2, 720/6);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
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
    public void resize(int i, int i1) {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}