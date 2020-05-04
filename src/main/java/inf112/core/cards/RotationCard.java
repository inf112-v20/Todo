package inf112.core.cards;

import com.badlogic.gdx.graphics.Texture;
import inf112.core.tile.Rotation;

import java.util.Objects;

public class RotationCard extends ProgramCard {

    private Rotation rotation;
    private int amount;

    public RotationCard(int priority, Rotation rotation, int amount, String name, Texture texture) {
        super(CardType.ROTATION, priority, name, texture);
        this.rotation = rotation;
        this.amount = amount;
    }

    public boolean isClockwise(){
        return rotation.equals(Rotation.RIGHT);
    }

    public int getAmount() { return amount; }

    public Rotation getRotation() {
        return rotation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RotationCard)) return false;
        if (!super.equals(o)) return false;
        RotationCard that = (RotationCard) o;
        return rotation == that.rotation &&
                amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isClockwise(), amount);
    }
}
