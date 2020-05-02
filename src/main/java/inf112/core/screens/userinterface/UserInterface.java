package inf112.core.screens.userinterface;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.cards.ProgramCard;
import inf112.core.cards.register.ProgramSheet;
import inf112.core.game.MainGame;
import inf112.core.player.Player;
import inf112.core.screens.GameScreen;
import inf112.core.util.AssMan;
import inf112.core.util.ButtonFactory;

import java.util.Arrays;
import java.util.List;


public class UserInterface extends Actor{
    private GameScreen screen;
    private static MainGame game;

    private Image overlay;
    private TextButton hideButton;
    private ProgramcardSelectionInteractive selectionButtons;
    private Stage stage;

    public UserInterface(GameScreen screen){
        this.screen = screen;
        this.stage = screen.getStage();
        this.game = GameScreen.getGame();
        this.selectionButtons = new ProgramcardSelectionInteractive();

        overlay = new Image(AssMan.manager.get(AssMan.OVERLAY));
        stage.addActor(overlay);


        hideButton = ButtonFactory.createCustomButton("Hide UI", 3);
        hideButton.setPosition(1100, 650);
        hideButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectionButtons.hideButtons();
                overlay.setVisible(!overlay.isVisible());
            }
        });
        stage.addActor(hideButton);

        initializeSelectionPhase(game.getDeck().getCards(9));

    }

    public void initializeSelectionPhase(List<ProgramCard> cards){
        showSelectionCards(cards);
        stage.addActor(createLockSelectionButton());
    }

    public TextButton createLockSelectionButton(){
        TextButton button = ButtonFactory.createCustomButton("Confirm", 3);
        button.setPosition(1100, 550);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectionButtons.lockSelection() != null){
                    button.remove();

                }
            }
        });
        return button;
    }

    public void showSelectionCards(List<ProgramCard> cards){

        selectionButtons.renderSelectionButtons(cards);
        List<ProgramCardButtonWrapper> buttons = Arrays.asList(selectionButtons.getSelectionPile());

        for(ProgramCardButtonWrapper button : buttons){
            stage.addActor(button.getButton());
        }
    }



    public void drawPlayerCondition(Player player){
        drawRegisters(player.getProgramSheet());

    }


    public void drawRegisters(ProgramSheet programSheet){
        List<ProgramCard> cards = programSheet.getCardList();

    }


}
