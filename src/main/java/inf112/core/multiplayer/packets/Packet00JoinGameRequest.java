package inf112.core.multiplayer.packets;

/**
 * A client sends this packet to a server once the connection is established.
 * It represents a wish to join the game party.
 * The client should put forth a name
 */
public class Packet00JoinGameRequest {

    public String playerName;

    public Packet00JoinGameRequest() {}

    @Override
    public String toString() {
        return playerName;
    }
}
