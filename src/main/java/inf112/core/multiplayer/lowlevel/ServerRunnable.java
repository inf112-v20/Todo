package inf112.core.multiplayer.lowlevel;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import inf112.core.cards.register.ProgramSheet;
import inf112.core.multiplayer.packets.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerRunnable implements Runnable {

    private class ServerListener extends Listener {
        @Override
        public void connected(Connection connection) {
            Log.info("[server] Connection ip: " + connection.getRemoteAddressTCP().getAddress().getHostAddress());
        }

        @Override
        public void disconnected(Connection connection) {
            if (!ServerData.connectionToPlayerNameMap.containsKey(connection)) {
                Log.info("[server] Unregistered connection disconnected.");
                return;
            }

            // we need to inform the other clients of this client's disconnection
            Packet99PlayerDisconnected packet = new Packet99PlayerDisconnected();
            packet.playerName = ServerData.connectionToPlayerNameMap.get(connection);
            server.sendToAllTCP(packet);

            // the disconnected client was registered as a player, let's update our data
            ServerData.removeConnection(connection);

            Log.info("[server] Disconnected to " + packet.playerName);
        }

        @Override
        public void received(Connection connection, Object o) {
            if (o instanceof Packet00JoinGameRequest) {
                if (ServerData.hasGameBegun)
                    connection.close();    // close any new connections after game start

                Packet00JoinGameRequest requestPacket = (Packet00JoinGameRequest) o;
                respondToGameRequest(connection, requestPacket.playerName);
                return;
            }

            // at this point, we should first verify the connection
            if (!ServerData.connectionToPlayerNameMap.containsKey(connection)) {
                Log.debug("[server] Unregistered connection. Server will ignore.");
                return;
            }

            if (o instanceof Packet04StartGameReady) {
                // TODO register the ready player
                //      If all are ready, start the round
            }
            else if (o instanceof Packet06ProgramSheetData) {
                Packet06ProgramSheetData receivedPacket = (Packet06ProgramSheetData) o;
                ProgramSheet programSheet = receivedPacket.programSheet;

                // TODO handle received program sheet.
                //      if received from all players (or timer is out), broadcast program sheets
            }
            else if (o instanceof Packet08NextRoundReady) {
                // TODO register the ready player
                //      If all are ready, start the round
            }
        }

        private void respondToGameRequest(Connection c, String nameProposed) {
            Packet01JoinGameResponse responsePacket = new Packet01JoinGameResponse();
            responsePacket.hitServer = true;

            if (nameProposed == "" || ServerData.playerNames.contains(nameProposed)) {    // request denied
                responsePacket.nameValidated = false;

                Log.info("[server] Rejected client: " + nameProposed);
            }
            else {                                                                        // request accepted
                responsePacket.nameValidated = true;

                // register client in our system
                ServerData.registerConnection(c, nameProposed);

                // inform all other clients of the new player
                Packet02NewPlayerBroadcast newPlayerPacket = new Packet02NewPlayerBroadcast();
                newPlayerPacket.playerName = nameProposed;
                server.sendToAllExceptTCP(c.getID(), newPlayerPacket);

                Log.info("[server] Accepted client: " + nameProposed);
            }
            c.sendTCP(responsePacket);
        }

        private void handleStartGameReady(Connection c) {
            ServerData.playersReady.add(ServerData.connectionToPlayerNameMap.get(c));

            if (ServerData.playersReady.containsAll(ServerData.playerNames)) {
                // all registered players are ready
                ServerData.playersReady.clear();
                ServerData.hasGameBegun = true;
                // TODO START THE GAME
                //      solve card dealing problem

            }

        }

        private void handleProgramSheetData(Connection c, ProgramSheet programSheet) {
            if (!ServerData.playerNames.contains(programSheet.playerName)) {
                Log.debug("[server] ERROR: Name on received program sheet inconsistent with registered client name. " +
                        "Server will ignore.");
                return;
            }
            ServerData.programSheets.add(programSheet);
            ServerData.programSheetNames.add(programSheet.playerName);

            if (ServerData.programSheetNames.containsAll(ServerData.playerNames)) {
                // all players have delivered their program sheet in time
                // TODO broadcast program sheets to all clients
            }
        }

        private void handleNextRoundReady(Connection c) {
            ServerData.playersReady.add(ServerData.connectionToPlayerNameMap.get(c));

            if (ServerData.playersReady.containsAll(ServerData.playerNames)) {
                // all players are ready for the next round
                ServerData.playersReady.clear();
                // TODO start a new round
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
        ServerData.initFields();

        Log.set(Log.LEVEL_DEBUG);

        try {
            server.bind(Common.PORT_TCP);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Log.info("[server] Server open.");

        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            Log.info("[server] Your address is: " + ip);
            ServerData.IP = ip;
            ClientData.serverIP = ip;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Log.info("[server] Failed to detect your host address.");
            System.exit(1);
        }
    }


    public void broadcastPlayerNames() {
        Packet03StartGameBroadcast packet = new Packet03StartGameBroadcast();
        packet.names = ServerData.playerNames;
        server.sendToAllTCP(packet);
    }
}
