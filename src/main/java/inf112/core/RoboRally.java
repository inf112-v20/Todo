package inf112.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import inf112.core.screens.gameScreen;

public class RoboRally extends Game implements ApplicationListener {
    @Override
    public void create() {
        setScreen(new gameScreen());
    }

    @Override
    public void resize(int i, int i1) {
        super.resize(i, i1);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
