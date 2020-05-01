package inf112.core.screens.userinterface;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import inf112.core.cards.ProgramCard;

public class ProgramCardButtonWrapper {
    private final ImageButton button;
    private final ProgramCard card;
    private final float x;
    private final float y;


    public ProgramCardButtonWrapper(ImageButton button, ProgramCard card){
        this.button = button;
        this.card = card;
        this.x = button.getX();
        this.y = button.getY();
    }

    protected ProgramCard getCard(){
        return card;
    }

    protected ImageButton getButton(){
        return button;
    }

    protected void returnPosition(){
        button.setPosition(x,y);
    }

    protected void setPosition(float x, float y){
        button.setPosition(x,y);
    }

    protected void dispose(){
        button.remove();

    }

    public boolean isVisible() {
        return button.isVisible();
    }


    public void setVisible(boolean b) {
        button.setVisible(b);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
