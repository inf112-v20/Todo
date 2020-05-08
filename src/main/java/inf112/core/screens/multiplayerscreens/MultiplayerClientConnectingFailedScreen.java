package inf112.core.screens.multiplayerscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import inf112.core.screens.IGameStateSwitcher;
import inf112.core.util.AssMan;
import inf112.core.util.ButtonFactory;

public class MultiplayerClientConnectingFailedScreen implements Screen {

    private float width;
    private float height;

    private Label.LabelStyle style;
    private BitmapFont font;

    private Stage stage;
    private IGameStateSwitcher gameStateSwitcher;

    private Label connectingFailedLabel;
    private TextButton backButton;

    public MultiplayerClientConnectingFailedScreen(IGameStateSwitcher gameStateSwitcher){
        this.gameStateSwitcher = gameStateSwitcher;

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        font = AssMan.manager.get(AssMan.CHINTZY_FONT.fileName);
        font.getData().setScale(2);

        style = new Label.LabelStyle();
        style.font = font;
        style.fontColor = com.badlogic.gdx.graphics.Color.BLUE;
    }


    @Override
    public void show() {
        stage = new Stage();

        this.connectingFailedLabel = new Label("Connection to server failed." , style);
        connectingFailedLabel.setPosition(width/2 - connectingFailedLabel.getWidth()/2, height*0.5f);
        stage.addActor(connectingFailedLabel);

        this.backButton = ButtonFactory.createCustomButton("Back", 4);
        backButton.setPosition(width/2 - backButton.getWidth()/2, height*0.2f);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                gameStateSwitcher.initMultiplayerClientPromptIP();
            }
        });
        stage.addActor(backButton);

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
        stage.dispose();
    }
}
