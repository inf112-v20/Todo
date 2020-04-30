package inf112.core.screens.userinterface;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.cards.ProgramCard;
import inf112.core.game.MainGame;
import inf112.core.screens.GameScreen;
import inf112.core.util.ButtonFactory;

import java.util.List;


public class UserInterface extends Actor{
    private GameScreen screen;
    private static MainGame game;

    private TextButton hideButton;
    private ProgramcardSelectionInteractive selectionButtons;
    private Stage stage;

    public UserInterface(GameScreen screen){
        this.screen = screen;
        this.stage = screen.getStage();
        game = GameScreen.getGame();
        selectionButtons = new ProgramcardSelectionInteractive();


        showSelectionCards(game.getDeck().getCards(9));



        hideButton = ButtonFactory.createCustomButton("Hide UI", 3);
        hideButton.setPosition(1100, 650);
        hideButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectionButtons.hideButtons();
            }
        });
        stage.addActor(hideButton);
    }

    public void showSelectionCards(List<ProgramCard> cards){
        List<ImageButton> buttons = selectionButtons.renderSelectionButtons(cards);
        for(ImageButton button : buttons){
            stage.addActor(button);
        }
    }

}
