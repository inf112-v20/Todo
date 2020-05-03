package inf112.core.game.event;

import com.badlogic.gdx.utils.Timer;
import inf112.core.game.MainGame;
import inf112.core.game.round.Round;

/**
 * Repair Event. An event where all wrenches will repair players standing on them.
 */
public class RepairEvent implements Event{
    private static float runtime = 0.5f;

    public void startEvent(float delay) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                MainGame.movementHandler.wrenchesRepair();
                System.out.println("Wrenches Repairing");
            }
        }, delay);
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}
