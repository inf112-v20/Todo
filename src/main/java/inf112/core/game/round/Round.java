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
    private List<Phase> phases;

    public Round(int rounds, List<Phase> phases) {
        this.rounds = rounds;
        this.phases = phases;
    }

    private void roundStart(float delay) {
        float phaseDelay = delay;
        for(Phase phase : phases) {
            phase.startPhase(phaseDelay);
            phaseDelay += phase.getRuntime();
        }
    }

    public float getRoundRuntime() {
        float runtime = 0f;
        for(Phase phase : phases) {
            runtime += phase.getRuntime();
        }
        return runtime;
    }
}
