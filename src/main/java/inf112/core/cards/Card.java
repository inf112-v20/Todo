package inf112.core.cards;


import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

/**
 * @author Pål
 * Abstract class for creating cards (Option and movement)
 */
public abstract class Card {
    protected CardType type;
    protected String name;
    protected Texture texture;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return type == card.type &&
                name.equals(card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }
}
