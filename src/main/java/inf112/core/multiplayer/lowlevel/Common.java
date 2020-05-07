package inf112.core.multiplayer.lowlevel;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import inf112.core.multiplayer.packets.*;


public class Common {

    public static final int TIMEOUT = 2000;
    public static final int WRITE_BUFFER = 256 * 1024;
    public static final int READ_BUFFER = 256 * 1024;
    public static final int PORT_UDP = 56777;
    public static final int PORT_TCP = 56555;

    public static final String serverIP = "192.168.56.1";    // very temporary

    public static void registerClasses(EndPoint endpoint) {
        Kryo kryo = endpoint.getKryo();
        kryo.register(Packet00JoinGameRequest.class);
        kryo.register(Packet01JoinGameResponse.class);
        kryo.register(Packet02NewPlayerBroadcast.class);
        kryo.register(Packet03StartGameBroadcast.class);
        kryo.register(Packet04StartGameReady.class);
        kryo.register(Packet05StartRoundBroadcast.class);
        kryo.register(Packet06ProgramSheetData.class);
        kryo.register(Packet07ProgramSheetsDataBroadcast.class);
        kryo.register(Packet99PlayerDisconnected.class);
        kryo.register(java.util.ArrayList.class);
    }

}
