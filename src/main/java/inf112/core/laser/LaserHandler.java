package inf112.core.laser;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import inf112.core.board.GameBoard;
import inf112.core.board.MapLayer;
import inf112.core.player.Player;
import inf112.core.tile.TileId;
import inf112.core.util.LayerOperation;
import inf112.core.player.PlayerBackup;

import java.util.List;

public class LaserHandler {

    private TiledMapTileLayer laserLayer;
    private LaserPositions laserPositions;
    private Cell vertical, horizontal, crossed;


    public LaserHandler(GameBoard board, List<Player> players) {
        this.laserLayer = board.getLayer(MapLayer.LASER_LAYER);
        this.laserPositions = new LaserPositions(board, players);

        this.vertical = new Cell();
        this.horizontal = new Cell();
        this.crossed = new Cell();

        vertical.setTile(board.getTile(TileId.SINGLE_LASER_VERTICAL));
        horizontal.setTile(board.getTile(TileId.SINGLE_LASER_HORIZONTAL));
        crossed.setTile(board.getTile(TileId.SINGLE_LASER_CROSS));
    }

    public void fireLasersVisually() {
        // update laser positions positions
        laserPositions.gatherAllLaserPositions();

        // draw all vertical lasers
        for (Vector2 pos : laserPositions.getAllVerticalLaserPositions())
            LayerOperation.draw(laserLayer, (int) pos.x, (int) pos.y, vertical);

        // draw all horizontal lasers
        for (Vector2 pos : laserPositions.getAllHorizontalLaserPositions())
            LayerOperation.draw(laserLayer, (int) pos.x, (int) pos.y, horizontal);

        // draw all crossed lasers
        for (Vector2 pos : laserPositions.getAllCrossedLaserPositions())
            LayerOperation.draw(laserLayer, (int) pos.x, (int) pos.y, crossed);
    }

    public void dealDamageToAffectedPlayers() {
        // TODO gi Player-klassen en representasjon av HP, og bruk laserPositions.getHitPlayers()
        for (Player player: laserPositions.getHitPlayers().keySet()){
            player.addDamageToken(player.getDamageTokens(), laserPositions.getHitPlayers().get(player));
            System.out.println(player.getName() + " " + player.getDamageTokens());
        }
        laserPositions.getHitPlayers().clear();
    }

    public void disableLasersVisually() {
        LayerOperation.clear(laserLayer);
    }
}
