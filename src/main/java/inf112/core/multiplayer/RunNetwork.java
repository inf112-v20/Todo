package inf112.core.multiplayer;

public class RunNetwork {

    public static void main(String[] args) {
        Thread serverThread = new Thread(new ServerThread());
        Thread clientThread = new Thread(new ClientThread());

        serverThread.start();
        clientThread.start();
    }

}
