package inf112.core.game.event;

import com.badlogic.gdx.utils.Timer;
import inf112.core.game.MainGame;
import inf112.core.game.event.Event;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import inf112.core.screens.GameScreen;

/**
 * PlayerEvent. An event where a player is moved by their current programCard
 */
public class PlayerEvent implements Event {

    private Player player;

    private static float runtime = 1.5f;

    public PlayerEvent(Player player) {
        this.player = player;
    }

    public void startEvent(float delay) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                MainGame.movementHandler.cardMovement(player, player.getCurrentCard());
                System.out.println(player.getName() + " : " + player.getCurrentCard().getName());
            }
        }, delay);
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}
