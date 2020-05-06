package inf112.core.screens.multiplayerscreens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import inf112.core.screens.IGameStateSwitcher;

public class MultiplayerScreenHost implements Screen {

    private IGameStateSwitcher gameStateSwitcher;
    private Stage mainmenuStage;
    private TextButton join;
    private TextButton host;
    private TextButton back;


    public MultiplayerScreenHost(IGameStateSwitcher gameStateSwitcher, String name){
        this.gameStateSwitcher = gameStateSwitcher;

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
