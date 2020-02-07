package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;


public class TestGame extends InputAdapter implements ApplicationListener {
    private TiledMap tiledMap;
    private TiledMapTileLayer playerLayer;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Cell playerCell;
    private Vector2 playerPosition;
    private Texture texture;
    private TextureRegion[][] textureRegion;
    private StaticTiledMapTile staticTiledMapTile;

    public final int MAP_SIZE_X = 5;             // #tiles in horizontal direction
    public final int MAP_SIZE_Y = 5;             // #tiles in vertical direction
    public final int TILE_SIZE = 300;

    @Override
    public void create() {
        tiledMap = new TmxMapLoader().load("testmap.tmx");
        playerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Player");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, MAP_SIZE_X, MAP_SIZE_Y);
        camera.position.set((float) MAP_SIZE_X / 2, (float) MAP_SIZE_Y / 2,0);
        camera.update();
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, (float) 1/TILE_SIZE);
        mapRenderer.setView(camera);

        texture = new Texture("player.png");
        textureRegion = TextureRegion.split(texture, TILE_SIZE,TILE_SIZE);
        playerCell = new Cell();
        staticTiledMapTile = new StaticTiledMapTile(textureRegion[0][0]);
        playerCell.setTile(staticTiledMapTile);
        playerPosition = new Vector2(0,0);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void dispose() {
        texture.dispose();
        mapRenderer.dispose();
        tiledMap.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        mapRenderer.render();
        playerLayer.setCell((int)playerPosition.x, (int)playerPosition.y, playerCell);
    }

    @Override
    public boolean keyDown(int keyCode) {
        clearPlayerLayer();
        if (keyCode == Input.Keys.DOWN) {
            playerPosition.y -= 1;
        }
        if (keyCode == Input.Keys.UP) {
            playerPosition.y += 1;
        }
        if (keyCode == Input.Keys.LEFT) {
            playerPosition.x -= 1;
        }
        if (keyCode == Input.Keys.RIGHT) {
            playerPosition.x += 1;
        }
        return false;
    }

    private void clearPlayerLayer() {
        playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, null);
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
