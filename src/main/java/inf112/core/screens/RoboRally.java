package inf112.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import inf112.core.screens.multiplayerscreens.MultiplayerHostStandby;
import inf112.core.screens.multiplayerscreens.MultiplayerScreen;
import inf112.core.screens.multiplayerscreens.MultiplayerScreenJoin;
import inf112.core.screens.multiplayerscreens.MultiplayerScreenPlayerName;
import inf112.core.screens.multiplayerscreens.*;
import inf112.core.util.AssMan;

public class RoboRally extends Game implements IGameStateSwitcher {
    @Override
    public void initMainMenu() {
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void initMainGame() {
        setScreen(new GameScreen(this));
    }

    @Override
    public void initGameOver() {
        setScreen(new GameOverScreen(this));
    }

    @Override
    public void initMultiplayerSettings() {
        setScreen(new MultiplayerScreen(this));
    }

    @Override
    public void initMultiplayerJoin() {
        setScreen(new MultiplayerScreenJoin(this));
    }

    @Override
    public void initMultiplayerHost() {
        setScreen(new MultiplayerHostStandby(this));
    }

    @Override
    public void initMultiplayerStandby() {
        setScreen(new MultiplayerJoinStandby(this));
    }

    @Override
    public void initMultiplayerPlayername() {
        setScreen(new MultiplayerScreenPlayerName(this));
    }
    @Override
    public void initSelectScreen() {
        setScreen(new SelectmapScreen(this));
    }

    @Override
    public void closeApplication() {
        Gdx.app.exit();
    }

    @Override
    public void create() {
        AssMan.load();
        AssMan.manager.finishLoading();
        initMainMenu();
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
        AssMan.dispose();
        super.dispose();
    }
}
