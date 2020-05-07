package inf112.core.multiplayer;

import inf112.core.multiplayer.lowlevel.ClientRunnable;
import inf112.core.multiplayer.lowlevel.ServerRunnable;

public class Multiplayer {

    public static boolean multiPlayer = false;
    public static boolean isHost = false;

    private Thread clientThread, serverThread;
    private ClientRunnable client;
    private ServerRunnable server;


    public Multiplayer() {
        if (multiPlayer) {
            if (isHost) {
                this.server = new ServerRunnable();
                this.serverThread = new Thread(server);

                serverThread.setName("Server");
                serverThread.start();
            }
            this.client = new ClientRunnable();
            this.clientThread = new Thread(client);

            clientThread.setName("Client");
            clientThread.start();
        }
    }
}
