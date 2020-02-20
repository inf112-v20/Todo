package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.core.player.Player;


public class TestGame implements ApplicationListener {
    private TiledMap tiledMap;
    private TiledMapTileLayer playerLayer;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Texture texture;
    private TextureRegion[][] textureRegions;
    private Player player;
    private int mapWidth, mapHeight;                   // #tiles in each direction
    private int tilePixelWidth, tilePixelHeight;


    @Override
    public void create() {
        // map setup and getting dimension info
        tiledMap = new TmxMapLoader().load("testmap.tmx");
        MapProperties properties = tiledMap.getProperties();
        mapWidth = properties.get("width", Integer.class);
        mapHeight = properties.get("height", Integer.class);
        tilePixelWidth = properties.get("tilewidth", Integer.class);
        tilePixelHeight = properties.get("tileheight", Integer.class);

        // camera setup
        camera = new OrthographicCamera();
        camera.setToOrtho(false, mapWidth, mapHeight);
        camera.position.set((float) mapWidth / 2, (float) mapHeight / 2,0);    // centers the camera
        camera.update();

        // tileMapRenderer setup
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, (float) 1/tilePixelHeight);
        mapRenderer.setView(camera);

        // player setup
        playerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Player");
        texture = new Texture("player.png");
        textureRegions = TextureRegion.split(texture, tilePixelWidth, tilePixelHeight);
        player = new Player(playerLayer, textureRegions[0][0]);
        Gdx.input.setInputProcessor(player);
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
        player.updateMyPosition();
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
