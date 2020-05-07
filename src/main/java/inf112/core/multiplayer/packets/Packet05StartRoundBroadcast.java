package inf112.core.multiplayer.packets;

import inf112.core.cards.Card;

import java.util.List;

/**
 * The server broadcasts this packet to all clients when a new round is about to begin.
 * Clients should interpret this as the beginning of the card programming phase.
 */
public class Packet05StartRoundBroadcast {

    public List<Card> dealtCards;    // the player/client is dealt these cards
    // TODO timer field (when should the program sheet be back)

    public Packet05StartRoundBroadcast() {}
}
