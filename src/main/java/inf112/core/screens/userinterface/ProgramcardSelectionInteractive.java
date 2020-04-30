package inf112.core.screens.userinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.cards.ProgramCard;
import inf112.core.util.ButtonFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.min;

public class ProgramcardSelectionInteractive {

    private boolean hide = true;
    private final int MAX_SELECTION_SIZE = 9;
    private final int CARD_SIZE = 8;

    private List<ImageButton> buttons;
    private int[] posX;
    private int[] posY;
    private int[] registerPosX = {20, 121, 222, 323, 424};
    private int[] registerPosY = {28,28,28,28,28};

    public ProgramcardSelectionInteractive(){
        buttons = new ArrayList<>();
        posX = new int[MAX_SELECTION_SIZE];
        posY = new int[MAX_SELECTION_SIZE];
        for(int i = 0; i < MAX_SELECTION_SIZE; i++){
            posX[i] = 30 + 128*i;
            posY[i] = 200;
        }

    }

    public List<ImageButton> renderSelectionButtons(List<ProgramCard> cards){
        for(ImageButton button : buttons){
            button.remove();
        }
        //Gdx.graphics.getWidth()..?
        for (int i = 0; i < min(MAX_SELECTION_SIZE, cards.size()); i++){
            ProgramCard currentCard = cards.get(i);
            Texture cardTexture = currentCard.getTexture();
            ImageButton button = ButtonFactory.createImageButton(cardTexture, CARD_SIZE);
            button.setPosition(posX[i], posY[i]);
            buttons.add(button);
        }
        return buttons;
    }


    public void hideButtons() {
        if (hide) {
            for (ImageButton card : buttons) {
                if (card.isVisible()) {
                    card.setVisible(false);
                }
            }
        } else {
            for (ImageButton card : buttons) {
                if (!card.isVisible()) {
                    card.setVisible(true);
                }
            }
        }
        hide = !hide;
    }

    public void renderLockedCards(List<ProgramCard> lockedCards){

    }

    public boolean canSelectCard(){
        // valgte kort er ikke fullt (Må ha med kort fra registeret...)

        return false;
    }

    public ProgramCard selectCard(){
        //

        return null;
    }

    public List<ProgramCard> lockSelection(){
        // Har valgt alle kort
        // canLockSelection
        // Player Register...
        return null;
    }
}
