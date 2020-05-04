package inf112.core.game.round;

import inf112.core.game.GameRule;
import inf112.core.game.phase.Phase;

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
    public static int roundCount;

    public Round(int rounds, List<Phase> phases) {
        this.rounds = rounds;
        this.phases = phases;
        this.roundCount = 0;
    }

    public void roundStart(float delay) {
        iterateRoundCount();
        System.out.println("Round started");
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

    public int getAmountOfRounds() {
        return rounds;
    }

    private void iterateRoundCount() {
        int temp = roundCount;
        roundCount = (temp % GameRule.NUMBER_OF_ROUNDS) + 1;
    }
}
