package inf112.core.cards.register;

import inf112.core.cards.ProgramCard;

import java.util.List;

public interface IProgramSheet {

    boolean isEmpty();

    boolean isFull();

    boolean contains(ProgramCard card);

    boolean add(ProgramCard card);

    boolean remove(ProgramCard card);

    void lockNext();

    void unlockNext();

    void clearUnlocked();

    List<ProgramCard> getCardList();

    ProgramCard getNext();

    ProgramCard get(int regNum);

    boolean isLocked(int regNum);
}
