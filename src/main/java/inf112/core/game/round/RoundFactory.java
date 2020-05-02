package inf112.core.game.round;

import inf112.core.cards.register.ProgramSheet;
import inf112.core.game.phase.Phase;

import java.util.ArrayList;
import java.util.List;

public class RoundFactory {

    private List<Phase> phases;
    private int rounds;

    public RoundFactory() {
        this.phases = new ArrayList<>();
    }

    public Round generate() {
        if(rounds <= 0)
            throw new IllegalStateException("round amount must be larger than 0");
        if(rounds > ProgramSheet.NUM_OF_REGISTERS)
            throw new IllegalStateException("round amount cannot be larger that num of possible registers");
        if(phases.isEmpty())
            throw new IllegalStateException("phase list cannot be empty");

        return new Round(rounds, phases);
    }

    public void addPhase(Phase phase) {
        phases.add(phase);
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setAmountOfRounds(int amount) {
        this.rounds = amount;
    }

    public int getAmountOfRounds() {
        return rounds;
    }
}


