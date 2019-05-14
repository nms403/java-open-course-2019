package ru.mail.polis.open.task6;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public abstract class ManagingPerson extends Person {
    private static int putsCount = 0;
    private static int removesCount = 0;

    protected static class Store {
        private HashMap<Book, Integer> store = new HashMap<>();

        protected void put(Book book, Integer amount) {
            if (!containsKey(book) || amount > get(book)) {
                putsCount++;
            }
            checkProperOperating(putsCount,
                    Manager.getPutsCount() + LibraryClient.getPutsCount(),
                    "put");
            justRemove(book);
            store.put(book, amount);
        }

        private void justRemove(Book key) {
            for (Book book: store.keySet()) {
                if (book.equals(key)) {
                    store.remove(book);
                    return;
                }
            }
        }

        protected void remove(Book book) {
            removesCount++;
            checkProperOperating(removesCount, LibraryClient.getRemovesCount(), "remove");
            store.remove(book);
        }

        protected boolean containsKey(Book key) {
            for (Book book: store.keySet()) {
                if (book.equals(key)) {
                    return true;
                }
            }
            return false;
        }

        protected int get(Book key) {
            for (Book book: store.keySet()) {
                if (book.equals(key)) {
                    return store.get(book);
                }
            }
            throw new NullPointerException();
        }

        protected Set<Book> keySet() {
            return store.keySet();
        }
    }

    protected static Store store = new Store();

    protected ManagingPerson(String name, Serializable database) {
        super(name, database);
    }
}
