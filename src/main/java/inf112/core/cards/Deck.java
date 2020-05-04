package inf112.core.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    public static final int HAND_SIZE = 9;

    private List<ProgramCard> activeDeck;
    private List<ProgramCard> discardDeck;
    private List<ProgramCard> inUse;

    public Deck(List<ProgramCard> cards){
        this.inUse = new ArrayList<>();
        this.discardDeck = new ArrayList<>();
        this.activeDeck = new ArrayList<>();
        this.activeDeck.addAll(cards);
    }

    public ProgramCard getCard(){
        if(activeDeck.size() <= 0) {
            reshuffleDeck();
            System.out.println("Reshuffled deck");

            if (activeDeck.size() <= 0) {   // returns null if no more cards are available
                return null;
            }
        }

        ProgramCard card = activeDeck.remove(0);
        inUse.add(card);
        return card;
    }

    public List<ProgramCard> getCards(int amount) {
        List<ProgramCard> cards = new ArrayList<>();
        for (int i = 0; i < amount; i++){
            cards.add(getCard());
        }
        return cards;
    }

    public ProgramCard discardCardFromInUse(ProgramCard card){
        inUse.remove(card);
        discardDeck.add(card);
        return card;
    }

    public void discardCards(List<ProgramCard> cards){
        for(ProgramCard card : cards) {
            discardCardFromInUse(card);
        }
    }

    public void reshuffleDeck(){
        this.activeDeck.addAll(discardDeck);
        discardDeck.clear();
        Collections.shuffle(activeDeck);
    }

    public List<ProgramCard> getActiveDeck() {
        return activeDeck;
    }

    public List<ProgramCard> getDiscardDeck() {
        return discardDeck;
    }

    public List<ProgramCard> getInUse() {
        return inUse;
    }

}
