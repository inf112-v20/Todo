package inf112.core.game.event;

import com.badlogic.gdx.utils.Timer;
import inf112.core.game.event.Event;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;

/**
 * PlayerEvent. An event where a player is moved by their current programCard
 */
public class PlayerEvent implements Event {

    private Player player;
    private MovementHandler movementHandler;

    private static float runtime = 1f;

    public PlayerEvent(Player player, MovementHandler movementHandler) {
        this.player = player;
        this.movementHandler = movementHandler;
    }

    public void startEvent(float delay) {
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
