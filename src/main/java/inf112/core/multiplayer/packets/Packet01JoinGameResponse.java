package inf112.core.multiplayer.packets;

/**
 * A server sends this to any client who just requested to join the game.
 */
public class Packet01JoinGameResponse {

    public boolean hitServer;          // this flag indicates whether the sender is a server or not
    public boolean nameValidated;      // this flag indicates whether or not the client provided a valid name

    public Packet01JoinGameResponse() {}

    public Packet01JoinGameResponse(boolean hitServer, boolean validName) {
        this.hitServer = hitServer;
        this.nameValidated = validName;
    }

}
