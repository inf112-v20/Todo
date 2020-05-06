package inf112.core.screens.multiplayerscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import inf112.core.screens.IGameStateSwitcher;
import inf112.core.util.AssMan;
import inf112.core.util.ButtonFactory;

import static com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.Color;

public class MultiplayerScreenPlayername implements Screen {

    static String name;
    private Stage stage;
    private IGameStateSwitcher gameStateSwitcher;
    private boolean clicked;

    public MultiplayerScreenPlayername(IGameStateSwitcher gameStateSwitcher){
        this.gameStateSwitcher = gameStateSwitcher;
    }


    @Override
    public void show() {
        this.stage = new Stage();

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        BitmapFont font = AssMan.manager.get(AssMan.CHINTZY_FONT.fileName);
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = font;
        font.getData().setScale(2);
        style.fontColor = com.badlogic.gdx.graphics.Color.BLUE;

        TextField text = new TextField("", style);
        text.setText(name == null ? "Enter name here" : name);
        clicked = name != null;
        text.setSize(1000,200);
        text.setMaxLength(20);
        text.setPosition(width/2 - text.getWidth()/2, height*0.6f);
        text.setAlignment(Align.center);
        text.setMaxLength(20);
        text.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!clicked) text.setText("");
                clicked = true;
            }
        });

        stage.addActor(text);


        TextButton confirm = ButtonFactory.createCustomButton("Confirm name", 6);
        confirm.setPosition(width/2 - confirm.getWidth()/2, height*0.4f);
        confirm.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!clicked || (text.getText().length() < 3)) {
                    return;
                }
                name = text.getText();
                gameStateSwitcher.initMultiplayerSettings();
            }
        });
        stage.addActor(confirm);


        TextButton back = ButtonFactory.createCustomButton("Back", 4);
        back.setPosition(width/2 - back.getWidth()/2, height*0.2f);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStateSwitcher.initMainMenu();
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
