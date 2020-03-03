package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.core.board.GameBoard;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;


public class GameScreen implements Screen {
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;
    private Texture texture;
    private TextureRegion[] textureRegions;
    private Player player1, player2, player3;
    private MovementHandler movementHandler;
    private int mapWidth, mapHeight;                   // #tiles in each direction
    private int tilePixelWidth, tilePixelHeight;
    private GameBoard board;


    @Override
    public void show() {
        // load the map and get dimension
        board = new GameBoard();
        MapProperties properties = board.getTiledmap().getProperties();
        mapWidth = properties.get("width", Integer.class);
        mapHeight = properties.get("height", Integer.class);
        tilePixelWidth = properties.get("tilewidth", Integer.class);
        tilePixelHeight = properties.get("tileheight", Integer.class);

        // set unit scale, how many pixels per world unit (1 unit == tilePixelHeight pixels)
        float unitScale = (float) 1/tilePixelHeight;
        mapRenderer = new OrthogonalTiledMapRenderer(board.getTiledmap(), unitScale);

        // camera setup
        camera = new OrthographicCamera();
        camera.setToOrtho(false, mapWidth, mapHeight);                           // show this many units of the world
        camera.position.set((float) mapWidth / 2, (float) mapHeight / 2,0);    // centers the camera
        camera.update();

        // player setup
        texture = new Texture("img/player.png");
        textureRegions = TextureRegion.split(texture, tilePixelWidth, tilePixelHeight)[0];
        player1 = new Player("Player1", textureRegions[0]);
        player2 = new Player("Player2", textureRegions[1]);
        player3 = new Player("Player3", textureRegions[2]);
        movementHandler = new MovementHandler(board);
        movementHandler.add(player1, player2, player3);
        movementHandler.setActive(player1);
        movementHandler.moveAllToSpawn();
        movementHandler.drawPlayers();

        Gdx.input.setInputProcessor(movementHandler);
    }

    @Override
    public void dispose() {
        texture.dispose();
        mapRenderer.dispose();
        board.dispose();
    }

    @Override
    public void hide() {
        dispose();    // hva er forskjellen på hide() og dispose() ??
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
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
