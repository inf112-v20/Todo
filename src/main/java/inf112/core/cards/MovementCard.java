package inf112.core.cards;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

public class MovementCard extends ProgramCard {

    private int distance;
    private boolean forward;

    public MovementCard(int priority, int distance, boolean forward, String name, Texture texture){
        super(CardType.MOVEMENT, priority, name, texture);
        this.distance = distance;
        this.forward = forward;
    }

    public int getDistance(){
        return distance;
    }

    public boolean isForward() { return forward; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovementCard)) return false;
        if (!super.equals(o)) return false;
        MovementCard that = (MovementCard) o;
        return distance == that.distance &&
                forward == that.forward;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), distance, forward);
    }
}
