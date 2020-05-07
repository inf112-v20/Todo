package inf112.core.screens.userinterface;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.cards.ProgramCard;
import inf112.core.cards.register.ProgramSheet;
import inf112.core.game.MainGame;
import inf112.core.player.Player;
import inf112.core.screens.GameScreen;
import inf112.core.util.AssMan;
import inf112.core.util.ButtonFactory;
import org.lwjgl.input.Keyboard;


import java.util.List;


public class UserInterface extends Actor{

    private Image overlay;
    private TextButton hideButton;
    private GameScreen screen;
    private MainGame game;
    private ProgramcardSelectionInteractive selectionButtons;
    private SelectionCards selectionCards;
    private Table table;
    private Stage stage;
    public UserInterface(GameScreen screen, MainGame game){
        this.screen = screen;
        this.selectionButtons = new ProgramcardSelectionInteractive();
        this.stage = screen.getStage();
        this.table = new Table();


        overlay = new Image(AssMan.manager.get(AssMan.OVERLAY));
        overlay.setPosition(getXPos(768),0);
        stage.addActor(overlay);

        this.selectionCards = new SelectionCards();

        stage.addActor(table);

        hideButton = ButtonFactory.createCustomButton("Hide UI", 2);
        hideButton.setPosition(getXPos(10), (getYPos(650)));
        hideButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectionButtons.hideButtons();
                overlay.setVisible(!overlay.isVisible());
                table.setVisible(!table.isVisible());
            }
        });
        stage.addActor(hideButton);
    }

    public Table getTable() {
        return table;
    }

    public ProgramcardSelectionInteractive getSelectionButtons() {
        return selectionButtons;
    }


    public void showSelectionCards(List<ProgramCard> cards){
        /*
        selectionCards.createcardbuttons();
        ImageCardWrapper[] cards = selectionCards.getSelectionPile();
        for(ImageCardWrapper card : cards) {
            table.addActor(card.getImage());
        }

         */


        selectionButtons.renderSelectionButtons(cards);
        ProgramCardButtonWrapper[] buttons = selectionButtons.getSelectionPile();

        for(ProgramCardButtonWrapper button : buttons){
            if (button == null) {continue; }
            table.addActor(button.getButton());
        }
        //table.addActor(createLockSelectionButton());


    }

    public void drawPlayerCondition(Player player){
        table.clearChildren();
        drawRegisters(player.getProgramSheet());
        drawLifetokens(player.getLifeTokens());
        drawDamagetokens(player.getDamageTokens());
    }

    private void drawDamagetokens(int damageTokens) {
        int lifetokenPosX = 800;
        int lifetokenPosY = 200;

        for(int i = 0; i < damageTokens; i++){
            Image image = new Image(AssMan.manager.get(AssMan.DAMAGETOKEN));
            image.setPosition(getXPos(lifetokenPosX + 30*i), getYPos(lifetokenPosY));
            image.setSize(28,28);
            table.addActor(image);
        }
    }

    private void drawLifetokens(int lifeTokens) {
        int[] lifetokenPosX = { 1120, 1170, 1220 };
        int[] lifetokenPosY = { 200,200,200 };

        for(int i = 0; i < lifeTokens; i++){
            Image image = new Image(AssMan.manager.get(AssMan.LIFETOKEN));
            image.setPosition(getXPos(lifetokenPosX[i]), getYPos(lifetokenPosY[i]));
            image.setSize(32,32);
            table.addActor(image);
        }

    }


    public void drawRegisters(ProgramSheet programSheet){
        int[] registerPosX = {20, 121, 222, 323, 424};
        int index = 0;
        List<ProgramCard> cards = programSheet.getCardList();
        for (ProgramCard card : cards) {
            index++;
            if (card == null) { continue; }
            Image image = new Image(card.getTexture());
            image.setSize(64, 128);
            image.setPosition(getXPos(registerPosX[index - 1] + 768), getYPos(28));
            table.addActor(image);
        }

    }


    public void resetSelectionButtons(){
        table.clearChildren();
        this.selectionButtons = new ProgramcardSelectionInteractive();
    }

    private float getXPos(float x){
        return ((float) x / 1280) * Gdx.graphics.getWidth();
    }
    private float getYPos(float y){
        return ((float) y / 720) * Gdx.graphics.getHeight();
    }

}
