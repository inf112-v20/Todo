package inf112.core.multiplayer.notused;

import inf112.core.multiplayer.lowlevel.ClientData;
import inf112.core.multiplayer.lowlevel.ClientRunnable;

/**
 * This class acts as the interface between the game logic and the client network logic.
 * Every human player wanting to play multiplayer (both the host and clients) should instantiate this class.
 */
public class ClientNetworkInterface {

    private Thread threadClient;
    private ClientRunnable client;

    public ClientNetworkInterface() {}

    public void setServerIP(String serverIP) {
        ClientData.serverIP = serverIP;
    }

    // serverIP should be correctly set when this is called
    public void startClientThread(String serverIP) {
        this.client = new ClientRunnable();
        threadClient = new Thread(client);
        threadClient.start();
    }

    public void attemptToJoinServer(String proposedName) {
        ClientData.playerName = proposedName;
        client.sendJoinGameRequest();
    }

    public boolean isConnectedToServer() {
        return client.isConnected();
    }

}
