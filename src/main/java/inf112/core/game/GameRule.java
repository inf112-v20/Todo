package inf112.core.game;

import inf112.core.game.phase.BoardPhase;
import inf112.core.game.phase.CleanupPhase;
import inf112.core.game.phase.PlayerPhase;
import inf112.core.game.round.Round;
import inf112.core.game.round.RoundFactory;
import inf112.core.movement.MovementHandler;
import inf112.core.player.PlayerHandler;

public class GameRule {

    public static final int NUMBER_OF_ROUNDS = 5;

    public static Round generateDefaultRound(PlayerHandler playerHandler, MovementHandler movementHandler) {
        RoundFactory roundFactory = new RoundFactory();

        roundFactory.setAmountOfRounds(5);
        roundFactory.addPhase(new PlayerPhase());
        roundFactory.addPhase(new BoardPhase());
        roundFactory.addPhase(new CleanupPhase());

        return roundFactory.generate();
    }
}
