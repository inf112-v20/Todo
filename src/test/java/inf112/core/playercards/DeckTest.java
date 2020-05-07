package inf112.core.playercards;

import inf112.core.game.MainGame;
import inf112.core.movement.MovementHandler;
import inf112.core.cards.Deck;
import inf112.core.cards.ProgramCard;
import inf112.core.testingUtils.GdxTestRunner;
import inf112.core.util.AssMan;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(GdxTestRunner.class)
public class DeckTest {
    MainGame game;
    MovementHandler movementHandler;
    Deck deck;


    @Before
    public void init() {
        AssMan.load();
        AssMan.manager.finishLoading();

        game = new MainGame();
        game.createDeck();
        movementHandler = new MovementHandler(game);
        deck = game.getDeck();
    }

    @Test
    public void DefaultDeckIs84Cards(){
        assertEquals(84, deck.getActiveDeck().size());
    }

    @Test
    public void selectedCardIsInUse(){
        assertEquals(deck.getCard(),deck.getInUse().get(0));
    }

    @Test
    public void selectedCardsAreInUse(){
        List<ProgramCard> cards = deck.getCards(10); // arbitrary amount
        assertEquals(cards, deck.getInUse());
    }

    @Test
    public void discardedCardIsPutInTheDisCardPile(){
        ProgramCard card = deck.getCard();
        deck.discardCardFromInUse(card);
        assertEquals(card, deck.getDiscardDeck().get(0));
    }

    @Test
    public void discardedCardsArePutInTheDisCardPile(){
        List<ProgramCard> cards = deck.getCards(10);
        deck.discardCards(cards);
        assertEquals(cards, deck.getDiscardDeck());
    }

    @Test
    public void discardpileIsReshuffledIntoActiveDeckWhenNeeded(){
        List<ProgramCard> cards = deck.getCards(84);    // Active deck is now empty
        deck.discardCards(cards);                               // Discard deck contains all the cards

        assertEquals(0,deck.getActiveDeck().size());
        assertEquals(84, deck.getDiscardDeck().size());

        ProgramCard newCard = deck.getCard();                   // Request new card

        assertEquals(83, deck.getActiveDeck().size());
        assertEquals(1, deck.getInUse().size());
    }

}
