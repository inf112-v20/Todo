package inf112.core.game.phase;

import inf112.core.cards.ProgramCard;
import inf112.core.game.MainGame;
import inf112.core.game.event.Event;
import inf112.core.game.event.PlayerEvent;
import inf112.core.player.Player;
import org.javatuples.Pair;

import java.util.*;

public class PlayerPhase implements Phase {

    private List<Pair<Player, ProgramCard>> queuedMoves;
    private Queue<Event> events;

    private static float runtime;

    public PlayerPhase() {
        this.events = new LinkedList<>();
    }

    private List<Pair<Player, ProgramCard>> getSortedMoves() {
        List<Pair<Player, ProgramCard>> moves = new ArrayList<>();
        for(Player player : MainGame.playerHandler.getPlayers()) {
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

    private void setupEvents() {
        for(Pair<Player, ProgramCard> move : queuedMoves) {
            Event newPlayerEvent = new PlayerEvent(move.getValue0());
            events.add(newPlayerEvent);
        }
    }

    @Override
    public void startPhase(float delay) {
        MainGame.playerHandler.nextCard();
        this.queuedMoves = getSortedMoves();
        setupEvents();
        setRuntime();

        float eventDelay = delay;
        while(!events.isEmpty()) {
            Event event = events.poll();
            event.startEvent(eventDelay);
            eventDelay += event.getRuntime();
        }
    }

    private void setRuntime() {
        float rTime = 0f;
        for(Event event : events) {
            rTime += event.getRuntime();
        }
        runtime = rTime;
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}
