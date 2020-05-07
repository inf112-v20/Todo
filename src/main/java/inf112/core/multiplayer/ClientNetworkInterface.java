package inf112.core.multiplayer;

import inf112.core.multiplayer.lowlevel.ClientData;
import inf112.core.multiplayer.lowlevel.ClientRunnable;

import java.util.List;

/**
 * This class acts as the interface between the game logic and the client network logic.
 * Every human player wanting to play multiplayer (both the host and clients) should utilize this class.
 */
public class ClientNetworkInterface {

    private static boolean started = false;
    private static Thread threadClient;
    private static ClientRunnable client;

    private ClientNetworkInterface() {
        throw new IllegalStateException("ClientNetworkInterface has been instantiated.");
    }

    /**
     * Starts the client network thread.
     * Should only be called if confident that the IP address of the server is set.
     */
    public static void startClientThread() {
        client = new ClientRunnable();
        threadClient = new Thread(client);
        threadClient.start();
        started = true;

    }

    public static void stop() {
        if (started) {
            client.close();
            threadClient.interrupt();
            started = false;
            System.out.println("Client network interface stopped");
        }
    }

    public static boolean isReady() {
        return client.isReady();
    }

    public static boolean isConnected() {
        return client.isConnected();
    }

    public static boolean hasServerIP() {
        return ClientData.serverIP != null;
    }

    public static String getServerIP() {
        return ClientData.serverIP;
    }

    public static void setServerIP(String serverIP) {
        ClientData.serverIP = serverIP;
    }

    public static List<String> getConnectedPlayerNames() {
        return ClientData.joinedPlayers;
    }

    public static void attemptToJoinServer(String proposedName) {
        ClientData.playerName = proposedName;
        client.sendJoinGameRequest();
    }

    public static boolean hasJoinedServer() {
        return ClientData.connectionConfirmedByServer;
    }



}
