package inf112.core.game.phase;

/**
 * General interface for Phases
 * @author Alvar
 */
public interface Phase {

    /**
     * Starts the phase. In practice it should start the events of the phase in he correct order, and calculate the
     * timing of every event.
     */
    void startPhase();

    /**
     * returns runtime of phase in float, used for timing tasks
     * @return runtime of entire phase
     */
    float getRuntime();
}
