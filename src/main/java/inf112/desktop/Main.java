package inf112.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import inf112.core.roborally.RoboRally;

public class Main {
    public static void main(String[] Args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Test2";
        cfg.width = 1280;
        cfg.height = 720;
        new LwjglApplication(new RoboRally(), cfg);
    }
}
