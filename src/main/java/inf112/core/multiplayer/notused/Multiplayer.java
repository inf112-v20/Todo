package inf112.core.multiplayer.notused;

import inf112.core.game.MainGame;
import inf112.core.multiplayer.lowlevel.ClientData;
import inf112.core.multiplayer.lowlevel.ClientRunnable;
import inf112.core.multiplayer.lowlevel.ServerRunnable;

public class Multiplayer {

    private Thread clientThread, serverThread;
    private ClientRunnable client;
    private ServerRunnable server;
    private boolean isHost;

    public static Multiplayer instance;

    private Multiplayer(boolean hosting) {
        System.out.println("Multiplayer instantiated");
        if (MainGame.isMultiplayer) {
            if (hosting) {

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
        this.isHost = hosting;
    }

    public static void newInstance(boolean isHost) {
        Multiplayer.instance = new Multiplayer(isHost);
    }

    public boolean hasServerIP() {
        return ClientData.serverIP != null;
    }

    public String getServerIP() {
        return ClientData.serverIP;
    }

    public void stop() {
        if (isHost) {
            server.close();
            serverThread.interrupt();
        }
        client.close();
        clientThread.interrupt();

        instance = null;

        System.out.println("Multiplayer stopped.");
    }
}
