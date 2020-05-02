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
    private MovementHandler movementHandler;

    private static float runtime = 1.5f;

    public PlayerEvent(Player player, MovementHandler movementHandler) {
        this.player = player;
        this.movementHandler = movementHandler;
    }

    public void startEvent(float delay) {
        MovementHandler movementHandler = this.movementHandler;
        System.out.println("Start playerEvent for " + player.getName());
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                System.out.println("Camera focus on " + player.getName());
                MainGame.playerHandler.setActivePlayer(player);
            }
        }, delay);
        delay += 0.8f;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {

                MainGame.movementHandler.cardMovement(player, player.getCurrentCard());
            }
        }, delay);
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}
