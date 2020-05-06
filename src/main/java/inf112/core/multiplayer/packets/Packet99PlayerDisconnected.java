package inf112.core.multiplayer.packets;

/**
 * A server broadcasts this when a connection is lost. Clients should take care of the disconnected
 * player somehow.
 */
public class Packet99PlayerDisconnected {

    public String playerName;

    public Packet99PlayerDisconnected() {}
}
