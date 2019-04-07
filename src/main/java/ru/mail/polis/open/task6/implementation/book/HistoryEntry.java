package ru.mail.polis.open.task6.implementation.book;

import ru.mail.polis.open.task6.implementation.people.Customer;

import java.util.Date;
import java.util.Objects;

/**
 * Data class that stores information about book borrowing
 */
public class HistoryEntry {

    // Who borrowed
    private Customer customer;

    // When borrowed
    private Date beginDate;

    // When customer has to return book
    private Date endDate;

    private boolean isReturned;

    public HistoryEntry(Customer customer, Date beginDate, Date endDate) {
        this.customer = customer;
        this.beginDate = beginDate;
        this.endDate = endDate;
        isReturned = false;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void doReturn() {
        isReturned = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HistoryEntry that = (HistoryEntry) o;
        return Objects.equals(customer, that.customer)
            && Objects.equals(beginDate, that.beginDate)
            && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, beginDate, endDate);
    }
}
