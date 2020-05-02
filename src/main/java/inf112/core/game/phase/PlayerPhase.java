package inf112.core.game.phase;

import com.badlogic.gdx.utils.Timer;
import inf112.core.cards.ProgramCard;
import inf112.core.game.MainGame;
import inf112.core.game.event.Event;
import inf112.core.game.event.PlayerEvent;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import inf112.core.player.PlayerHandler;
import inf112.core.screens.GameScreen;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayerPhase implements Phase {

    private List<Pair<Player, ProgramCard>> queuedMoves;
    private List<Event> events;
    private PlayerHandler playerHandler;
    private MovementHandler movementHandler;

    private static float runtime;

    public PlayerPhase(PlayerHandler playerHandler, MovementHandler movementHandler) {
        this.playerHandler = playerHandler;
        this.movementHandler = movementHandler;
        this.events = new ArrayList<>();
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
        for(Pair<Player, ProgramCard> move : queuedMoves) {
            Event newPlayerEvent = new PlayerEvent(move.getValue0(), movementHandler);
            events.add(newPlayerEvent);
        }
        System.out.print("Events: ");
        System.out.println(events);
    }

    private void setRuntime() {
        float rTime = 0f;
        for(Event event : events) {
            rTime += event.getRuntime();
        }
        this.runtime = rTime;
    }

    @Override
    public void startPhase(float delay) {
        playerHandler.nextCard();
        this.queuedMoves = getSortedMoves();
        System.out.print("Sorted Moves: ");
        System.out.println(queuedMoves);
        setEvents();
        setRuntime();

        GameScreen.cameraController.switchCameraMode();
        MainGame.playerHandler.setActivePlayer(queuedMoves.get(0).getValue0());

        float eventDelay = delay;
        for(Event event : events) {
            event.startEvent(eventDelay);
            eventDelay += event.getRuntime();
        }

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                GameScreen.cameraController.switchCameraMode();
            }
        }, eventDelay);
    }

    @Override
    public float getRuntime() {
        return runtime;
    }
}
