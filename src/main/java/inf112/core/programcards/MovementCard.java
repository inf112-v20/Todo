package inf112.core.programcards;

public class MovementCard extends ProgramCard {
    private int distance;
    public MovementCard(int priority, int distance){
        super("Movement", priority);
        this.distance = distance;
    }
}
