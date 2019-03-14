package ru.mail.polis.open.task3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestCorrectBracketSequenceChecker {
    @AfterEach
    void resetCountersOfClassCorrectBracketSequenceChecker() {
        CorrectBracketSequenceChecker.reset();
    }

    @Test
    void testReturnTrueOfMethodsCheckSequence() {
        assertTrue(CorrectBracketSequenceChecker.checkSequence(""));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]()(){}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[]{()}{{()}}()"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[{}]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("[()]"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("{()}"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({{[]}})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({{[]}})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({{[]{}}()})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({[](){{}{}()()}})"));
        assertTrue(CorrectBracketSequenceChecker.checkSequence("({[]([]){{}{[]}()()}}){}{()}"));
    }

    @Test
    void testReturnFalseOfMethodsCheckSequence() {
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("[}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(}}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("(}]]"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("([}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("{(}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}{{({)}{)[(])"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}{{]{"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({})}{)[(])"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}{{)[(])"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}{{({)}{)[(])"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}{{)[(])"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}{{({)[(])"));
        assertFalse(CorrectBracketSequenceChecker.checkSequence("({}{{({)[(])"));
    }

    @Test
    void testThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence("[][][]g[][]"));
        assertThrows(IllegalArgumentException.class,
            () -> assertFalse(CorrectBracketSequenceChecker.checkSequence("()()()()()()()()()()()()"
                    + "()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()"
                    + "()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()"
                    + "()()()()()()()()()()()()()()()()()()")));
        assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence(null));
    }

    @Test
    void testCorrectWorkingQuantityOfSuccessfulAttempts() {
        assertEquals(0,
                CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("()()()[][][]{}{}{}");
        CorrectBracketSequenceChecker.checkSequence("()()[][]{[]{()}}");
        CorrectBracketSequenceChecker.checkSequence("()()()()[][]{()}");
        assertEquals(3,
                CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("()()()({}[({}{})])([])");
        CorrectBracketSequenceChecker.checkSequence("()()()({}[([]{[]()})])([])");
        assertEquals(5,
                CorrectBracketSequenceChecker.getSuccessChecksCount());
        CorrectBracketSequenceChecker.checkSequence("()()([]{[]}){}");
        CorrectBracketSequenceChecker.checkSequence("()()([]{}{}(){[]}){}");
        assertEquals(7,
                CorrectBracketSequenceChecker.getSuccessChecksCount());
    }

    @Test
    void testCorrectWorkingQuantityOfFailedAttempts() {
        assertEquals(0,
                CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence("{}({}()[]()[){})[[[)");
        CorrectBracketSequenceChecker.checkSequence("{}({}]()[){})[[[)");
        assertEquals(2,
                CorrectBracketSequenceChecker.getFailChecksCount());
        CorrectBracketSequenceChecker.checkSequence("{}({})[){})[[[)");
        CorrectBracketSequenceChecker.checkSequence("{}({}()[]()[){})[[[)");
        assertEquals(4,
                CorrectBracketSequenceChecker.getFailChecksCount());

        assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence("{}({}()[]yyt()[}}ii}{})[[[)"));

        assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence("{}({}()[]()[fefw}}}{})[[[)"));

        CorrectBracketSequenceChecker.checkSequence("{}({}()[]()))))[)");
        assertEquals(7,
                CorrectBracketSequenceChecker.getFailChecksCount());

        assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence("{}({rr}}{})[[[)"));

        assertEquals(8,
                CorrectBracketSequenceChecker.getFailChecksCount());
    }

    @Test
    void testCorrectWorkingGetLastSuccessSequence() {
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());
        CorrectBracketSequenceChecker.checkSequence("()))))(()(");
        assertNull(CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("()()()()()()()");
        assertEquals("()()()()()()()",
                CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("))))(()()({{}}}{{}");
        assertEquals("()()()()()()()",
                CorrectBracketSequenceChecker.getLastSuccessSequence());

        CorrectBracketSequenceChecker.checkSequence("[()({}{[{}][]})]");
        assertEquals("[()({}{[{}][]})]",
                CorrectBracketSequenceChecker.getLastSuccessSequence());

        assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence("{}({}()[]()[fefw}}}{})[[[)"));
        assertEquals("[()({}{[{}][]})]",
                CorrectBracketSequenceChecker.getLastSuccessSequence());

        assertThrows(IllegalArgumentException.class,
            () -> CorrectBracketSequenceChecker.checkSequence("{}({rr}}{})[[[)"));
        CorrectBracketSequenceChecker.checkSequence("[()()()([]{})()({}{[{}][]})]");
        assertEquals("[()()()([]{})()({}{[{}][]})]",
                CorrectBracketSequenceChecker.getLastSuccessSequence());
    }
}