package inf112.core.screens.userinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.core.game.MainGame;
import inf112.core.player.Player;
import inf112.core.screens.GameScreen;
import inf112.core.util.AssMan;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatusScreen extends Actor {

    private Image background;
    private MainGame game;
    private GameScreen screen;
    private Table table;
    private Stage stage;
    private List<Player> allPlayers;
    private final int lifetokenPosX = 0;
    private final int lifetokenPosY = 100;
    private final int damageTokenPosX = 0;
    private final int damageTokenPosY = 87;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/chintzy_font.fnt"), false);

    public StatusScreen(GameScreen screen, MainGame game){
        this.screen = screen;
        this.game = game;
        this.table = new Table();
        this.stage = screen.getStage();

        background = new Image(AssMan.manager.get(AssMan.UIBACKGROUND));
        background.setPosition(0,0);
        background.setSize(140, 725);
        stage.addActor(background);

    }

    public Table getTable(){ return table; }

    public void drawStatus(){
        table.clearChildren();
        drawTokens();
    }

    private void drawTokens(){
        Iterable<Player> players = Stream.concat(game.getPlayers().stream(), game.getDisabledPlayers().stream()).collect(Collectors.toList());

        int y = 0;
        for (Player player : players){
            int lifeTokens = player.getLifeTokens();

            Label playerName = new Label(player.getName(), new Label.LabelStyle(font, Color.BLACK));
            playerName.setText(player.getName());
            playerName.setPosition(lifetokenPosX, lifetokenPosY+y);
            playerName.setSize(50, 100);
            playerName.setFontScale(0.80f);
            table.addActor(playerName);

            for(int i = 0; i < lifeTokens; i++){
                Image image = new Image(AssMan.manager.get(AssMan.LIFETOKEN));
                image.setPosition(getXPos(lifetokenPosX + 30*i), getYPos(lifetokenPosY+y));
                image.setSize(28,28);
                table.addActor(image);
            }

            for (int i = 0; i < player.getDamageTokens(); i ++){
                Image imageDam = new Image(AssMan.manager.get(AssMan.DAMAGETOKEN));
                imageDam.setPosition(getXPos(damageTokenPosX + 13*i), getYPos(damageTokenPosY+y));
                imageDam.setSize(12,12);
                table.addActor(imageDam);
            }

            y += 80;
        }
    }



    private float getXPos(float x){
        return x;
    }
    private float getYPos(float y){
        return y;
    }

}
