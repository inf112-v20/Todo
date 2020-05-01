package inf112.core.screens.userinterface;

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
    private final int SELECTION_CARD_SIZE = 8;

    private List<ProgramCardButtonWrapper> buttons;
    private int[] posX;
    private int[] posY;

    private ProgramCardButtonWrapper[] selectionPile;
    private ProgramCardButtonWrapper[] registerPile;

    private boolean[] registerPositionStatus = {false, false, false, false, false};

    private final int[] registerPosX = {20, 121, 222, 323, 424};
    private final int registerPosY = 28;

    //Klasse som extender imagebutton...
    //ImageButton som inneholder 2 posisjoner, en metode for å bytte posisjon, et programkort, og en getter for programkortet

    public ProgramcardSelectionInteractive(){
        selectionPile = new ProgramCardButtonWrapper[9];
        registerPile = new ProgramCardButtonWrapper[5];



        buttons = new ArrayList<>();
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
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    moveToRegister(buttonContainer);
                    removeFromSelectionPile(buttonContainer);
                }
            });
            selectionPile[i] = buttonContainer;

        }
    }


    public void hideButtons() {
        if (hide) {
            for (ProgramCardButtonWrapper card : buttons) {
                if (card.isVisible()) {
                    card.setVisible(false);
                }
            }
        } else {
            for (ProgramCardButtonWrapper card : buttons) {
                if (!card.isVisible()) {
                    card.setVisible(true);
                }
            }
        }
        hide = !hide;
    }

    private void moveToRegister(ProgramCardButtonWrapper button){
        int newX = getFirstFreeRegister();
        System.out.println(newX);
        if (newX == -1) {return;}

        registerPile[newX] = button;

        button.setPosition(registerPosX[newX], 28);
        button.getButton().clearListeners();
        button.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                deselectCard(button);
            }
        });


    }

    private void removeFromSelectionPile(ProgramCardButtonWrapper button){
        for(int i = 0; i < selectionPile.length; i++){
            //ProgramCardButtonWrapper buttonInList : selectionPile){
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
    }

    public void addLockedCards(List<ProgramCard> lockedCards){
        for(int i = 5; i > 0; i--){

        }
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
