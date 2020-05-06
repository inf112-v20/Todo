package inf112.core.multiplayer.packets;

import inf112.core.cards.register.ProgramSheet;

/**
 * When the player is done programming his/hers robot, the client should send this to the server.
 */
public class Packet06ProgramSheetData {

    public ProgramSheet programSheet;

    public Packet06ProgramSheetData() {}

}
