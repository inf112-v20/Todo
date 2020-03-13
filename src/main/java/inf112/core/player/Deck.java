package inf112.core.player;

import inf112.core.programcards.MovementCard;
import inf112.core.programcards.ProgramCard;
import inf112.core.programcards.RotationCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<ProgramCard> discardDeck;
    private List<ProgramCard> activeDeck;

    public Deck(){
        discardDeck = new ArrayList<>();
        activeDeck = new ArrayList<>();
        addCard(new MovementCard(100, 2, true, "Move forward 2"));
        addCard(new RotationCard(120, true, 2, "Rotate right 2"));
        addCard(new MovementCard(110, 1, false, "Move backwards 1"));
        addCard(new RotationCard(150, false, 1, "Rotate left 1"));
        addCard(new MovementCard(200, 3, true, "Move forward 3"));
    }

    public void addCard(ProgramCard card) {
        activeDeck.add(card);
    }

    public void addCards(List<ProgramCard> cards){
        for (ProgramCard card : cards){
            activeDeck.add(card);
        }
    }

    public ProgramCard getRandomCard(){
        if (activeDeck.size() <= 0) {
            activeDeck.addAll(discardDeck);
            discardDeck.clear();
            Collections.shuffle(activeDeck);
        }
        ProgramCard card = activeDeck.remove(0);
        discardDeck.add(card);
        return card;
    }

    public void shuffleActive(){
        Collections.shuffle(activeDeck);
    }

    public List<ProgramCard> getFiveCards(){
        List<ProgramCard> cards = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            cards.add(getRandomCard());
        }
        return cards;
    }

    public void selectCard(ProgramCard card){
        discardDeck.add(activeDeck.remove(activeDeck.indexOf(card)));
    }
}
