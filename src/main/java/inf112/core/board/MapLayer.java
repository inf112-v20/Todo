package inf112.core.board;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.jetbrains.annotations.NotNull;

/**
 * MapLayer enum used for easily adding new layers
 *
 * @author Alvar
 */
public enum MapLayer {
    BASE_LAYER("Base"),
    PLAYER_LAYER("Player"),
    COLLIDABLE_LAYER("Collidable"),
    ACTIVATABLE_LAYER("Activatable"),
    VOID_LAYER("Void"),
    LASER_LAYER("Laser"),
    BACKUP_LAYER("Backup"),
    SPAWN_LAYER("Spawn"),
    FLAG_LAYER("Flag"),
    CONVEYOR_LAYER("Conveyor"),
    GEAR_LAYER("Gear"),
    WRENCH_LAYER("Wrench")
    ;

    private String name;

    MapLayer(@NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
