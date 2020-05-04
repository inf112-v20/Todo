package inf112.core.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.core.cards.Deck;
import inf112.core.game.MainGame;

import java.util.ArrayList;

public class PlayerHandler {

    public static int humanPlayers = 0;
    public static int playerCount = 0;

    private MainGame game;

    private ArrayList<Player> players;
    private Player activePlayer;

    public PlayerHandler(MainGame game) {
        this.game = game;
        players = new ArrayList<>();
    }

    /**
     * creates a player setup with given amount of human players, the rest of the player limit is filled with playerAI
     *
     * @param humanPlayers amount of human players
     */
    public void setupPlayers(int humanPlayers) {
        createPlayers(humanPlayers);
        createAI(game.playerLimit - humanPlayers);

        game.getMovementHandler().moveAllToSpawn();
        game.drawPlayers();
    }

    private boolean createPlayer() {
        if (playerCount >= game.getPlayerSpriteSheetGrid().length)
            throw new IllegalStateException(
                    "Cannot create more players than the number of available textures"
            );

        TextureRegion graphic = game.getPlayerSpriteSheetGrid()[playerCount][0];
        if(players.add(new Player(graphic))) {
            humanPlayers++;
            return true;
        }
        return false;

    }

    public boolean createPlayers(int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Illegal quantity");

        boolean allAdded = true;
        for (int i = 0; i < quantity; i++)
            if (!createPlayer())
                allAdded = false;

        return allAdded;
    }

    public boolean createAI() {
        if (playerCount >= game.getPlayerSpriteSheetGrid().length)
            throw new IllegalStateException(
                    "Cannot create more players than the number of available textures"
            );

        TextureRegion graphic = game.getPlayerSpriteSheetGrid()[playerCount][0];
        return players.add(new PlayerAI(graphic));
    }

    public boolean createAI(int quantity) {
        boolean allAdded = true;
        for(int i = 0; i < quantity; i++) {
            if(!(playerCount < MainGame.playerLimit))
                return false;
            createAI();
        }
        return true;
    }

    public Player getPlayerById(int id) {
        for (Player player : players)
            if (player.getId() == id) {
                return player;
            }
        throw new IllegalArgumentException("No player with the given id exists");
    }

    public void setActivePlayerById(int id) {
        for (Player player : getPlayers())
            if (player.getId() == id) {
                setActivePlayer(player);
                return;
            }
        throw new IllegalArgumentException("No player with the given id exists");
    }

    public boolean areProgramsReady() {
        for(Player player : players) {
            if (!player.programReady) {
                return false;
            }
        }
        return true;
    }

    public void makeAIPrograms() {
        for(Player player : players) {
            if(player instanceof PlayerAI)
                ((PlayerAI) player).makeProgram();
        }
    }

    public void givePlayerCards(Player player){
        player.addToHand(game.getDeck().getCards(Deck.HAND_SIZE));
    }

    public void giveAllPlayersCards() {
        for (Player player : players)
            givePlayerCards(player);
    }

    public void nextCard() {
        for (Player player : players)
            player.nextCard();
    }

    public void clearAllProgramsheets() {
        for(Player player : players)
            clearProgramsheet(player);
    }

    private void clearProgramsheet(Player player) {
        game.getDeck().discardCards(player.clearProgramSheet());
        player.programReady = false;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

}
