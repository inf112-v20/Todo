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

public class MultiplayerClientPromptIPScreen implements Screen {

    private boolean clicked = false;
    private Stage stage;
    private IGameStateSwitcher gameStateSwitcher;

    public MultiplayerClientPromptIPScreen(IGameStateSwitcher gameStateSwitcher){
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

        TextField writeIPText = new TextField("", style);
        writeIPText.setText("Enter IP address here");
        writeIPText.setSize(1000,200);
        writeIPText.setMaxLength(20);

        writeIPText.setPosition(width/2 - writeIPText.getWidth()/2, height*0.6f);
        writeIPText.setAlignment(Align.center);
        writeIPText.setMaxLength(20);

        writeIPText.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!clicked) writeIPText.setText("");
                clicked = true;
            }
        });
        stage.addActor(writeIPText);


        TextButton connectButton = ButtonFactory.createCustomButton("Connect", 8);
        connectButton.setPosition(width/2 - connectButton.getWidth()/2, height*0.4f);
        connectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clicked) {
                    tryToJoinGame(writeIPText.getText(), MultiplayerPromptNameScreen.nameTyped);
                }
            }
        });
        stage.addActor(connectButton);

        TextButton backButton = ButtonFactory.createCustomButton("Back", 4);
        backButton.setPosition(width/2 - backButton.getWidth()/2, height*0.2f);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                gameStateSwitcher.initMultiplayerJoinOrHost();
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

    private void tryToJoinGame(String ip, String desiredName) {
        // attempting to connect to server with the ip address typed by the human player
        ClientNetworkInterface.setServerIP(ip);
        ClientNetworkInterface.startClientThread();

        // takes some time to establish a connection
        int i = 0;
        while (i < 25 && (!ClientNetworkInterface.isReady())) {
            i++;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // connection failed
        if (!ClientNetworkInterface.isConnected()) {
            ClientNetworkInterface.stop();                   // stops the remaining ongoing multiplayer threads
            dispose();
            gameStateSwitcher.initMultiplayerClientConnectingFailed();
            return;
        }

        // connection succeeded, let's send join game request
        ClientNetworkInterface.attemptToJoinServer(desiredName);

        // takes som time to verify the typed name
        i = 0;
        while (i < 25 && (!ClientNetworkInterface.hasJoinedServer())) {
            i++;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        dispose();

        // connection was dropped, party is full
        if (!ClientNetworkInterface.isConnected()) {
            ClientNetworkInterface.stop();                   // stops the remaining ongoing multiplayer threads
            gameStateSwitcher.initMultiplayerClientConnectingFailed();
            return;
        }

        // server rejected the desired name
        if (!ClientNetworkInterface.hasJoinedServer()) {
            ClientNetworkInterface.stop();
            gameStateSwitcher.initMultiplayerClientConnectingFailed();   // takes the player back one screen
            return;
        }
        // WE JOINED THE SERVER
        gameStateSwitcher.initMultiplayerClientStandby();
    }
}
