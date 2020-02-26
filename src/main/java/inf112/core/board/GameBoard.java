package inf112.core.board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.core.player.Player;
import inf112.skeleton.app.TestGame;

public class GameBoard extends LayeredBoard {

    public GameBoard(){
        makeBoard();
    }

    public Boolean onBoard(Player player){
        int playerX = player.getX();
        int playerY = player.getY();
        if (playerY < 0 || playerY >= players.getHeight()) { return false; }
        else if (playerX < 0 || playerX >= players.getWidth()) { return false; }
        else return true;
    }
}
