package inf112.core.multiplayer;

import inf112.core.multiplayer.lowlevel.ClientData;
import inf112.core.multiplayer.lowlevel.ClientRunnable;

/**
 * A human player wanting to connect to a another host should instantiate this class.
 */
public class ClientInterface {

    private Thread threadClient;
    private ClientRunnable client;

    public ClientInterface() {}

    public void setServerIP(String serverIP) {
        ClientData.serverIP = serverIP;
    }

    // serverIP should be correctly set when this is called
    public void startClientThread(String serverIP) {
        this.client = new ClientRunnable();
        threadClient = new Thread(client);
    }

    public void attemptToJoinServer(String proposedName) {
        ClientData.playerName = proposedName;
        client.sendJoinGameRequest();
    }

//    public boolean

}
