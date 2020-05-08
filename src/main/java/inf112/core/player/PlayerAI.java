package inf112.core.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.core.cards.ProgramCard;
import inf112.core.cards.register.ProgramSheet;
import inf112.core.game.MainGame;
import inf112.core.screens.userinterface.ImageCardWrapper;

import java.util.ArrayList;
import java.util.List;

public class PlayerAI extends Player {

    public PlayerAI() {
        this("Player " + (PlayerHandler.playerCount + 1));
    }

    public PlayerAI(String name) {
        this(name, new TextureRegion());
    }

    public PlayerAI(String name, TextureRegion region) {
        this(name, region, 0, 0);
    }

    public PlayerAI(int xPos, int yPos) {
        this("Player " + (PlayerHandler.playerCount + 1), new TextureRegion(), xPos, yPos);
    }

    public PlayerAI(TextureRegion region) {
        this("Player " + (PlayerHandler.playerCount + 1), region, 0, 0);
    }

    public PlayerAI(String name, TextureRegion region, int xPos, int yPos) {
        super(name, region, xPos, yPos);
    }

    public void makeProgram() {
        ProgramSheet programSheet = getProgramSheet();
        List<ProgramCard> hand = programSheet.getHand();
        while(!programSheet.isFull()) {
            programSheet.addToRegister(hand.remove(0));
        }
        List<ProgramCard> discardCards = new ArrayList<>();
        for(ProgramCard card : programSheet.getHand()){
            if (!programSheet.getProgram().contains(card)){
                discardCards.add(card);
            }
        }
        MainGame.deck.discardCards(discardCards);
        programReady = true;
    }
}
