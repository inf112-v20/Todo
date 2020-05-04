package inf112.core.cards.register;

import inf112.core.cards.ProgramCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramSheet implements IProgramSheet<ProgramCard> {
    public static final int NUM_OF_REGISTERS = 5;

    private List<ProgramCard> hand;
    private ProgramCard[] registers;
    private int addHere;               // points to the next available space in register array
    private int locked;                // points to first locked register (from left)
    private int current;               // points to current card being played

    public ProgramSheet() {
        this.hand = new ArrayList<>();
        this.registers = new ProgramCard[NUM_OF_REGISTERS];
        this.addHere = 0;
        this.locked = NUM_OF_REGISTERS;
        this.current = 0;
    }

    @Override
    public boolean isEmpty() {
        return addHere == 0 && locked == NUM_OF_REGISTERS;
    }

    @Override
    public boolean isFull() {
        return addHere == locked;
    }

    @Override
    public boolean contains(ProgramCard card) {
        for (int i = 0; i < NUM_OF_REGISTERS; i++)
            if (card.equals(registers[i]))
                return true;
        return false;
    }

    public List<ProgramCard> getProgram() {
        assert(isFull());

        return Arrays.asList(registers);
    }

    @Override
    public boolean addToRegister(ProgramCard card) {
        assert(hand.contains(card));

        if (isFull())
            return false;

        registers[addHere++] = card;
        checkInv();
        return true;
    }

    public void addToHand(List<ProgramCard> programCards) {
        hand.addAll(programCards);
    }

    public List<ProgramCard> getHand() {
        return hand;
    }

    @Override
    public boolean remove(ProgramCard card) {
        for (int i = 0; i < locked; i++)
            if (card.equals(registers[i])) {
                registers[i] = null;
                adjustAfterRemoval(i);
                updateAddHerePointer();
                checkInv();
                return true;
            }
        return false;
    }

    /**
     * Moves all cards from unlocked registers to the right of removedHere one register to the left (to fill the gap)
     *
     * @param removedHere, index where a card just was removed
     */
    private void adjustAfterRemoval(int removedHere) {
        int i;
        for (i = removedHere; i < locked - 1; i++)
            registers[i] = registers[i + 1];

        registers[i] = null;   // to not duplicate the last element
    }

    /**
     * Updates addHere pointer to the first empty unlocked register.
     * If all registers are non-empty, then addHere points to the leftmost locked register (addHere == locked)
     */
    private void updateAddHerePointer() {
        addHere = 0;
        while (addHere < locked) {
            if (registers[addHere] == null)
                break;

            addHere++;
        }
    }

    @Override
    public void lockNextRegister() {
        this.locked--;
        this.locked = Math.max(locked, 0);    // if all registers are locked, we cannot lock more registers
        if (registers[locked] == null)
            throw new IllegalStateException("Cannot lock an empty register");
        updateAddHerePointer();
        checkInv();
    }

    @Override
    public void unlockNextRegister() {
        this.locked++;
        this.locked = Math.min(locked, NUM_OF_REGISTERS);    // if all registers are unlocked, we cannot unlock more registers
        updateAddHerePointer();
        checkInv();
    }

    @Override
    public List<ProgramCard> clearUnlockedRegisters() {
        List<ProgramCard> cards = new ArrayList<>();

        for (int i = 0; i < locked; i++){
            cards.add(registers[i]);
            registers[i] = null;
        }

        this.addHere = 0;
        this.current = 0;
        this.hand.clear();
        return cards;
    }

    @Override
    public List<ProgramCard> getCardList() {
        return Arrays.asList(registers);
    }

    @Override
    public ProgramCard getNext() {
        if (current >= NUM_OF_REGISTERS)
            throw new IllegalStateException("Program sheet has no more registers");

        return registers[current++];
    }

    @Override
    public ProgramCard get(int regNum) {
        if (regNum < 1 || regNum > NUM_OF_REGISTERS)
            throw new IllegalArgumentException("Invalid register number");

        return registers[regNum - 1];    // "translates" register number to register index
    }

    @Override
    public boolean isRegisterEmpty(int regNum) {
        if (regNum < 1 || regNum > NUM_OF_REGISTERS)
            throw new IllegalArgumentException("Invalid register number");

        return registers[regNum - 1] == null;    // "translates" register number to register index
    }

    @Override
    public boolean isRegisterLocked(int regNum) {
        if (regNum < 1 || regNum > NUM_OF_REGISTERS)
            throw new IllegalArgumentException("Invalid register number");

        return (regNum - 1) >= locked;    // "translates" register number to register index
    }

    /**
     * Class invariant.
     */
    private void checkInv() {
        assert addHere <= locked : "addHere > locked";
        assert addHere >= 0 : "addHere < 0";
        assert locked <= NUM_OF_REGISTERS : "locked > number of registers";
    }
}
