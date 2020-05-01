package inf112.core.game;

import com.badlogic.gdx.utils.Timer;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;

public class PlayerEvent implements Event{

    private Player player;
    private MovementHandler movementHandler;
    private float delay;

    private static float runtime = 1f;

    public PlayerEvent(Player player, MovementHandler movementHandler, float delay) {
        this.player = player;
        this.movementHandler = movementHandler;
        this.delay = delay;
    }

    public void startEvent() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                movementHandler.cardMovement(player, player.getCurrentCard());
            }
        }, delay);
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}
