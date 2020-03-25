package inf112.core.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.core.board.GameBoard;
import inf112.core.board.MapLayer;
import inf112.core.board.MapNames;
import inf112.core.movement.MovementHandler;
import inf112.core.player.Player;
import inf112.core.util.LayerOperation;

import java.util.ArrayList;
import java.util.List;

public class MainGame {

    public static final int MAX_DAMAGE_TOKENS_LIMIT = 10;    // a player should not be able to receive more damage tokens

    private GameBoard board;
    private MovementHandler movementHandler;
    private List<Player> players;
    private Texture playerSpriteSheet;
    private TextureRegion[][] playerSpriteSheetGrid;
    private RoundHandler roundHandler;
    private Player winner;

    public MainGame(MapNames mapNames) {
        this.players = new ArrayList<>();
        this.board = new GameBoard(mapNames);
        this.roundHandler = new RoundHandler(this, players);
        this.movementHandler = new MovementHandler(this, players);
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

    public RoundHandler getRoundHandler() {return roundHandler;}

    public MovementHandler getMovementHandler() {
        return movementHandler;    // this should of course change
    }

    public void drawPlayers() {
        for (Player player : players)
            LayerOperation.drawPlayer(board.getLayer(MapLayer.PLAYER_LAYER), player);
    }

    private boolean createPlayer() {
        if (Player.getPlayerCount() >= playerSpriteSheetGrid.length)
            throw new IllegalStateException(
                    "Cannot create more players than the number of available textures"
            );

        TextureRegion graphic = playerSpriteSheetGrid[Player.getPlayerCount()][0];
        return players.add(new Player(graphic));
    }

    public boolean createPlayers(int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Illegal quantity");

        boolean allAdded = true;
        for (int i = 0; i < quantity; i++)
            if (!createPlayer())
                allAdded = false;

        movementHandler.moveAllToSpawn();
        drawPlayers();

        givePlayersCards();         // Temp solution for testing cards

        return allAdded;
    }

    public void setActivePlayerById(int id) {
        for (Player player : players)
            if (player.getId() == id) {
                movementHandler.setActive(player);
                return;
            }
        throw new IllegalArgumentException("No player with the given id exists");
    }

    public Player getPlayerById(int id) {
        for (Player player : players)
            if (player.getId() == id) {
                return player;
            }
        throw new IllegalArgumentException("No player with the given id exists");
    }

    public void dispose() {
        board.dispose();
        playerSpriteSheet.dispose();
    }

    public void givePlayersCards() {    // Gives all players random cards
        for (Player player : players){
            player.selectFiveCards();
        }
    }

    public boolean hasLost(Player player) {
        return player.isDead() && player.isOutOfLifeTokes();
    }

    public void removeLosers() {
        players.removeIf(player -> hasLost(player));
    }

    // should be called between each movement
    public void attemptToAppointWinner() {
        if (players.size() == 1 && Player.getPlayerCount() > 1) {    // all other players has lost
            this.winner = players.get(0);
            return;
        }
        for (Player player : players)
            if (movementHandler.getFlagWinnerChecker().hasVisitedAllFlags(player)) {
                this.winner = player;
                return;
            }
    }

    public boolean hasWon() {
        return this.winner != null;
    }
}
