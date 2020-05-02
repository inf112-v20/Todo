package inf112.core.game.event;

/**
 * General interface for Events
 * @author Alvar
 */
public interface Event {

    /**
     * Starts the event.
     */
    void startEvent();

    /**
     * Runtime of an event. Must be atleast larger than the actual runtime of the code, or timing errors will occur
     * @return runtime
     */
    float getRuntime();
}
