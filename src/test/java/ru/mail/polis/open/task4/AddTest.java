package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddTest {

    @Test
    void test() {
        assertEquals(
            3,
            new Add(
                new Const(1),
                new Const(2)
            ).evaluate()
        );
    }

    @Test
    void testEvaluate() {
        assertEquals(100, new Add(new Const(81), new Const(19)).evaluate());
    }

    @Test
    void testWorkingEquals() {
        Add add1 = new Add(
                new Const(14),
                new Const(2)
        );

        Add add2 = new Add(
                new Const(14),
                new Const(2)
        );

        assertEquals(add1, add2);
    }
}