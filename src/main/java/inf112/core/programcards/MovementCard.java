package inf112.core.programcards;

import com.badlogic.gdx.graphics.Texture;

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
}
