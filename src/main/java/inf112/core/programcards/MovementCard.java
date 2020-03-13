package inf112.core.programcards;

public class MovementCard extends ProgramCard {

    private int distance;
    private boolean forward;

    public MovementCard(int priority, int distance, boolean forward, String name){
        super(CardType.MOVEMENT, priority, name);
        this.distance = distance;
        this.forward = forward;
    }

    public int getDistance(){
        return distance;
    }

    public boolean isForward() { return forward; }
}
