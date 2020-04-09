package inf112.core.camera;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class OrthographicCameraController implements InputProcessor {

    final OrthographicCamera camera;

    public OrthographicCameraController(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int i) {
        switch (i) {
            case Input.Keys.W:
                camera.translate(0, -1);
                break;
            case Input.Keys.D:
                camera.translate(1, 0);
                break;
            case Input.Keys.S:
                camera.translate(0, 1);
                break;
            case Input.Keys.A:
                camera.translate(-1, 0);
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
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        camera.zoom += i;
        return true;
    }
}
