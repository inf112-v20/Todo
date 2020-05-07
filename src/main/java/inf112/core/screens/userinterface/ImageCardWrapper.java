package inf112.core.screens.userinterface;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.core.cards.ProgramCard;

public class ImageCardWrapper {

    private final Image image;
    private final ProgramCard card;


    public ImageCardWrapper(Image image, ProgramCard card){
        this.image = image;
        this.card = card;
    }

    protected ProgramCard getCard(){
        return card;
    }

    protected Image getImage(){
        return image;
    }

    protected void setPosition(float x, float y){
        image.setPosition(x,y);
    }

    protected void dispose(){
        image.remove();
    }

}


