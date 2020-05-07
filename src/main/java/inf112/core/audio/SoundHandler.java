package inf112.core.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class SoundHandler {
    public float musicVolume;
    public float soundVolume;

    private Music backgroundMusic;

    public SoundHandler() {
        this.musicVolume = 0.5f;
        this.soundVolume = 0.7f;
    }

    public void setBackgroundMusic(SoundStore soundStore) {
        this.backgroundMusic = Gdx.audio.newMusic(soundStore.fileHandle);
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(musicVolume);
    }

    public void playSound(SoundStore soundStore) {
        Sound sound = Gdx.audio.newSound(soundStore.fileHandle);
        sound.play(soundVolume);
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
