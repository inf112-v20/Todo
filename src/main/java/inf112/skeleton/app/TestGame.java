package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

import javax.print.attribute.standard.OrientationRequested;

public class TestGame extends InputAdapter implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    TiledMap tiledMap;
    TiledMapTileLayer boardLayer, playerLayer, holeLayer, flagLayer;
    OrthogonalTiledMapRenderer mapRenderer;
    OrthographicCamera camera;
    Cell playerCell, playerDiedCell, playerWonCell;
    Vector2 playerPosition;
    Texture texture;
    TextureRegion[][] textureRegion;
    StaticTiledMapTile staticTiledMapTile;
    final int MAP_SIZE_X = 5;
    final int MAP_SIZE_Y = 5;
    final int TILE_SIZE = 300;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        tiledMap = new TmxMapLoader().load("testmap.tmx");
        boardLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Board");
        playerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Player");
        holeLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Hole");
        flagLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Flag");
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
        batch.dispose();
        font.dispose();
        texture.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        mapRenderer.render();
        playerLayer.setCell((int)playerPosition.x, (int)playerPosition.y, playerCell);
    }

    @Override
    public boolean keyUp(int keyCode) {
        return false;
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.DOWN) {
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, null);
            playerPosition.set(playerPosition.x, playerPosition.y - 1);
        }
        if (keyCode == Input.Keys.UP) {
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, null);
            playerPosition.set(playerPosition.x, playerPosition.y + 1);
        }
        if (keyCode == Input.Keys.LEFT) {
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, null);
            playerPosition.set(playerPosition.x - 1, playerPosition.y);
        }
        if (keyCode == Input.Keys.RIGHT) {
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, null);
            playerPosition.set(playerPosition.x + 1, playerPosition.y);
        }
        return false;
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
