package inf112.core.input;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import inf112.core.game.MainGame;
import inf112.core.movement.util.SpawnHandler;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import inf112.core.util.VectorMovement;

public class SpawnController implements InputProcessor {
    MainGame game;
    SpawnHandler spawnHandler;

    public SpawnController(MainGame game) {
        this.game = game;
        this.spawnHandler = game.getMovementHandler().getSpawnHandler();
    }

    @Override
    public boolean keyDown(int keycode) {
        Player activePlayer = spawnHandler.getActivePlayer();
        if (spawnHandler.getState().equals(SpawnHandler.SpawnState.SELECT_POSITION)) {
            Vector2 proposedSpawnPos = activePlayer.getArchiveMarkerCopy();
            switch (keycode) {
                case Input.Keys.NUMPAD_1:
                case Input.Keys.NUM_1:
                    VectorMovement.go(proposedSpawnPos, Direction.SOUTH);
                    VectorMovement.go(proposedSpawnPos, Direction.WEST);
                    break;
                case Input.Keys.NUMPAD_2:
                case Input.Keys.NUM_2:
                    VectorMovement.go(proposedSpawnPos, Direction.SOUTH);
                    break;
                case Input.Keys.NUMPAD_3:
                case Input.Keys.NUM_3:
                    VectorMovement.go(proposedSpawnPos, Direction.SOUTH);
                    VectorMovement.go(proposedSpawnPos, Direction.EAST);
                    break;
                case Input.Keys.NUMPAD_4:
                case Input.Keys.NUM_4:
                    VectorMovement.go(proposedSpawnPos, Direction.WEST);
                    break;
                case Input.Keys.NUMPAD_6:
                case Input.Keys.NUM_6:
                    VectorMovement.go(proposedSpawnPos, Direction.EAST);
                    break;
                case Input.Keys.NUMPAD_7:
                case Input.Keys.NUM_7:
                    VectorMovement.go(proposedSpawnPos, Direction.NORTH);
                    VectorMovement.go(proposedSpawnPos, Direction.WEST);
                    break;
                case Input.Keys.NUMPAD_8:
                case Input.Keys.NUM_8:
                    VectorMovement.go(proposedSpawnPos, Direction.NORTH);
                    break;
                case Input.Keys.NUMPAD_9:
                case Input.Keys.NUM_9:
                    VectorMovement.go(proposedSpawnPos, Direction.NORTH);
                    VectorMovement.go(proposedSpawnPos, Direction.EAST);
                    break;
                case Input.Keys.ENTER:
                    System.out.println("Position confirmed.");
                    spawnHandler.initRotationSelection();
                    return true;
                default:
                    //All other inputs should be locked while new position is being selected
                    return true;
            }
            if (spawnHandler.getAdjPositions().contains(proposedSpawnPos))    // i.e. proposed position is legal
                game.getMovementHandler().moveToPos(activePlayer, proposedSpawnPos);
            else {
                System.out.println("Cannot spawn player here.");
            }
        }
        else if (spawnHandler.getState().equals(SpawnHandler.SpawnState.SELECT_ROTATION)){ // state == SELECT_ROTATION
            switch (keycode) {
                case Input.Keys.NUMPAD_2:
                case Input.Keys.DOWN:
                    activePlayer.rotateTo(Direction.SOUTH);
                    break;
                case Input.Keys.NUMPAD_4:
                case Input.Keys.LEFT:
                    activePlayer.rotateTo(Direction.WEST);
                    break;
                case Input.Keys.NUMPAD_6:
                case Input.Keys.RIGHT:
                    activePlayer.rotateTo(Direction.EAST);
                    break;
                case Input.Keys.NUMPAD_8:
                case Input.Keys.UP:
                    activePlayer.rotateTo(Direction.NORTH);
                    break;
                case Input.Keys.ENTER:
                    if (game.getBoard().checkIfFacingAnotherPlayerWithin3squares(activePlayer)){
                        System.out.println("Can't choose this position cause your facing another player within three squares");
                    }
                    else {
                        ((InputMultiplexer) Gdx.input.getInputProcessor()).removeProcessor(this);
                        System.out.println("Rotation confirmed.");
                    }
                    break;
                default:
                    //All other inputs should be locked while new position is being selected
                    return true;
            }
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
        return false;
    }
}
