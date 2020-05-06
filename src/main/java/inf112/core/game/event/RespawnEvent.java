package inf112.core.game.event;

import com.badlogic.gdx.utils.Timer;
import inf112.core.game.GameRule;
import inf112.core.game.MainGame;
import inf112.core.game.round.Round;
import inf112.core.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Respawn Event. An event where all dead players are respawned if they have any lifetokens left.
 */
public class RespawnEvent implements Event{
    private static float runtime = 0.1f;

    public void startEvent(float delay) {
        if(Round.roundCount < GameRule.NUMBER_OF_ROUNDS)
            return;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                System.out.println("Respawning dead players");
                List<Player> playersToBeRespawned = new ArrayList<>();
                for(Player player : MainGame.playerHandler.getDisabledPlayers()) {
                    if(!player.isOutOfLifeTokes()) {
                        playersToBeRespawned.add(player);
                    }
                }
                for(Player player : playersToBeRespawned) {
                    player.resetDamageTokens();
                    MainGame.movementHandler.getSpawnHandler().initSpawning(player);
                }
            }
        }, delay);
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}
