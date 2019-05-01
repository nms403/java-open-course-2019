package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.jackManager;
import static ru.mail.polis.open.task6.UtilTest.myths;

public class TestIllegalRemove {
    @Test
    void testIllegalRemove() {
        jackManager.addNewBookToStore(myths, 12);
        assertThrows(IllegalCallerException.class, () -> ManagingPerson.store.remove(myths));
    }
}
