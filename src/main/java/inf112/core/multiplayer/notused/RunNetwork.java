package inf112.core.multiplayer.notused;

import inf112.core.multiplayer.lowlevel.ClientRunnable;
import inf112.core.multiplayer.lowlevel.ServerRunnable;

public class RunNetwork {

    public static void main(String[] args) {
        Thread serverThread = new Thread(new ServerRunnable());
        Thread clientThread = new Thread(new ClientRunnable());

        serverThread.start();
        clientThread.start();
    }

}
