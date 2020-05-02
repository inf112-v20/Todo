package inf112.core.screens.userinterface;

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
import inf112.core.player.Player;
import inf112.core.screens.GameScreen;
import inf112.core.util.AssMan;
import inf112.core.util.ButtonFactory;


import java.util.List;


public class UserInterface extends Actor{

    private Image overlay;
    private Image uibackground;
    private TextButton hideButton;
    private GameScreen screen;
    private ProgramcardSelectionInteractive selectionButtons;
    private Table table;
    private Stage stage;
    public UserInterface(GameScreen screen){
        this.screen = screen;
        this.selectionButtons = new ProgramcardSelectionInteractive();
        this.stage = screen.getStage();
        this.table = new Table();

        overlay = new Image(AssMan.manager.get(AssMan.OVERLAY));
        overlay.setPosition(768,0);
        stage.addActor(overlay);

        stage.addActor(table);

        hideButton = ButtonFactory.createCustomButton("Hide UI", 3);
        hideButton.setPosition(1100, 650);
        hideButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectionButtons.hideButtons();
                overlay.setVisible(!overlay.isVisible());
                //uibackground.setVisible(!uibackground.isVisible());
            }
        });
        stage.addActor(hideButton);
    }

    public Table getTable() {
        return table;
    }

    public void initializeSelectionPhase(List<ProgramCard> cards){
        table.clearChildren();
        GameScreen.getGame().getDeck().discardCards(selectionButtons.releaseCards());

        showSelectionCards(cards);


    }


    public void showSelectionCards(List<ProgramCard> cards){

        selectionButtons.renderSelectionButtons(cards);
        ProgramCardButtonWrapper[] buttons = selectionButtons.getSelectionPile();

        for(ProgramCardButtonWrapper button : buttons){
            table.addActor(button.getButton());
        }
        table.addActor(createLockSelectionButton());
    }



    public void drawPlayerCondition(Player player){
        drawRegisters(player.getProgramSheet());

    }


    public void drawRegisters(ProgramSheet programSheet){
        List<ProgramCard> cards = programSheet.getCardList();

    }

    public TextButton createLockSelectionButton(){
        TextButton button = ButtonFactory.createCustomButton("Confirm", 3);
        button.setPosition(1100, 550);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                List<ProgramCard> selected = selectionButtons.lockSelection();
                if (selected != null){
                    button.remove();
                    Player player = GameScreen.getGame().getActivePlayer();
                    for(ProgramCard card : selected){
                        player.addToProgramSheet(card);
                    }
                    // discard kort som ikke blir brukt...


                }
            }
        });
        return button;
    }

}
