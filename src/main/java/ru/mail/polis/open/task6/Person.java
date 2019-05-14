package ru.mail.polis.open.task6;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Person {

    private static final String UNSUPPORTED_COLLECTION_TYPE_MESSAGE =
            "This Person's Book Collection is something strange";
    private static final String NON_UNIQUE_NAME_MESSAGE = "Name must be unique!";
    protected static String ILLEGAL_CALL_MESSAGE = "Illegal call to ";

    protected static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private final String name;
    
    private static int rawOperationsCount = 0;

    protected Person(String name, Serializable database) {
        if (database instanceof HashMap) {
            HashMap hashDatabase = (HashMap) database;
            if (hashDatabase.containsKey(name)) {
                throw new IllegalArgumentException(NON_UNIQUE_NAME_MESSAGE);
            }
            hashDatabase.put(name, this);
        } else {
            if (database instanceof ArrayList) {
                ArrayList arrayDatabase = (ArrayList) database;
                if (arrayDatabase.contains(name)) {
                    throw new IllegalArgumentException(NON_UNIQUE_NAME_MESSAGE);
                }
                arrayDatabase.add(name);
            } else {
                throw new IllegalArgumentException(UNSUPPORTED_COLLECTION_TYPE_MESSAGE);
            }
        }
        this.name = name;
    }

    public enum BookSearchParameter {
        BY_ID, BY_NAME, BY_PARTITIONS, BY_PARTITION
    }

    public String getName() {
        return name;
    }

    protected static void checkProperOperating(int legalOperationsCount, int actualOperationsCount, String methodName) {
        if (legalOperationsCount != actualOperationsCount) {
            throw new IllegalCallerException(ILLEGAL_CALL_MESSAGE + methodName);
        }
    }

    private void checkProperOperatingForPerson(String methodName) {
        checkProperOperating(rawOperationsCount,
                Manager.getOperationsCount() + LibraryClient.getOperationsCount(),
                methodName);
    }

    protected void bookHashMapOperating(ManagingPerson.Store collection, Book book, int amountChange) {
        rawOperationsCount++;
        checkProperOperatingForPerson("bookHashMapOperating");
        int previousAmount = collection.get(book);
        collection.put(book, previousAmount + amountChange);
    }

    protected <V> Book removeOneBookFromCollection(Book bookToRemove, V collection) {
        rawOperationsCount++;
        checkProperOperatingForPerson("removeOneBookFromCollection");
        if (collection instanceof ManagingPerson.Store) {
            ManagingPerson.Store store = (ManagingPerson.Store) collection;
            if (store.containsKey(bookToRemove)) {
                if (store.get(bookToRemove) == 1) {
                    store.remove(bookToRemove);
                } else {
                    rawOperationsCount--;
                    bookHashMapOperating(store, bookToRemove, -1);
                }
            } else {
                return null;
            }
        } else {
            if (collection instanceof ArrayList) {
                ArrayList arrayCollection = (ArrayList) collection;
                if (arrayCollection.contains(bookToRemove)) {
                    arrayCollection.remove(bookToRemove);
                } else {
                    return null;
                }
            } else {
                throw new IllegalArgumentException(UNSUPPORTED_COLLECTION_TYPE_MESSAGE);
            }
        }
        return bookToRemove;
    }

    protected <V> void addOneBookToCollection(Book bookToAdd, V collection) {
        rawOperationsCount++;
        checkProperOperatingForPerson("addOneBookToCollection");
        if (collection instanceof ManagingPerson.Store) {
            ManagingPerson.Store store = (ManagingPerson.Store) collection;
            if (store.containsKey(bookToAdd)) {
                rawOperationsCount--;
                bookHashMapOperating(store, bookToAdd, 1);
            } else {
                store.put(bookToAdd, 1);
            }
        } else {
            if (collection instanceof ArrayList) {
                ((ArrayList) collection).add(bookToAdd);
            } else {
                throw new IllegalArgumentException(UNSUPPORTED_COLLECTION_TYPE_MESSAGE);
            }
        }
    }
}
