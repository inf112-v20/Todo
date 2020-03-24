package inf112.core.programcards;


import com.badlogic.gdx.graphics.Texture;

public abstract class Card {
    private CardType type;
    private String name;
    private Texture texture;

    public Card(CardType type, String name, Texture texture){
        this.type = type;
        this.name = name;
        this.texture = texture;
    }

    public CardType getType() {
        return type;
    }

    public String getName(){
        return name;
    }

    public Texture getTexture(){
        return texture;
    }

}
