package inf112.core.movement;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.core.player.Direction;
import inf112.core.player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayerCollisionHandlerTest {

    private TiledMapTileLayer playerLayer;
    private CollisionHandler collisionHandler;
    private Player player1, player2;

    @Before
    public void init() {
        player1 = new Player("Spiller1", new TextureRegion(), 0, 0);
        player2 = new Player("Spiller2", new TextureRegion(), 0 ,1);
        player1.setDirection(Direction.NORTH);
        player2.setDirection(Direction.EAST);

        playerLayer = new TiledMapTileLayer(5, 5, 100, 100);
        playerLayer.setCell(player1.getX(), player1.getY(), player1.getCell());
        playerLayer.setCell(player2.getX(), player2.getY(), player2.getCell());
        collisionHandler = new CollisionHandler();
    }

    @Test
    public void movingPlayerWillPushAdjacentPlayerTest() {
        boolean moved = collisionHandler.handleMoveBy(player1, player1.getDirection());
        assertTrue(moved);
    }

    @Test
    public void movingPlayerWillNotPushNonAdjacentPlayerTest() {
        player2.move(Direction.NORTH);
        boolean moved = collisionHandler.handleMoveBy(player1, player1.getDirection());
        assertFalse(moved);
    }

}
