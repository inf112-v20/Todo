package inf112.core.audio;

import com.badlogic.gdx.files.FileHandle;

public enum SoundStore {
    //Music
    COFFIN_DANCE_MUSIC("assets/music/roboRally_coffindance.mp3");


    private String fileLocation;
    public FileHandle fileHandle;

    SoundStore(String fileLocation) {
        this.fileLocation = fileLocation;
        this.fileHandle = new FileHandle(fileLocation);
    }
}
