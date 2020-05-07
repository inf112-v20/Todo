package inf112.core.screens.userinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.cards.ProgramCard;
import inf112.core.cards.register.ProgramSheet;
import inf112.core.player.Player;
import inf112.core.screens.GameScreen;

import java.util.List;

public class SelectionCards {

    private final int MAX_SELECTION_SIZE = 9;
    private final float SELECTION_CARD_SIZE = 2;

    private int[] posX = {140,210,280,350,420,490,560,630,700};
    private int[] posY = {10,10,10,10,10,10,10,10,10};

    private ImageCardWrapper[] selectionPile;

    private final int[] registerPosX = {20, 121, 222, 323, 424};
    private final int registerPosY = 28;

    private final Player player;
    private final ProgramSheet programSheet;


    public SelectionCards(){
        selectionPile = new ImageCardWrapper[9];
        player = GameScreen.getGame().getActivePlayer();
        programSheet = player.getProgramSheet();
    }

    public void createcardbuttons(){
        List<ProgramCard> cards = programSheet.getHand();

        for(int i = 0; i < cards.size(); i++){
            ProgramCard card = cards.get(i);
            selectionPile[i] = createcardButton(card);
            selectionPile[i].getImage().setPosition(newX(posX[i]), newY(posY[i]));
            selectionPile[i].getImage().setSize(32*SELECTION_CARD_SIZE,64*SELECTION_CARD_SIZE );
            unselectedlistener(selectionPile[i]);
        }
    }

    private ImageCardWrapper createcardButton(ProgramCard card){
        Texture cardTexture = card.getTexture();
        Image image = new Image(cardTexture);
        return new ImageCardWrapper(image, card);
    }

    private float newX(float x){
        return ((float) x / 1280) * Gdx.graphics.getWidth();
    }
    private float newY(float y){
        return ((float) y / 720) * Gdx.graphics.getHeight();
    }

    private void unselectedlistener(ImageCardWrapper card){
        card.getImage().clearListeners();
        card.getImage().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                programSheet.addToRegister(card.getCard());
                card.getImage().setPosition(registerPosX[getPos()], registerPosY+100);
            }
        });
    }

    private void selectedlistener(ImageCardWrapper card){
        card.getImage().clearListeners();
        card.getImage().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!programSheet.remove(card.getCard())) throw new IllegalStateException();
                card.getImage().setPosition(registerPosX[getPos()], registerPosY+100);
            }
        });
    }

    private int getPos(){
        return programSheet.getCardList().size();
    }

    public ImageCardWrapper[] getSelectionPile() {
        return selectionPile;
    }
}
