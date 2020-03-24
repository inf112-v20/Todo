package inf112.core.programcards;

import com.badlogic.gdx.graphics.Texture;

public class RotationCard extends ProgramCard {

    private boolean clockwise;
    private int rotations;

    public RotationCard(int priority, boolean clockwise, int rotations, String name, Texture texture) {
        super(CardType.ROTATION, priority, name, texture);
        this.clockwise = clockwise;
        this.rotations = rotations;
    }

    public boolean isClockwise(){
        return clockwise;
    }

    public int getRotations() { return rotations; }
}
