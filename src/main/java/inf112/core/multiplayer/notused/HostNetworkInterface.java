package inf112.core.multiplayer.notused;

import inf112.core.multiplayer.lowlevel.ClientData;
import inf112.core.multiplayer.lowlevel.ClientRunnable;
import inf112.core.multiplayer.lowlevel.ServerRunnable;

/**
 * This class acts as the interface between the game logic and the server network logic.
 * A human player declaring him- or herself a host should instantiate this class.
 */
public class HostNetworkInterface {

    private Thread threadServer;
    private ServerRunnable server;

    public HostNetworkInterface() {}

    public void startServerThread() {
        this.server = new ServerRunnable();
        this.threadServer = new Thread(server);
        this.threadServer.start();
    }
}
