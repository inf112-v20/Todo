package inf112.core.multiplayer.lowlevel;

import java.util.List;

public abstract class ClientData {

    public static String serverIP;
    public static boolean initialConnectionMade = false;
    public static boolean connectionConfirmedByServer = false;
    public static boolean hasGameBegun = false;
    public static String playerName;
    public static List<String> joinedPlayers;

}
