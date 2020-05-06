package inf112.core.screens.multiplayerscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.screens.IGameStateSwitcher;
import inf112.core.util.ButtonFactory;

public class MultiplayerScreenJoin implements Screen {

    private Stage stage;
    private IGameStateSwitcher gameStateSwitcher;
    private String PlayerName;

    public MultiplayerScreenJoin(IGameStateSwitcher gameStateSwitcher){
        this.gameStateSwitcher = gameStateSwitcher;
    }


    @Override
    public void show() {
        this.stage = new Stage();

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        TextButton join = ButtonFactory.createCustomButton("Join Game", 8);
        join.setPosition(width/2 - join.getWidth()/2, height*0.6f);
        join.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStateSwitcher.initMainGame();
            }
        });
        stage.addActor(join);


        TextButton host = ButtonFactory.createCustomButton("Host Game", 8);
        host.setPosition(width/2 - host.getWidth()/2, height*0.4f);
        host.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStateSwitcher.initMainGame();
            }
        });
        stage.addActor(host);




        TextButton back = ButtonFactory.createCustomButton("Back", 4);
        back.setPosition(width/2 - back.getWidth()/2, height*0.2f);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStateSwitcher.initMainGame();
            }
        });
        stage.addActor(back);

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

    }

    @Override
    public void dispose() {

    }
}
