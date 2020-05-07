package inf112.core.multiplayer;

import inf112.core.multiplayer.lowlevel.ClientData;
import inf112.core.multiplayer.lowlevel.ClientRunnable;
import inf112.core.multiplayer.lowlevel.ServerRunnable;

/**
 * This class acts as the interface between the game logic and the server network logic.
 * A human player declaring him- or herself a host should utilize this class.
 */
public class HostNetworkInterface {

    private static boolean started;
    private static ServerRunnable server;
    private static Thread threadServer;

    private HostNetworkInterface() {
        throw new IllegalStateException("HostNetworkInterface has been instantiated.");
    }

    public static void startServerThread() {
        server = new ServerRunnable();
        threadServer = new Thread(server);

        threadServer.setName("Server");
        threadServer.start();
        started = true;
    }

    public static void stop() {
        if (started) {
            server.close();
            threadServer.interrupt();
            System.out.println("HostNetworkInterface stopped.");
        }
    }
}
