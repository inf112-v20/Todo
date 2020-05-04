package inf112.core.multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import inf112.core.cards.register.ProgramSheet;
import inf112.core.multiplayer.packets.Packet00JoinGameRequest;
import inf112.core.multiplayer.packets.Packet01JoinGameResponse;
import inf112.core.multiplayer.packets.Packet04ProgramSheetData;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerThread implements Runnable {

    class ServerListener extends Listener {
        @Override
        public void connected(Connection connection) {
            Log.info("[server] Connection ip: " + connection.getRemoteAddressTCP().getAddress().getHostAddress());
        }

        @Override
        public void disconnected(Connection connection) {
            Log.info("[server] Disconnected.");
        }

        @Override
        public void received(Connection connection, Object o) {
            if (o instanceof Packet00JoinGameRequest) {
                // client reaches out for the first time, we should accept
                Packet00JoinGameRequest requestPacket = (Packet00JoinGameRequest) o;
                Log.info("[server] Registered client with name: " + requestPacket);

                // immediate response
                Packet01JoinGameResponse receivedPacket = new Packet01JoinGameResponse();
                receivedPacket.hitServer = true;
                receivedPacket.nameValidated = true;    // should actually validate name here

                connection.sendTCP(receivedPacket);
            }
            else if (o instanceof Packet04ProgramSheetData) {
                Packet04ProgramSheetData receivedPacket = (Packet04ProgramSheetData) o;
                ProgramSheet programSheet = receivedPacket.programSheet;

                // TODO handle received program sheet
            }
        }
    }

    Server server;
    Listener listener;

    @Override
    public void run() {
        listener = new ServerListener();

        server = new Server(Common.WRITE_BUFFER, Common.READ_BUFFER);
        server.addListener(listener);
        server.start();
        Common.registerClasses(server);

        Log.set(Log.LEVEL_DEBUG);

        try {
            server.bind(Common.PORT_TCP);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Log.info("[server] Server open.");

        try {
            InetAddress ip = InetAddress.getLocalHost();
            Log.info("[server] Your address is: " + ip.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Log.info("[server] Failed to detect your host address.");
            System.exit(1);
        }
    }
}
