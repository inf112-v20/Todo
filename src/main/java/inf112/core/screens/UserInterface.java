package inf112.core.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.cards.ProgramCard;
import inf112.core.util.AssMan;
import inf112.core.util.ButtonFactory;

import java.util.List;


public class UserInterface extends Actor{
    private TextButton hideButton;
    private Stage stage;
    private boolean hide = false;

    public UserInterface(Stage stage){
        this.stage = stage;
        hideButton = ButtonFactory.createCustomButton("Hide UI", 3);
        hideButton.setPosition(1100, 650);
        hideButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide = hide ? false : true;
            }
        });
        stage.addActor(hideButton);
    }


    public void showPlayerCards(List<ProgramCard> cards){
        int posX = 0;
        int posY = 0;
        for (ProgramCard card : cards){
            Image cardTexture = new Image(card.getTexture());
            cardTexture.setPosition(posX, posY+=60);
            stage.addActor(cardTexture);
        }
    }

}
