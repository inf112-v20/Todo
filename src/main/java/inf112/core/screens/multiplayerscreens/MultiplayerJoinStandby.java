package inf112.core.screens.multiplayerscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.screens.IGameStateSwitcher;
import inf112.core.util.AssMan;
import inf112.core.util.ButtonFactory;

public class MultiplayerJoinStandby implements Screen {

    private float width;
    private float height;

    private final int MAX_NAMES = 8;

    private Label.LabelStyle style;
    private BitmapFont font;

    private Stage stage;
    private IGameStateSwitcher gameStateSwitcher;
    private Label[] names;

    public MultiplayerJoinStandby(IGameStateSwitcher gameStateSwitcher){
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
        this.stage = new Stage();

        createLabels();

        Label label = new Label("Waiting for host to start game" , style);
        label.setPosition(width/2 - label.getWidth()/2, height*0.9f);
        stage.addActor(label);


        TextButton back = ButtonFactory.createCustomButton("Back", 4);
        back.setPosition(width/4 - back.getWidth()/2, height*0.10f);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                gameStateSwitcher.initMultiplayerSettings();
            }
        });
        stage.addActor(back);


        Gdx.input.setInputProcessor(stage);

    }

    private void createLabels(){
        for(int i = 0; i < MAX_NAMES; i++){
            Label player = new Label("Waiting for player " + (i+1), style);
            player.setPosition(width/2 - player.getWidth()/2, height*(0.8f - i * 0.08f));
            stage.addActor(player);
        }
    }

    private void setLabel(int x, String string){
        names[x].setText(string);
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
