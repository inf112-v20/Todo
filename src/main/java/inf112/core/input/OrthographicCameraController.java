package inf112.core.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import inf112.core.board.GameBoard;
import inf112.core.game.MainGame;

public class OrthographicCameraController implements InputProcessor {

    OrthographicCamera camera;
    MainGame game;
    GameBoard board;

    public OrthographicCameraController(MainGame game) {
        this.game = game;
        board = game.getBoard();
    }

    @Override
    public boolean keyDown(int i) {
        //cameraMode Regular
        if(!board.playerCamera) {
            switch (i) {
                case Input.Keys.W:
                    game.getBoard().moveCamera(0, -1);
                    return true;
                case Input.Keys.D:
                    game.getBoard().moveCamera(1, 0);
                    return true;
                case Input.Keys.S:
                    game.getBoard().moveCamera(0, 1);
                    return true;
                case Input.Keys.A:
                    game.getBoard().moveCamera(-1, 0);
                    return true;
            }
        }

        //CameraMode playerCamera
        if(board.playerCamera) {
            switch (i) {

            }
        }

        //Common functions for both cameraModes
        switch (i) {
            case Input.Keys.PERIOD:
                game.getBoard().zoomCamera(-1);
                return true;
            case Input.Keys.MINUS:
                game.getBoard().zoomCamera(1);
                return true;
            case Input.Keys.Q:
                switchCameraMode();
                return true;
            default:
                return false;
        }
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
        if(board.playerCamera)
            return false;

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

    private void switchCameraMode() {
        if(board.playerCamera)
            board.playerCamera = false;
        else
            board.playerCamera = true;
    }
}
