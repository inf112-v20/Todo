package inf112.core.programcards;

public class RotationCard extends ProgramCard {

    private boolean clockwise;

    public RotationCard(int priority, boolean clockwise, String name) {
        super(CardType.ROTATION, priority, name);
        this.clockwise = clockwise;
    }

    public boolean getClockwise(){
        return clockwise;
    }
}
