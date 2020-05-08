package inf112.core.multiplayer.lowlevel;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
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
                    if (receivedPacket.nameValidated) {
                        ClientData.joinedPlayers = receivedPacket.joinedPlayers;
                        ClientData.connectionConfirmedByServer = true;

                        Log.info("[client] The server registered your name.");
                    }
                    else {
                        ClientData.connectionConfirmedByServer = false;

                        Log.info("[client] The server denied the name you provided.");
                    }
                }
                else {
                    connection.close();

                    Log.debug("[client] Did not hit server. Connection was closed.");
                }

            }
            else if (o instanceof Packet02NewPlayerBroadcast) {
                if (ClientData.hasGameBegun)
                    return;       // no new player should join after the game has started, so we should ignore

                Packet02NewPlayerBroadcast newPlayerPacket = (Packet02NewPlayerBroadcast) o;

                if (ClientData.playerName.equals(newPlayerPacket.playerName))
                    Log.debug("[client] ERROR: Server informed me about a new player with my name");

                ClientData.joinedPlayers.add(newPlayerPacket.playerName);
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

        ClientData.initialConnectionMade = attemptToConnect();
    }

    /**
     * Attempts to connect to the server based on the server IP given in ClientData.
     *
     * @return true if connection was made, and false if no such server was found or attempt timed out.
     */
    public boolean attemptToConnect() {
        try {
            client.connect(Common.TIMEOUT, ClientData.serverIP, Common.PORT_TCP);
        } catch (IOException e) {
            //e.printStackTrace();
            Log.info("[client] Couldn't connect to server.");
            return false;
        }
        return true;
    }

    /**
     * Prompts the server to join the game, based on the name given in ClientData.
     *
     * @return true if the packet is sent, and false if there is no connection to the server
     */
    public boolean sendJoinGameRequest() {
        if (!client.isConnected()) {
            Log.debug("[client] You are not connected to any server.");
            return false;
        }

        Packet00JoinGameRequest requestPacket = new Packet00JoinGameRequest();
        requestPacket.playerName = ClientData.playerName;
        client.sendTCP(requestPacket);
        return true;
    }

    /**
     * Closes the connection to the server
     */
    public void close() {
        client.close();
    }

    /**
     * @return true if it's safe to send packets to the server.
     */
    public boolean isReady() {
        return client != null && client.isConnected();
    }

    public boolean isConnected() {
        return client.isConnected();
    }
}
