package inf112.core.programcards;

public class RotationCard extends ProgramCard {

    private boolean clockwise;
    private int rotations;

    public RotationCard(int priority, boolean clockwise, int rotations, String name) {
        super(CardType.ROTATION, priority, name);
        this.clockwise = clockwise;
        this.rotations = rotations;
    }

    public boolean isClockwise(){
        return clockwise;
    }

    public int getRotations() { return rotations; }
}
