package inf112.core.cards;


import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

/**
 * @author Pål
 * A abstract class for creating movement-/rotationcards
 */
public abstract class ProgramCard extends Card implements Comparable<ProgramCard>{

    protected int priority;

    public ProgramCard(CardType type, int priority, String name, Texture texture){
        super(type, name, texture);
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }


    @Override
    public int compareTo(ProgramCard other){
        return priority - other.getPriority();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgramCard)) return false;
        if (!super.equals(o)) return false;
        ProgramCard that = (ProgramCard) o;
        return priority == that.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), priority);
    }
}
