package inf112.core.game.event;

import com.badlogic.gdx.utils.Timer;
import inf112.core.game.MainGame;

/**
 * Conveyor Event. An event where all conveyors on the board moves a player if it is standing on said conveyor
 */
public class ConveyorEvent implements Event{
    private static float runtime = 0.1f;

    public void startEvent(float delay) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                MainGame.movementHandler.runConveyors();
                System.out.println("Copnveyors running");
            }
        }, delay);
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}
