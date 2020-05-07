package inf112.core.multiplayer.packets;

/**
 * A server broadcasts this (before the game has started) to inform the other clients of the new player.
 */
public class Packet02NewPlayerBroadcast {

    public String playerName;

    public Packet02NewPlayerBroadcast() {}
}
