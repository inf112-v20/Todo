package inf112.core.playercards;

import inf112.core.cards.*;
import inf112.core.cards.register.IProgramSheet;
import inf112.core.cards.register.ProgramSheet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProgramSheetTest {

    private IProgramSheet<ProgramCard> programSheet;
    private ProgramCard card1, card2, card3, card4, card5, card6;

    @Before
    public void init() {
        this.programSheet = new ProgramSheet();
        this.card1 = new MovementCard(1, 1, true, "Card1", null);
        this.card2 = new MovementCard(2, 1, true, "Card2", null);
        this.card3 = new MovementCard(3, 1, true, "Card3", null);
        this.card4 = new RotationCard(4, false, 1, "Card4", null);
        this.card5 = new RotationCard(5, false, 1, "Card5", null);
        this.card6 = new RotationCard(6, false, 1, "Card6", null);
    }

    private void fillProgramSheet() {
        programSheet.add(card1);
        programSheet.add(card2);
        programSheet.add(card3);
        programSheet.add(card4);
        programSheet.add(card5);
    }

    @Test
    public void containsAddedCardYielsTrueTest() {
        programSheet.add(card1);
        assertTrue(programSheet.contains(card1));
    }

    @Test
    public void containsNotAddedCardYieldsFalseTest() {
        programSheet.add(card2);
        assertFalse(programSheet.contains(card1));
    }

    @Test
    public void emptyProgramSheetIsEmptyTest() {
        assertTrue(programSheet.isEmpty());
    }

    @Test
    public void nonEmptyProgramSheetIsNotEmptyTest() {
        programSheet.add(card1);
        assertFalse(programSheet.isEmpty());
    }

    @Test
    public void containsAddedCardThatHasBeenRemovedYieldsFalse1Test() {
        programSheet.add(card3);
        programSheet.remove(card3);
        assertFalse(programSheet.contains(card3));
    }

    @Test
    public void containsAddedCardThatHasBeenRemovedYieldsFalse2Test() {
        programSheet.add(card3);
        programSheet.add(card2);
        programSheet.remove(card3);
        assertFalse(programSheet.contains(card3));
    }

    @Test
    public void containsAddedCardThatHasBeenRemovedYieldsFalse3Test() {
        programSheet.add(card3);
        programSheet.remove(card3);
        programSheet.add(card2);
        assertFalse(programSheet.contains(card3));
    }

    @Test
    public void theFirstCardAddedShouldBeAtRegisterNumberOneTest() {
        programSheet.add(card1);
        programSheet.add(card2);
        programSheet.add(card3);
        assertEquals(card1, programSheet.get(1));
    }

    @Test
    public void theSecondCardAddedShouldBeAtRegisterNumberTwoTest() {
        programSheet.add(card1);
        programSheet.add(card2);
        programSheet.add(card3);
        assertEquals(card2, programSheet.get(2));
    }

    @Test
    public void addingFiveCardsShouldYieldTrueTest() {
        assertTrue(programSheet.add(card1));
        assertTrue(programSheet.add(card2));
        assertTrue(programSheet.add(card3));
        assertTrue(programSheet.add(card4));
        assertTrue(programSheet.add(card5));
    }

    @Test
    public void addingTheSixthCardShouldYieldFalseTest() {
        fillProgramSheet();
        assertFalse(programSheet.add(card6));
    }

    @Test
    public void initiallyAllRegistersAreUnlockedTest() {
        for (int i = 1; i <= 5; i++) {
            assertFalse(programSheet.isRegisterLocked(i));
        }
    }

    @Test
    public void lockingOneRegisterShouldLockTheFifthRegisterOnly() {
        fillProgramSheet();

        programSheet.lockNextRegister();
        for (int i = 1; i <= 4; i++)
            assertFalse(programSheet.isRegisterLocked(i));

        assertTrue(programSheet.isRegisterLocked(5));
    }

    @Test
    public void lockingTwoRegistersShouldLockTheFourthAndFifthRegisterOnly() {
        fillProgramSheet();

        programSheet.lockNextRegister();
        programSheet.lockNextRegister();
        for (int i = 1; i <= 3; i++)
            assertFalse(programSheet.isRegisterLocked(i));

        assertTrue(programSheet.isRegisterLocked(4));
        assertTrue(programSheet.isRegisterLocked(5));
    }

    @Test
    public void unlockingALockedRegisterShouldYieldThatRegisterIsUnlockedTest() {
        fillProgramSheet();

        programSheet.lockNextRegister();
        programSheet.unlockNextRegister();

        assertFalse(programSheet.isRegisterLocked(5));
    }

    @Test
    public void removingACardFromALockedRegisterShouldYieldFalseTest() {
        fillProgramSheet();

        programSheet.lockNextRegister();
        assertFalse(programSheet.remove(card5));
    }

    @Test
    public void afterClearingUnlockedRegistersAreEmptyAndLockedRegistersAreNonEmptyTest1() {
        fillProgramSheet();

        programSheet.lockNextRegister();
        programSheet.clearUnlockedRegisters();

        for (int i = 1; i <= 4; i++)
            assertTrue(programSheet.isRegisterEmpty(i));

        assertFalse(programSheet.isRegisterEmpty(5));
    }

    @Test
    public void filledProgramSheetWithAllRegistersUnlockedIsFullTest() {
        fillProgramSheet();
        assertTrue(programSheet.isFull());
    }

    @Test
    public void filledProgramSheetWithSomeRegistersLockedThenClearedThenRefilledIsFullTest() {
        fillProgramSheet();

        // lock reg 4 and 5
        programSheet.lockNextRegister();
        programSheet.lockNextRegister();

        programSheet.clearUnlockedRegisters();

        programSheet.add(card6);    // fill reg 1
        programSheet.add(card2);    // fill reg 2
        programSheet.add(card4);    // fill reg 3

        assertTrue(programSheet.isFull());
    }

    @Test
    public void lockingAnEmptyRegisterShouldThrowAnExceptionTest() {
        fillProgramSheet();

        programSheet.remove(card5);    // remove the card at reg 5

        try {
            programSheet.lockNextRegister();
            fail("Locking an empty register should throw an exception");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void removingTheLastCardYieldsTheRegisterContentsAsExpectedTest() {
        fillProgramSheet();

        programSheet.remove(card5);

        assertEquals(card1, programSheet.get(1));
        assertEquals(card2, programSheet.get(2));
        assertEquals(card3, programSheet.get(3));
        assertEquals(card4, programSheet.get(4));
        assertEquals(null, programSheet.get(5));
    }

    @Test
    public void removingAMiddleCardYieldsTheRegisterContentsAsExpectedTest() {
        fillProgramSheet();

        programSheet.remove(card3);

        assertEquals(card1, programSheet.get(1));
        assertEquals(card2, programSheet.get(2));
        assertEquals(card4, programSheet.get(3));
        assertEquals(card5, programSheet.get(4));
        assertEquals(null, programSheet.get(5));
    }

    @Test
    public void removingMiddleCardsAndAddingCardsAfterYieldsTheRegisterContentAsExpectedTest() {
        fillProgramSheet();

        programSheet.remove(card3);
        programSheet.add(card2);
        programSheet.remove(card4);
        programSheet.add(card6);

        assertEquals(card1, programSheet.get(1));
        assertEquals(card2, programSheet.get(2));
        assertEquals(card5, programSheet.get(3));
        assertEquals(card2, programSheet.get(4));
        assertEquals(card6, programSheet.get(5));
    }

    @Test
    public void lockingARegisterAndThenRemovingACardYieldsTheRegisterContentAsExpectedTest() {
        fillProgramSheet();

        programSheet.lockNextRegister();

        programSheet.remove(card2);

        assertEquals(card1, programSheet.get(1));
        assertEquals(card3, programSheet.get(2));
        assertEquals(card4, programSheet.get(3));
        assertEquals(null, programSheet.get(4));
        assertEquals(card5, programSheet.get(5));
    }

    @Test
    public void lockingTwoRegisterAndThenRemovingACardYieldsTheRegisterContentAsExpectedTest() {
        fillProgramSheet();

        programSheet.lockNextRegister();
        programSheet.lockNextRegister();

        programSheet.remove(card1);

        assertEquals(card2, programSheet.get(1));
        assertEquals(card3, programSheet.get(2));
        assertEquals(null, programSheet.get(3));
        assertEquals(card4, programSheet.get(4));
        assertEquals(card5, programSheet.get(5));
    }

    @Test
    public void lockingARegisterThenRemovingTwoCardsAndThenAddingACardYieldsTheRegisterContentAsExpectedTest() {
        fillProgramSheet();

        programSheet.lockNextRegister();

        programSheet.remove(card2);
        programSheet.remove(card4);

        programSheet.add(card6);

        assertEquals(card1, programSheet.get(1));
        assertEquals(card3, programSheet.get(2));
        assertEquals(card6, programSheet.get(3));
        assertEquals(null, programSheet.get(4));
        assertEquals(card5, programSheet.get(5));
    }

    @Test
    public void removingACardDuplicateOnlyRemovesTheFirstOccurrenceTest() {
        programSheet.add(card1);
        programSheet.add(card2);
        programSheet.add(card3);
        programSheet.add(card2);
        programSheet.remove(card2);

        assertEquals(card1, programSheet.get(1));
        assertEquals(card3, programSheet.get(2));
        assertEquals(card2, programSheet.get(3));
        assertEquals(null, programSheet.get(4));
        assertEquals(null, programSheet.get(5));
    }
}
