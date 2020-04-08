package inf112.core.cards;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RotationCard)) return false;
        if (!super.equals(o)) return false;
        RotationCard that = (RotationCard) o;
        return clockwise == that.clockwise &&
                rotations == that.rotations;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clockwise, rotations);
    }
}
