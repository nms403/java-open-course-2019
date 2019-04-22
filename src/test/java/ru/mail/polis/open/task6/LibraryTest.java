package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void testInitOfWorkLib() {
        Library.libInit();
        Book book = new Book("451 градус по Фаренгейту", "Брэдбери", "Фантастика", 1,1);
        for (Book book2 : Library.getAllBooks()) {
            if(book.equals(book2)) {
                assertEquals(true, book.equals(book2));
            }
        }
    }
}