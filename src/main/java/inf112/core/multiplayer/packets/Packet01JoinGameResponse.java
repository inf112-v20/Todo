package inf112.core.multiplayer.packets;

import java.util.List;

/**
 * A server sends this to any client who just requested to join the game.
 */
public class Packet01JoinGameResponse {

    public boolean hitServer;             // this flag indicates whether the sender is a server or not
    public boolean nameValidated;         // this flag indicates whether or not the client provided a valid name
    public List<String> joinedPlayers;    // to inform player of all current connected players

    public Packet01JoinGameResponse() {}

}
