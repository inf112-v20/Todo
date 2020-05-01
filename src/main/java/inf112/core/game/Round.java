package inf112.core.game;

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
