package inf112.core.game.event;

import com.badlogic.gdx.utils.Timer;
import inf112.core.game.MainGame;

/**
 * Gear Event. An event where all gears will rotate players standing on them.
 */
public class GearEvent implements Event{
    private static float runtime = 0.5f;

    public void startEvent(float delay) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                MainGame.movementHandler.gearsRotate();
                System.out.println("Rotating Gears");
            }
        }, delay);
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}
