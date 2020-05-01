package inf112.core.game;

public interface Event {

    /**
     * runs the event
     */
    void startEvent();

    /**
     * runtime of event
     * @return runtime
     */
    float getRuntime();
}
