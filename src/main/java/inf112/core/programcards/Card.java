package inf112.core.programcards;


import com.badlogic.gdx.graphics.Texture;

/**
 * @author Pål
 * Abstract class for creating cards (Option and movement)
 */
public abstract class Card {
    private CardType type;
    private String name;
    private Texture texture;

    public Card(CardType type, String name, Texture texture){
        this.type = type;
        this.name = name;
        this.texture = texture;
    }

    public String getName(){
        return name;
    }

    public Texture getTexture(){
        return texture;
    }

}
