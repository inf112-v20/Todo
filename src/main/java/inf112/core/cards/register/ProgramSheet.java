package inf112.core.cards.register;

import inf112.core.cards.ProgramCard;

import java.util.Arrays;
import java.util.List;

public class ProgramSheet implements IProgramSheet {

    public static final int NUM_OF_REGISTERS = 5;

    private ProgramCard[] registers;
    private int addHere;               // points to the next available space in register array
    private int locked;                // points to first locked register (from left)
    private int current;               // points to current card being played

    public ProgramSheet() {
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
        return addHere >= locked;
    }

    @Override
    public boolean contains(ProgramCard card) {
        for (int i = 0; i < NUM_OF_REGISTERS; i++)
            if (registers[i].equals(card))
                return true;
        return false;
    }

    @Override
    public boolean add(ProgramCard card) {
        if (isFull())
            return false;

        registers[addHere++] = card;
        return true;
    }

    @Override
    public boolean remove(ProgramCard card) {
        for (int i = 0; i < NUM_OF_REGISTERS; i++)
            if (registers[i].equals(card)) {
                registers[i] = null;
                return true;
            }
        return false;
    }

    @Override
    public void lockNext() {
        this.locked--;
        this.locked = Math.max(locked, 0);    // if all registers are locked, we cannot lock more registers
    }

    @Override
    public void unlockNext() {
        this.locked++;
        this.locked = Math.min(locked, NUM_OF_REGISTERS);
    }

    @Override
    public void clearUnlocked() {
        for (int i = 0; i < locked; i++)
            registers[i] = null;

        this.addHere = 0;
        this.current = 0;
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
    public boolean isLocked(int regNum) {
        if (regNum < 1 || regNum > NUM_OF_REGISTERS)
            throw new IllegalArgumentException("Invalid register number");

        return (regNum - 1) >= locked;    // "translates" register number to register index
    }
}
