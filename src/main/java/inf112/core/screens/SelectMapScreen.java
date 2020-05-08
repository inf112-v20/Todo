package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.game.MainGame;
import inf112.core.screens.IGameStateSwitcher;
import inf112.core.util.AssMan;
import inf112.core.util.ButtonFactory;

public class SelectMapScreen implements Screen {

    private float width;
    private float height;

    public static String mapFilePath;
    public static int numPlayers = 1;

    private Label.LabelStyle style;
    private BitmapFont font;

    private Stage stage;
    private IGameStateSwitcher gameStateSwitcher;

    private Label playerAmountLabel;

    public SelectMapScreen(IGameStateSwitcher gameStateSwitcher){
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


        Label mapLabel = new Label("Select map!" , style);
        mapLabel.setPosition(width/3 - mapLabel.getWidth()/2, height*0.9f);
        stage.addActor(mapLabel);

        Label playersLabel = new Label("Players!" , style);
        playersLabel.setPosition(width/4*3 - playersLabel.getWidth()/2, height*0.9f);
        stage.addActor(playersLabel);

        playerAmountLabel = new Label("" , style);
        playerAmountLabel.setPosition(width/4*3 - playerAmountLabel.getWidth()/2, height*0.55f);
        stage.addActor(playerAmountLabel);



        TextButton addPlayer = ButtonFactory.createCustomButton("+", 4);
        addPlayer.setPosition(width/4*3 - addPlayer.getWidth()/2, height*0.6f);
        addPlayer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                numPlayers = Math.min(++numPlayers, 8);
                updateNumPlayers();
            }
        });
        stage.addActor(addPlayer);

        TextButton removePlayer = ButtonFactory.createCustomButton("-", 4);
        removePlayer.setPosition(width/4*3 - removePlayer.getWidth()/2, height*0.40f);
        removePlayer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                numPlayers = Math.max(--numPlayers, 1);
                updateNumPlayers();
            }
        });
        stage.addActor(removePlayer);



        TextButton back = ButtonFactory.createCustomButton("Back", 4);
        back.setPosition(width/4 - back.getWidth()/2, height*0.10f);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                gameStateSwitcher.initMainMenu();
            }
        });
        stage.addActor(back);

        TextButton start = ButtonFactory.createCustomButton("Start Game", 4);
        start.setPosition(width/4*3 - start.getWidth()/2, height*0.10f);
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                gameStateSwitcher.initMainGame();
            }
        });
        stage.addActor(start);

        updateNumPlayers();

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

    public void updateNumPlayers(){
        playerAmountLabel.setText(numPlayers);
    }
}
