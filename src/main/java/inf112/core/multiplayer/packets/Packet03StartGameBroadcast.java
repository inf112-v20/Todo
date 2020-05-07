package inf112.core.multiplayer.packets;

import inf112.core.player.Player;

import java.util.List;

/**
 * A server broadcasts this when all the player hosting the server is ready to start.
 *
 * All clients should instantiate the robot representation of the players in the order of the names, so
 * that the game starts off consistently.
 */
public class Packet03StartGameBroadcast {

    public List<String> names;     // names of all player names (selected by individual clients)
    public int aiCount;

    public Packet03StartGameBroadcast() {}
}
