package inf112.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import inf112.core.game.MainGame;
import inf112.core.movement.MovementHandler;

public class InputController extends InputAdapter {

    private MainGame game;
    private MovementHandler movementHandler;
    private InputAdapter debugControls;
    private boolean debugMode = false;

    public InputController(MainGame game) {
        this.game = game;
        this.movementHandler = game.getMovementHandler();
        this.debugControls = new DebuggingController(game);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.Z:
                toggleDebugControls();
                break;
            default:
                return false;
        }
        return true;
    }

    private void toggleDebugControls() {
        if(debugMode) {
            debugMode = false;
            ((InputMultiplexer) Gdx.input.getInputProcessor()).removeProcessor(debugControls);
        } else {
            debugMode = true;
            ((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(debugControls);
        }
    }
}
