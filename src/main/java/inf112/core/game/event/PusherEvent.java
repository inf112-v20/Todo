package inf112.core.game.event;

import com.badlogic.gdx.utils.Timer;
import inf112.core.game.MainGame;
import inf112.core.game.round.Round;

/**
 * Pusher Event. An event where all pushers will push a player in its direction, if the round number is even or odd.
 */
public class PusherEvent implements Event{
    private static float runtime = 0.5f;

    public void startEvent(float delay) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                MainGame.movementHandler.pushPlayerInDirection(Round.roundCount);
                System.out.println("Pushers running");
            }
        }, delay);
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}
