package inf112.core.audio;

import com.badlogic.gdx.files.FileHandle;

public enum SoundStore {
    /**
     * Music
     */
    COFFIN_DANCE_MUSIC("assets/music/roboRally_coffindance.mp3"),

    /**
     * Sounds
     */
    //Menu
    BUTTON_CLICK("assets/sound/menu/button_click.mp3"),
    BUTTON_HOVER("assets/sound/menu/button_hover.mp3"),
    CARD_HOVER("assets/sound/menu/kort_hover.mp3"),
    CARD_CLICK("assets/sound/menu/kort_klikk.mp3"),
    GAME_START("assets/sound/menu/game_start.mp3"),
    PROGRAMSHEET_LOCKIN("assets/sound/menu/programsheet_lockin.mp3"),
    //Robot
    ROBOT_FALL("assets/sound/robot/robot_fall.mp3"),
    ROBOT_MOVE("assets/sound/robot/robot_move.mp3"),
    ROBOT_ROTATE("assets/sound/robot/robot_rotate.mp3"),
    ROBOT_POWERDOWN("assets/sound/robot/robot_powerdown.mp3"),
    ROBOT_HIT_1("assets/sound/robot/robot_hit_1.mp3"),
    ROBOT_HIT_2("assets/sound/robot/robot_hit_2.mp3"),
    ROBOT_HIT_3("assets/sound/robot/robot_hit_3.mp3"),
    ROBOT_HIT_4("assets/sound/robot/robot_hit_4.mp3"),
    ROBOT_HIT_5("assets/sound/robot/robot_hit_5.mp3"),
    //Laser
    LASER_FIRE_1("assets/sound/laser/laser_1.mp3"),
    LASER_FIRE_2("assets/sound/laser/laser_2.mp3")

    ;



    private String fileLocation;
    public FileHandle fileHandle;

    SoundStore(String fileLocation) {
        this.fileLocation = fileLocation;
        this.fileHandle = new FileHandle(fileLocation);
    }
}
