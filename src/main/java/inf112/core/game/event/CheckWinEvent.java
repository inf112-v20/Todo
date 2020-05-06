package inf112.core.game.event;

import com.badlogic.gdx.utils.Timer;
import inf112.core.game.GameRule;
import inf112.core.game.MainGame;
import inf112.core.game.round.Round;

/**
 * CheckWinEvent. An event where all MainGame checks for a win condition.
 */
public class CheckWinEvent implements Event{
    private static float runtime = 0.1f;

    public void startEvent(float delay) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                System.out.println("Checking for winning player");
                MainGame.attemptToAppointWinner();
            }
        }, delay);
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}