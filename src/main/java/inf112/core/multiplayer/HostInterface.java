package inf112.core.multiplayer;

import inf112.core.multiplayer.lowlevel.ClientData;
import inf112.core.multiplayer.lowlevel.ClientRunnable;
import inf112.core.multiplayer.lowlevel.ServerRunnable;

/**
 * A human player declaring him- or herself a host should instantiate this class.
 * Such a player becomes both a client and a server.
 */
public class HostInterface {

    private Thread threadServer, threadClient;
    private ClientRunnable client;
    private ServerRunnable server;

    public HostInterface() {}

    public void startServerThread() {
        this.server = new ServerRunnable();
        this.threadServer = new Thread(server);
        this.threadServer.start();
    }

    public void startClientThread() {
        this.client = new ClientRunnable();
        this.threadClient = new Thread(client);
        this.threadClient.start();
    }

    public void attemptToJoinServer(String proposedName) {
        ClientData.playerName = proposedName;
        client.sendJoinGameRequest();
    }

}
