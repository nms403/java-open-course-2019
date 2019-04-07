package ru.mail.polis.open.task6.implementation.people;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task6.implementation.book.Book;
import ru.mail.polis.open.task6.implementation.book.Category;
import ru.mail.polis.open.task6.implementation.BookShelf;
import ru.mail.polis.open.task6.implementation.Library;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ManagerTest {

    private static Manager manager;
    private static BookShelf bookShelf;
    private static Librarian librarian;
    private static Library library;

    @BeforeEach
    void createManager() {
        bookShelf = new BookShelf();
        manager = new Manager(new Person("name", "surname"));
        librarian = new Librarian(new Person("name", "surname"));
        library = new Library(bookShelf, manager, librarian);
        librarian.assignToLibrary(library);
        manager.assignToLibrary(library);
        library.open();
    }

    @Test
    void addBook() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        manager.addBook(book1);

        assertEquals(Set.of(book1), bookShelf.getAllBooks());

        Book book2 = new Book("n2", "a2", Category.PROGRAMMING);

        manager.addBook("n2", "a2", Category.PROGRAMMING);

        assertEquals(Set.of(book1, book2), bookShelf.getAllBooks());
    }

    @Test
    void deleteBook_WorksCorrectly() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        manager.addBook(book1);
        assertEquals(Set.of(book1), bookShelf.getAllBooks());
        manager.removeBook(book1);

        assertEquals(Set.of(), bookShelf.getAllBooks());
    }

    @Test
    void deleteBook_ReturnsFalseWhenBookIsNotInStock() {
        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        manager.addBook(book1);
        assertEquals(Set.of(book1), bookShelf.getAllBooks());
        librarian.lendBook(null, book1);

        assertThrows(NoSuchElementException.class, () -> manager.removeBook(book1));
    }

    @Test
    void deleteBook_ThrowsWhenBookDoesNotExist() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        assertThrows(NoSuchElementException.class, () -> manager.removeBook(book1));
    }

    @Test
    void deleteBookIfPresent_ReturnsTrueWhenBookIsInStock() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        manager.addBook(book1);
        assertEquals(Set.of(book1), bookShelf.getAllBooks());

        assertTrue(manager.removeBookIfPresent(book1));

        assertEquals(Set.of(), bookShelf.getAllBooks());
    }


    @Test
    void deleteBookIfPresent_ReturnsFalseWhenBookIsNotInStock() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        manager.addBook(book1);
        assertEquals(Set.of(book1), bookShelf.getAllBooks());
        librarian.lendBook(null, book1);

        assertFalse(manager.removeBookIfPresent(book1));
    }


    @Test
    void deleteBookIfPresent_ReturnsFalseWhenBookDoesNotExists() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        assertFalse(
            manager.removeBookIfPresent(book1));
    }

    @Test
    void openLibrary() {

        if (library.isOpened()) {
            library.close();
        }

        manager.openLibrary();

        assertTrue(library.isOpened());
    }

    @Test
    void closeLibrary() {

        if (!library.isOpened()) {
            library.open();
        }

        manager.closeLibrary();

        assertFalse(library.isOpened());
    }
}