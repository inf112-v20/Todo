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
import inf112.core.multiplayer.ClientNetworkInterface;
import inf112.core.multiplayer.HostNetworkInterface;
import inf112.core.multiplayer.notused.Multiplayer;
import inf112.core.screens.IGameStateSwitcher;
import inf112.core.util.AssMan;
import inf112.core.util.ButtonFactory;

public class MultiplayerScreen implements Screen {

    private Stage stage;
    private IGameStateSwitcher gameStateSwitcher;

    public MultiplayerScreen(IGameStateSwitcher gameStateSwitcher){
        this.gameStateSwitcher = gameStateSwitcher;
        System.out.println(MultiplayerScreenPlayerName.nameTyped);
    }


    @Override
    public void show() {
        this.stage = new Stage();

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();


        BitmapFont font = AssMan.manager.get(AssMan.CHINTZY_FONT.fileName);
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        font.getData().setScale(2);
        style.fontColor = com.badlogic.gdx.graphics.Color.BLUE;
        Label label = new Label("Hello " + MultiplayerScreenPlayerName.nameTyped + "!", style);
        label.setPosition(width/2 - label.getWidth()/2, height*0.8f);
        stage.addActor(label);


        TextButton join = ButtonFactory.createCustomButton("Join Game", 8);
        join.setPosition(width/2 - join.getWidth()/2, height*0.6f);
        join.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO new screen: type in server IP
            }
        });
        stage.addActor(join);


        TextButton host = ButtonFactory.createCustomButton("Host Game", 8);
        host.setPosition(width/2 - host.getWidth()/2, height*0.4f);
        host.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                // start both network threads
                HostNetworkInterface.startServerThread();
                ClientNetworkInterface.startClientThread();

                while (!ClientNetworkInterface.isReady()) {    // need to wait until we have a connection
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ClientNetworkInterface.attemptToJoinServer(MultiplayerScreenPlayerName.nameTyped);

                // there should be no problem connecting to local server since no name is taken
                // so we immediately go to next screen
                gameStateSwitcher.initMultiplayerHost();
            }
        });
        stage.addActor(host);


        TextButton back = ButtonFactory.createCustomButton("Back", 4);
        back.setPosition(width/2 - back.getWidth()/2, height*0.2f);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStateSwitcher.initMultiplayerPlayername();
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
