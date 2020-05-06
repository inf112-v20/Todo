package inf112.core.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.core.board.GameBoard;
import inf112.core.board.MapLayer;
import inf112.core.board.MapNames;
import inf112.core.game.round.RoundHandler;
import inf112.core.input.InputController;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import inf112.core.cards.CardFactory;
import inf112.core.cards.Deck;
import inf112.core.player.PlayerHandler;
import inf112.core.screens.GameScreen;
import inf112.core.util.LayerOperation;

import java.util.List;

public class MainGame {

    public static final int MAX_DAMAGE_TOKENS_LIMIT = 10;    // a player should not be able to receive more damage tokens
    public static int playerLimit = 0;
    public static MovementHandler movementHandler;
    public static PlayerHandler playerHandler;

    private GameScreen gameScreen;
    private GameBoard board;
    private InputAdapter defaultInputAdapter;
    private Texture playerSpriteSheet;
    private TextureRegion[][] playerSpriteSheetGrid;
    private RoundHandler roundHandler;
    private Deck deck;
    private static Player winner;

    public MainGame(MapNames mapNames) {
        this.playerHandler = new PlayerHandler(this);
        this.board = new GameBoard(mapNames, playerHandler);
        playerLimit = board.getSpawns().size();
        playerLimit = 2;
        this.roundHandler = new RoundHandler(this);
        this.movementHandler = new MovementHandler(this);
        this.deck = new Deck(CardFactory.createDefaultDeck());

        this.defaultInputAdapter = new InputController(this);
        playerSpriteSheet = new Texture("img/Player_Spritesheet.png");
        playerSpriteSheetGrid = TextureRegion.split(
                playerSpriteSheet,
                board.getTileWidthInPixels(),
                board.getTileHeightInPixels()
        );

    }

    public MainGame() {
        this(MapNames.TESTING_MAP);
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setGameScreen(GameScreen gameScreen) {
       this.gameScreen = gameScreen;
    }

    public Screen getGameScreen() {
        return gameScreen;
    }

    public List<Player> getPlayers() { return playerHandler.getPlayers(); }

    public RoundHandler getRoundHandler() { return roundHandler; }

    public MovementHandler getMovementHandler() {
        return movementHandler;    // this should of course change
    }

    public InputProcessor getDefaultInputProcessor() { return defaultInputAdapter; }

    public void drawPlayers() {
        for (Player player : playerHandler.getPlayers())
            LayerOperation.drawPlayer(board.getLayer(MapLayer.PLAYER_LAYER), player);
    }

    public void setActivePlayerById(int id) {
        playerHandler.setActivePlayerById(id);
    }

    public Player getPlayerById(int id) {
        return playerHandler.getPlayerById(id);
    }

    public void dispose() {
        board.dispose();
        playerSpriteSheet.dispose();
    }

    public Deck getDeck() {
        return deck;
    }

    public PlayerHandler getPlayerHandler() {
        return playerHandler;
    }

    public boolean hasLost(Player player) {
        return player.isDead() && player.isOutOfLifeTokes();
    }

    public void removeLosers() {
        playerHandler.getPlayers().removeIf(player -> hasLost(player));
    }

    public static void attemptToAppointWinner() {
        List<Player> players = playerHandler.getPlayers();
        if (players.size() == 1 && PlayerHandler.playerCount > 1 && !playerHandler.hasRevivablePlayers()) {    // all other players has lost
            winner = players.get(0);
            return;
        }
        for (Player player : players)
            if (movementHandler.getFlagWinnerChecker().hasVisitedAllFlags(player)) {
                winner = player;
                return;
            }
    }

    public boolean hasWon() {
        return this.winner != null;
    }

    public Player getActivePlayer() {
        return playerHandler.getActivePlayer();
    }

    public void setActivePlayer(Player player) {
        playerHandler.setActivePlayer(player);
    }

    public TextureRegion[][] getPlayerSpriteSheetGrid() {
        return playerSpriteSheetGrid;
    }
}
