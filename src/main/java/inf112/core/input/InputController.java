package inf112.core.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import inf112.core.game.MainGame;
import inf112.core.movement.MovementHandler;

public class InputController extends InputAdapter {

    private MainGame game;
    private MovementHandler movementHandler;

    public InputController(MainGame game) {
        this.game = game;
        this.movementHandler = game.getMovementHandler();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                movementHandler.attemptToMoveForward(game.getActivePlayer());
                break;
            case Input.Keys.DOWN:
                movementHandler.attemptToMoveBackward(game.getActivePlayer());
                break;
            case Input.Keys.LEFT:
                game.getActivePlayer().rotateLeft();
                break;
            case Input.Keys.RIGHT:
                game.getActivePlayer().rotateRight();
                break;
            case Input.Keys.C:
                game.getActivePlayer().setArchiveMarkerHere();
                break;
            case Input.Keys.SPACE:
                movementHandler.moveToBackup(game.getActivePlayer());
                break;
            case Input.Keys.T:
                movementHandler.runConveyors();
                movementHandler.gearsRotate();
                movementHandler.wrenchesRepair();
                movementHandler.pushPlayerInDirection(1);
                break;
            case Input.Keys.R:
                game.getRoundHandler().instantiateNextRound();
                break;
            case Input.Keys.L:
                movementHandler.fireAllLasers();
                break;
            default:
                return false;
        }
        game.removeLosers();    // strictly speaking unnecessary to call this upon every move
        game.attemptToAppointWinner();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.L:
                movementHandler.removeLasers();
                break;
            default:
                return false;
        }
        return true;
    }
}
