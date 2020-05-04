package inf112.core.game.phase;

import inf112.core.game.GameRule;
import inf112.core.game.event.*;

import java.util.ArrayList;
import java.util.List;

public class BoardPhase implements Phase {

    private List<Event> events;
    private static float runtime;

    public BoardPhase() {
        this.events = new ArrayList<>();
        events.add(new ConveyorEvent());
        events.add(new LaserEvent());
        events.add(new PusherEvent());
        events.add(new GearEvent());
        events.add(new RepairEvent());
        setRuntime();
    }

    @Override
    public void startPhase(float delay) {
        float eventDelay = delay;
        for(Event event : events) {
            event.startEvent(eventDelay);
            eventDelay += event.getRuntime();
        }
    }

    private void setRuntime() {
        float rTime = 0f;
        for(Event event : events) {
            rTime += event.getRuntime();
        }
        runtime = rTime;
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}
