package inf112.core.programcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
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

    public ProgramCard discardCard(ProgramCard card){
        inUse.remove(card);
        discardDeck.add(card);
        return card;
    }

    public void discardCards(List<ProgramCard> cards){
        for(ProgramCard card : cards) {
            discardCard(card);
        }
    }

    public void reshuffleDeck(){
        this.activeDeck.addAll(discardDeck);
        discardDeck.clear();
        Collections.shuffle(activeDeck);
    }

    public void discardInUse(){
        discardCards(inUse);
    }
}
