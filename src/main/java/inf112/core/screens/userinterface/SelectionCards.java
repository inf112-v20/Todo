package inf112.core.screens.userinterface;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.cards.ProgramCard;
import inf112.core.cards.register.ProgramSheet;
import inf112.core.player.Player;
import inf112.core.screens.GameScreen;

import java.util.List;

public class SelectionCards {
    private boolean hide;

    private final int MAX_SELECTION_SIZE = 9;
    private final float SELECTION_CARD_SIZE = 2;

    private int[] posX = {140,210,280,350,420,490,560,630,700};
    private int[] posY = {10,10,10,10,10,10,10,10,10};

    private Image[] selectionPile;
    private Image[] registerPile;


    private final int[] registerPosX = {20, 121, 222, 323, 424};
    private final int registerPosY = 28;

    private final Player player;
    private final ProgramSheet programSheet;

    public SelectionCards(){
        selectionPile = new Image[9];
        registerPile = new Image[5];
        player = GameScreen.getGame().getActivePlayer();
        programSheet = player.getProgramSheet();
    }

    public void createcardbuttons(){
        List<ProgramCard> cards = programSheet.getHand();
        for(int i = 0; i < cards.size(); i++){
            ProgramCard card = cards.get(i);
            selectionPile[i] = createButton(card);
            selectionPile[i].setPosition(newX(posX[i]), newY(posY[i]));
            unselectedlistener(selectionPile[i]);
        }

    }

    private Image createButton(ProgramCard card){
        Texture cardTexture = card.getTexture();
        Image image = new Image(cardTexture);
        return image;
    }

    private float newX(float x){
        return ((float) x / 1280) * Gdx.graphics.getWidth();
    }
    private float newY(float y){
        return ((float) y / 720) * Gdx.graphics.getHeight();
    }

    private void unselectedlistener(Image card){
        card.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("hei");
            }
        });
    }

}
