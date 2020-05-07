package inf112.core.multiplayer.lowlevel;

import com.esotericsoftware.kryonet.Connection;
import inf112.core.cards.register.ProgramSheet;
import com.esotericsoftware.minlog.Log;

import java.util.*;

public abstract class ServerData {

    // a server should set this field
    static String IP;

    // game oriented fields
    static int connectionsCount;
    static boolean hasGameBegun;                                      // if game has begun then no more players can join
    static Map<String, Connection> playerNameToConnectionMap;
    static Map<Connection, String> connectionToPlayerNameMap;
    static List<String> playerNames;                                  // all players
    static int aiCount;                                               // so that all clients creates the same amount of AIs

    // round oriented fields
    static Set<String> playersReady;                                  // players that have confirmed their readiness
    static Set<ProgramSheet> programSheets;                           // received program sheets
    static Set<String> programSheetNames;                             // name of the received program sheets


    static void initFields() {
        connectionsCount = 0;
        hasGameBegun = false;
        playerNameToConnectionMap = new HashMap<>();
        connectionToPlayerNameMap = new HashMap<>();
        playerNames = new ArrayList<>();
        aiCount = 0;
        playersReady = new HashSet<>();
        programSheets = new HashSet<>();
        programSheetNames = new HashSet<>();
    }

    static void registerConnection(Connection c, String playerName) {
        playerNames.add(playerName);
        playerNameToConnectionMap.put(playerName, c);
        connectionToPlayerNameMap.put(c, playerName);
        connectionsCount++;
    }

    static void removeConnection(Connection c) {
        String nameOfDCPlayer = connectionToPlayerNameMap.get(c);

        if (!playerNames.contains(nameOfDCPlayer) || !playerNameToConnectionMap.containsKey(playerNames))    // for debugging
            Log.debug("[server] ERROR: Inconsistent player/connection data");

        playerNames.remove(nameOfDCPlayer);
        playerNameToConnectionMap.remove(nameOfDCPlayer);
        connectionToPlayerNameMap.remove(c);
        connectionsCount--;

        playersReady.remove(nameOfDCPlayer);
        programSheets.removeIf(programSheet -> programSheet.playerName.equals(nameOfDCPlayer));
        programSheetNames.remove(nameOfDCPlayer);
    }

    static Set<String> extractNamesFromProgramSheets() {
        Set<String> playerNames = new HashSet<>();
        for (ProgramSheet programSheet : programSheets)
            playerNames.add(programSheet.playerName);

        return playerNames;
    }

}
