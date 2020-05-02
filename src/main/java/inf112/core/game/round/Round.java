package inf112.core.game.round;

import inf112.core.game.phase.EventPhase;

import java.util.List;

public class Round {

    private int rounds;
    private float delay;
    private List<EventPhase> phases;

    public Round(int rounds) {
        this.rounds = rounds;
    }

    public void roundStart() {
        for(EventPhase phase : phases) {


        }
    }
}
