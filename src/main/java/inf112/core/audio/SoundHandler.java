package inf112.core.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;

public class SoundHandler {
    public float musicVolume;
    public float soundVolume;

    private Music backgroundMusic;

    public SoundHandler() {
        this.musicVolume = 1f;
        this.soundVolume = 1f;
    }

    public void setBackgroundMusic(SoundStore soundStore) {
        this.backgroundMusic = Gdx.audio.newMusic(soundStore.fileHandle);
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(musicVolume);
    }

    public void playBackgroundMusic() {
        backgroundMusic.play();
    }

    public void pauseBackgroundMusic() {
        backgroundMusic.pause();
    }

    public void dispose() {
        backgroundMusic.dispose();
    }
}
