package inf112.core.game.event;

import com.badlogic.gdx.utils.Timer;
import inf112.core.game.MainGame;

/**
 * Laser Event. An event where all lasers are fired
 */
public class LaserEvent implements Event{
    private static float runtime = 0.5f;

    public void startEvent(float delay) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                MainGame.movementHandler.fireAllLasers();
                System.out.println("Shooting Lasers");
            }
        }, delay);
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}