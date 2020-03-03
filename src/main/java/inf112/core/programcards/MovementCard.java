package inf112.core.programcards;

public class MovementCard extends ProgramCard {

    private int distance;

    public MovementCard(int priority, int distance, String name){
        super(CardType.MOVEMENT, priority, name);
        this.distance = distance;
    }

    public int getDistance(){
        return distance;
    }
}
