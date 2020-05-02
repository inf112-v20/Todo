package inf112.core.game.phase;

import inf112.core.cards.ProgramCard;
import inf112.core.game.event.Event;
import inf112.core.game.event.PlayerEvent;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import inf112.core.player.PlayerHandler;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayerPhase implements Phase {

    private List<Pair<Player, ProgramCard>> queuedMoves;
    private List<Event> events;
    private PlayerHandler playerHandler;
    private MovementHandler movementHandler;

    private float delay;
    private static float runtime;

    public PlayerPhase(PlayerHandler playerHandler, MovementHandler movementHandler, float delay) {
        this.playerHandler = playerHandler;
        this.movementHandler = movementHandler;
        this.delay = delay;
        setRuntime();
        this.queuedMoves = getSortedMoves();
        setEvents();
    }

    private List<Pair<Player, ProgramCard>> getSortedMoves() {
        List<Pair<Player, ProgramCard>> moves = new ArrayList<>();
        for(Player player : playerHandler.getPlayers()) {
            if(player.getCurrentCard() != null)
                moves.add(new Pair<>(player, player.getCurrentCard()));
        }
        moves.sort(new Comparator<Pair<Player, ProgramCard>>() {
            @Override
            public int compare(Pair<Player, ProgramCard> p1, Pair<Player, ProgramCard> p2) {
                return p1.getValue1().getPriority() - p2.getValue1().getPriority();
            }
        });
        return moves;
    }

    private void setEvents() {
        float eventDelay = delay;
        for(Pair<Player ,ProgramCard> move : queuedMoves) {
            Event newPlayerEvent = new PlayerEvent(move.getValue0(), movementHandler, eventDelay);
            events.add(newPlayerEvent);
            eventDelay += newPlayerEvent.getRuntime();
        }
    }

    private void setRuntime() {
        float rTime = 0f;
        for(Event event : events) {
            rTime += event.getRuntime();
        }
        this.runtime = rTime;
    }

    @Override
    public void startPhase() {
        for(Event event : events) {
            event.startEvent();
        }
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}
