package inf112.core.screens.userinterface;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.cards.ProgramCard;
import inf112.core.util.ButtonFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.min;

public class ProgramcardSelectionInteractive {

    private boolean hide = true;
    private final int MAX_SELECTION_SIZE = 9;
    private final int SELECTION_CARD_SIZE = 4;

    private int[] posX;
    private int[] posY;

    private ProgramCardButtonWrapper[] selectionPile;
    private ProgramCardButtonWrapper[] registerPile;


    private final int[] registerPosX = {20, 121, 222, 323, 424};
    private final int registerPosY = 28;

    public ProgramcardSelectionInteractive(){
        selectionPile = new ProgramCardButtonWrapper[9];
        registerPile = new ProgramCardButtonWrapper[5];


        posX = new int[MAX_SELECTION_SIZE];
        posY = new int[MAX_SELECTION_SIZE];
        for(int i = 0; i < MAX_SELECTION_SIZE; i++){
            posX[i] = 30 + 128*i;
            posY[i] = 250;
        }

    }

    public ProgramCardButtonWrapper[] getSelectionPile() {
        return selectionPile;
    }

    public void renderSelectionButtons(List<ProgramCard> cards){

        for (int i = 0; i < min(MAX_SELECTION_SIZE, cards.size()); i++){
            ProgramCard currentCard = cards.get(i);
            Texture currentCardTexture = currentCard.getTexture();
            ImageButton button = ButtonFactory.createImageButton(currentCardTexture, SELECTION_CARD_SIZE);
            button.setPosition(posX[i], posY[i]);
            ProgramCardButtonWrapper buttonContainer = new ProgramCardButtonWrapper(button, currentCard);
            buttonListenerForSelection(buttonContainer);
            selectionPile[i] = buttonContainer;
        }
    }

    public TextButton createLockSelectionButton(){
        TextButton button = ButtonFactory.createCustomButton("Confirm", 3);
        button.setPosition(1100, 550);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                button.remove();
                lockSelection();
            }
        });
        return button;
    }


    public void hideButtons() {
        for (ProgramCardButtonWrapper card : selectionPile) {
            if (card == null) {continue;}
            card.setVisible(!card.isVisible());
        }
        for (ProgramCardButtonWrapper card : registerPile) {
            if (card == null) {continue;}
            card.setVisible(!card.isVisible());
        }

        hide = !hide;
    }

    private void moveToRegister(ProgramCardButtonWrapper button){
        int newX = getFirstFreeRegister();
        if (newX == -1) {return;}

        registerPile[newX] = button;

        button.setPosition(registerPosX[newX], registerPosY);
        buttonListenerForDeselection(button);
    }

    private void buttonListenerForDeselection(ProgramCardButtonWrapper button){
        button.getButton().clearListeners();
        button.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                deselectCard(button);
            }
        });
    }

    private void buttonListenerForSelection(ProgramCardButtonWrapper button){
        button.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                moveToRegister(button);
                removeFromSelectionPile(button);
            }
        });
    }

    private void removeFromSelectionPile(ProgramCardButtonWrapper button){
        for(int i = 0; i < selectionPile.length; i++){
            ProgramCardButtonWrapper buttonInList = selectionPile[i];
            if (button == buttonInList){
                selectionPile[i] = null;
            }
        }
    }

    private void removeFromRegisterPile(ProgramCardButtonWrapper button){
        for(int i = 0; i < registerPile.length; i++){
            //ProgramCardButtonWrapper buttonInList : selectionPile){
            ProgramCardButtonWrapper buttonInList = registerPile[i];
            if (button == buttonInList){
                registerPile[i] = null;
            }
        }
    }

    private int getFirstFreeRegister(){
        for(int i = 0; i < registerPile.length; i++){
            if (registerPile[i] == null) { return i; }
        }
        return -1;
    }

    private int getFreePositionInSelectionPile(){
        for (int i = 0; i < selectionPile.length; i++){
            if (selectionPile[i] == null){
                return i;
            }
        }
        return -1;
    }

    private void deselectCard(ProgramCardButtonWrapper button){
        int selectionPilePosition = getFreePositionInSelectionPile();
        if (selectionPilePosition == -1) {return;}

        button.returnPosition();
        removeFromRegisterPile(button);
        selectionPile[selectionPilePosition] = button;
        buttonListenerForSelection(button);
    }

    public void addLockedCards(List<ProgramCard> lockedCards){
        for(int i = 5; i > 0; i--){

        }
    }


    public List<ProgramCard> lockSelection(){
        List<ProgramCard> cards = new ArrayList<>();
        for(ProgramCardButtonWrapper button : registerPile){
            if (button == null) {return null;}
            cards.add(button.getCard());
        }

        for(ProgramCard card : cards){
            System.out.println(card.getName());
        }

        for(ProgramCardButtonWrapper button : selectionPile){
            if (button == null) {continue;}
            button.dispose();
        }
        for(ProgramCardButtonWrapper button : registerPile){
            if (button == null) {continue;}
            button.dispose();
        }

        return cards;
    }

}