package inf112.core.multiplayer.lowlevel;

public class RunNetwork {

    public static void main(String[] args) {
        Thread serverThread = new Thread(new ServerRunnable());
        Thread clientThread = new Thread(new ClientRunnable());

        serverThread.start();
        clientThread.start();
    }

}
