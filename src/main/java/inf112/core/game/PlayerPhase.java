package inf112.core.game;

import inf112.core.cards.ProgramCard;
import inf112.core.player.Player;
import inf112.core.player.PlayerHandler;
import org.javatuples.Pair;

import java.util.List;

public class PlayerPhase implements Phase{

    private List<Pair<Player, ProgramCard>> queuedMoves;
    private PlayerHandler playerHandler;

    private float delay;
    private static float runtime;

    public PlayerPhase(PlayerHandler playerHandler, float delay) {
        this.playerHandler = playerHandler;
        this.delay = delay;
        instantiatePhase();
    }

    private void instantiatePhase() {

    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}
