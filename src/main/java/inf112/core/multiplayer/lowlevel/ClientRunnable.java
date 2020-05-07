package inf112.core.multiplayer.lowlevel;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import inf112.core.cards.register.ProgramSheet;
import inf112.core.multiplayer.packets.*;

import java.io.IOException;
import java.util.List;

public class ClientRunnable implements Runnable {

    private class ClientListener extends Listener {
        @Override
        public void connected(Connection connection) {
            Log.info("[client] Connection ip: " + connection.getRemoteAddressTCP().getAddress().getHostAddress());
            ClientData.hasConnected = true;

//            Packet00JoinGameRequest requestPacket = new Packet00JoinGameRequest();
//            requestPacket.playerName = "Eskil";
//
//            connection.sendTCP(requestPacket);
//            Log.info("[client] Join game request sent.");
        }

        @Override
        public void disconnected(Connection connection) {
            Log.info("[client] Disconnected.");
        }

        @Override
        public void received(Connection connection, Object o) {
            if (o instanceof Packet00JoinGameRequest) {
                // another client thinks we're the server, we should not accept
                Packet01JoinGameResponse receivedPacket = new Packet01JoinGameResponse();
                receivedPacket.hitServer = false;
                receivedPacket.nameValidated = false;

                connection.sendTCP(receivedPacket);
            }
            else if (o instanceof Packet01JoinGameResponse) {
                // we got a response to our Join game request
                Packet01JoinGameResponse receivedPacket = (Packet01JoinGameResponse) o;

                if (receivedPacket.hitServer) {
                    if (receivedPacket.nameValidated)
                        Log.info("[client] The server registered your name.");
                    else
                        Log.info("[client] The server denied the name you provided.");
                }
                else {
                    Log.info("[client] Server denied your request! Perhaps you tried connection to a client?");
                    connection.close();
                }

            }
            else if (o instanceof Packet03StartGameBroadcast) {
                // server sent us all the players
                Packet03StartGameBroadcast playersPacket = (Packet03StartGameBroadcast) o;
                List<String> playerNames = playersPacket.names;
                int aiCount = playersPacket.aiCount;

                // TODO create players and AIs!

                System.out.println("Received these players:");
                for (String player : playerNames)
                    System.out.println(player);

                // players and Ai
            }
            else if (o instanceof Packet05StartRoundBroadcast) {
                // server basicly just started the card programming phase
                Packet06ProgramSheetData responseData = new Packet06ProgramSheetData();
                responseData.programSheet = new ProgramSheet();

                connection.sendTCP(responseData);
            }
        }
    }

    Client client;
    Listener listener;

    @Override
    public void run() {
        listener = new ClientListener();

        client = new Client(Common.WRITE_BUFFER, Common.READ_BUFFER);
        client.addListener(listener);
        new Thread(client).start();
        Common.registerClasses(client);

        Log.set(Log.LEVEL_DEBUG);

        try {
            client.connect(Common.TIMEOUT, ClientData.serverIP, Common.PORT_TCP);
        } catch (IOException e) {
            e.printStackTrace();
            Log.info("[client] Couldn't connect to server.");
        }

    }

    public void sendJoinGameRequest() {
        if (!client.isConnected())
            Log.debug("[client] You are not connected to any server.");

        Packet00JoinGameRequest requestPacket = new Packet00JoinGameRequest();
        requestPacket.playerName = ClientData.playerName;
        client.sendTCP(requestPacket);
    }
}
