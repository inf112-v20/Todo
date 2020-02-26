package inf112.core.board;

import org.jetbrains.annotations.NotNull;

public enum MapLayer {
    BASE_LAYER("Base"),
    PLAYER_LAYER("Player"),
    COLLIDABLE_LAYER("Collidable"),
    LASER_LAYER("Laser"),
    BACKUP_LAYER("Backup"),
    SPAWN_LAYER("Spawn"),
    ;

    private String name;

    MapLayer(@NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
