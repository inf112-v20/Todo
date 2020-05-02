package inf112.core.game.round;

import inf112.core.game.phase.Phase;

import java.util.ArrayList;
import java.util.List;

/**
 * Round class for running game rounds.
 * Amount of rounds to be run indicated by rounds variable
 * Contains a list of phases, and repeats these phases for every round.
 * @author Alvar
 */
public class Round {

    private int rounds;
    private float roundDelay;
    private List<Phase> phases;

    public Round(int rounds) {
        this.rounds = rounds;
        this.phases = new ArrayList<>();
        this.roundDelay = 0f;
    }

    public void instantiateNewGameRound() {
        for(int i = 0; i < rounds; i++) {
            roundStart();
            //2 second pause between each round
            roundDelay += 2f;
        }
    }

    private void roundStart() {
        for(Phase phase : phases) {
            phase.startPhase();

        }
    }

    public void addPhase(Phase phase) {
        phases.add(phase);
    }

    public float getRoundRuntime() {
        float runtime = 0f;
        for(Phase phase : phases) {
            runtime += phase.getRuntime();
        }
        return runtime;
    }
}
