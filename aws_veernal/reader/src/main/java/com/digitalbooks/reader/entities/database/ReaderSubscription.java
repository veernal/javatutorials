package com.digitalbooks.reader.entities.database;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ReaderSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBSCRIPTION_ID")
    private Long subscriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "READER_ID")
    private ReaderInfo parentReaderInfo;

    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "SUBSCRIPTION_DATE_TIME")
    private LocalDateTime subscriptionDateTime;

    @Column(name = "IS_CANCELLED", columnDefinition = "boolean default false")
    private Boolean isCancelled;

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public ReaderInfo getParentReaderInfo() {
        return parentReaderInfo;
    }

    public void setParentReaderInfo(ReaderInfo parentReaderInfo) {
        this.parentReaderInfo = parentReaderInfo;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getSubscriptionDateTime() {
        return subscriptionDateTime;
    }

    public void setSubscriptionDateTime(LocalDateTime subscriptionDateTime) {
        this.subscriptionDateTime = subscriptionDateTime;
    }

    public Boolean getCancelled() {
        return isCancelled;
    }

    public void setCancelled(Boolean cancelled) {
        isCancelled = cancelled;
    }
}
