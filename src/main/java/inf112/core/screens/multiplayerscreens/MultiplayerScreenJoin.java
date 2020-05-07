package inf112.core.screens.multiplayerscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import inf112.core.multiplayer.ClientNetworkInterface;
import inf112.core.screens.IGameStateSwitcher;
import inf112.core.util.AssMan;
import inf112.core.util.ButtonFactory;

public class MultiplayerScreenJoin implements Screen {

    private boolean clicked = false;
    private Stage stage;
    private IGameStateSwitcher gameStateSwitcher;

    public MultiplayerScreenJoin(IGameStateSwitcher gameStateSwitcher){
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
        text.setText("Enter IP address here");
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


        TextButton connect = ButtonFactory.createCustomButton("Connect", 8);
        connect.setPosition(width/2 - connect.getWidth()/2, height*0.4f);
        connect.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // attempting to connect to server with the ip address typed by the human player
                ClientNetworkInterface.setServerIP(text.getText());
                ClientNetworkInterface.startClientThread();

                int i = 0;
                while (i < 25 && (!ClientNetworkInterface.isReady())) {
                    i++;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                dispose();
                if (ClientNetworkInterface.isConnected()) {            // connected to server
                    gameStateSwitcher.initMultiplayerClientStandby();
                }
                else                                                   // couldn't connect, takes him back
                    gameStateSwitcher.initMultiplayerSettings();
            }
        });
        stage.addActor(connect);

        TextButton back = ButtonFactory.createCustomButton("Back", 4);
        back.setPosition(width/2 - back.getWidth()/2, height*0.2f);
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
