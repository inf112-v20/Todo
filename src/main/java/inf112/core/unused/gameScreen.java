package inf112.core.unused;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class gameScreen implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    public final int MAP_SIZE_X = 5;             // #tiles in horizontal direction
    public final int MAP_SIZE_Y = 5;             // #tiles in vertical direction
    public final int TILE_SIZE = 16;

    @Override
    public void show() {
        map = new TmxMapLoader().load("maps/testmap2.tmx");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, MAP_SIZE_X, MAP_SIZE_Y);
        camera.position.set((float) MAP_SIZE_X/2, (float) MAP_SIZE_Y/2, 0);
        //camera.translate(MAP_SIZE_X/2,MAP_SIZE_Y/2);
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(map, (float) 2);
        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }

    private int mapPixelHeight() {
        int mapheight = map.getProperties().get("height", Integer.class);
        int tilePixels = map.getProperties().get("tileheight", Integer.class);
        return mapheight * tilePixels;
    }

    private int mapPixelWidth() {
        int mapheight = map.getProperties().get("width", Integer.class);
        int tilePixels = map.getProperties().get("tilewidth", Integer.class);
        return mapheight * tilePixels;
    }
}
