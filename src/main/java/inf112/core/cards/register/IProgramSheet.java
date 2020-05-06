package inf112.core.cards.register;

import inf112.core.cards.ProgramCard;

import java.util.List;

/**
 * An interface for a data structure consisting of a fixed amount of registers, or slots, with register each being
 * capable of holding a single element (i.e. card). The registers are numbered like 1, 2, ... up to the fixed limit.
 *
 * A register can also be locked (from right to left, starting with the rightmost register) and unlocked
 * (from left to right, starting with the leftmost locked register).
 *
 * Elements can only be removed from the data structure as long as its corresponding register is unlocked.
 *
 * @param <C> to represent a Card-like class of some sort.
 */
public interface IProgramSheet<C> {

    /**
     * Checks if the program sheet contains any cards.
     *
     * @return false if the program sheet contains any non-empty registers (regardless of the register being locked),
     *         and otherwise true.
     */
    boolean isEmpty();

    /**
     * Checks if the program sheet is filled with cards.
     *
     * @return false if the program sheet contains any empty registers (regardless of the register being locked),
     *         and otherwise true.
     */
    boolean isFull();

    /**
     * Checks if the program sheet contains the given card.
     *
     * @param card in question
     * @return true if any of the cards in the registers matches the equality of the card in question,
     *         and otherwise false.
     */
    boolean contains(C card);

    /**
     * Adds the card in question to the first available register, or does nothing if all registers are filled up.
     *
     * @param card in question
     * @return true if the card was successfully added to the data structure, and false otherwise
     */
    boolean addToRegister(C card);

    /**
     * Removes the card in question from the first unlocked register where its equality is matched, and if so,
     * all cards to the right is moved one register to the left (to fill the gap).
     * However, cards in locked registers stay where they are.
     *
     * If no such cards matches the equality of the card in question, the state is unchanged.
     *
     * @param card in question
     * @return true if the card in question is found (and removed) from the data structure, and otherwise false.
     */
    boolean remove(C card);

    /**
     * Locks the next unlocked register (from the right). If all registers are locked, then state is unchanged.
     *
     * WARNING: the register to be locked must be non-empty
     *
     * @throws IllegalStateException if the register to be unlocked is empty
     */
    void lockNextRegister();

    /**
     * Unlocks the next locked register (from the left). If all registers are unlocked, then state is unchanged.
     */
    void unlockNextRegister();

    /**
     * Removes the cards from all unlocked registers. Locked registers keep their cards in place.
     * @return
     */
    List<ProgramCard> clearUnlockedRegisters();

    /**
     * @return a list of all the cards in the program sheet, in the order of the registers
     */
    List<C> getCardList();

    /**
     * The first call would yield the card from register 1, the second call from register 2 etc.
     *
     * @return the "current" register's card.
     * @throws IllegalStateException if there is no more registers available, i.e. if all registers have been "visited"
     */
    C getNext();

    /**
     * @param regNum, the register number
     * @return the card at that register number (can be null)
     * @throws IllegalArgumentException if the register number is not in 1-5
     */
    C get(int regNum);

    /**
     * @param regNum, the register number
     * @return true if the register corresponding to the register number has no card, otherwise false
     * @throws IllegalArgumentException if the register number is not in 1-5
     */
    boolean isRegisterEmpty(int regNum);

    /**
     * @param regNum, the register number
     * @return true if the register corresponding to the register number is locked, otherwise false
     * @throws IllegalArgumentException if the register number is not in 1-5
     */
    boolean isRegisterLocked(int regNum);
}
