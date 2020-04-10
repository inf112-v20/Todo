package inf112.core.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import inf112.core.game.MainGame;
import inf112.core.screens.GameScreen;

public class OrthographicCameraController implements InputProcessor {

    OrthographicCamera camera;
    MainGame game;

    public OrthographicCameraController(MainGame game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int i) {
        switch (i) {
            case Input.Keys.W:
                game.getBoard().moveCamera(0, -1);
                break;
            case Input.Keys.D:
                game.getBoard().moveCamera(1, 0);
                break;
            case Input.Keys.S:
                game.getBoard().moveCamera(0, 1);
                break;
            case Input.Keys.A:
                game.getBoard().moveCamera(-1, 0);
                break;
            case Input.Keys.PERIOD:
                game.getBoard().zoomCamera(-1);
                break;
            case Input.Keys.MINUS:
                game.getBoard().zoomCamera(1);
                break;
            default:
                return false;

        }
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        game.getBoard().moveCamera(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
        return true;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int dir) {
        game.getBoard().zoomCamera(dir);
        return true;
    }
}
