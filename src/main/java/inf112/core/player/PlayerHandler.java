package inf112.core.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.core.cards.ProgramCard;
import inf112.core.game.MainGame;

import java.util.ArrayList;
import java.util.List;

public class PlayerHandler {

    private MainGame game;

    private ArrayList<Player> players;
    private Player activePlayer;

    public PlayerHandler(MainGame game) {
        this.game = game;
        players = new ArrayList<>();
    }

    private boolean createPlayer() {
        if (Player.getPlayerCount() >= game.getPlayerSpriteSheetGrid().length)
            throw new IllegalStateException(
                    "Cannot create more players than the number of available textures"
            );

        TextureRegion graphic = game.getPlayerSpriteSheetGrid()[Player.getPlayerCount()][0];
        return players.add(new Player(graphic));
    }

    public boolean createPlayers(int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Illegal quantity");

        boolean allAdded = true;
        for (int i = 0; i < quantity; i++)
            if (!createPlayer())
                allAdded = false;

        game.getMovementHandler().moveAllToSpawn();
        game.drawPlayers();

        return allAdded;
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

    public void givePlayerCards(Player player){   // Is to be moved once we have a proper implementation for rounds
        List<ProgramCard> fiveRandomCards = game.getDeck().getCards(5);
        for (ProgramCard card : fiveRandomCards){
            player.addToProgramSheet(card);
        }
    }

    public void givePlayersCards() {   // Is to be moved once we have a proper implementation for rounds
        for (Player player : players)
            givePlayerCards(player);
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
