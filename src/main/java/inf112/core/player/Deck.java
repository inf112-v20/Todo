package inf112.core.player;

import inf112.core.programcards.Card;
import inf112.core.programcards.ProgramCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<ProgramCard> discardDeck;
    private List<ProgramCard> activeDeck;

    public Deck(){
        discardDeck = new ArrayList<>();
        activeDeck = new ArrayList<>();
    }

    public void addCard(ProgramCard card) {
        activeDeck.add(card);
    }

    public void addCards(List<ProgramCard> cards){
        for (ProgramCard card : cards){
            activeDeck.add(card);
        }
    }

    public void shuffleActive(){
        Collections.shuffle(activeDeck);
    }

    // Returns list of cards for player to choose from
    // Should throw exception if amount is greater than (activecards + discardcards)
    public List<ProgramCard> getSelection(int amount) {
        List<ProgramCard> selection = null;
        if (amount >= activeDeck.size()) {
            selection.addAll(activeDeck);
            activeDeck.clear();
            activeDeck.addAll(discardDeck);
            shuffleActive();
            discardDeck.clear();
        }
        for (int i = 0; i < amount - selection.size(); i++) {
            selection.add(activeDeck.get(i));
        }
        return selection;
    }

    // select card (Dette blir antageligvis feil)
    public void selectCard(ProgramCard card){
        discardDeck.add(activeDeck.remove(activeDeck.indexOf(card)));
    }
}
