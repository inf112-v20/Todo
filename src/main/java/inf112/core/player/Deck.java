package inf112.core.player;

import inf112.core.programcards.Card;
import inf112.core.programcards.ProgramCard;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<ProgramCard> discardDeck = new ArrayList<>();
    private List<ProgramCard> activeDeck = new ArrayList<>();
    private ProgramCard[] selectedCards = new ProgramCard[5];
    // Skal være et felt i player

    public Deck(){
    }

    public void addCard(ProgramCard card) {

    }

}
