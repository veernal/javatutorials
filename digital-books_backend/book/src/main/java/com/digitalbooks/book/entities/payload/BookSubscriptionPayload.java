package com.digitalbooks.book.entities.payload;

import com.digitalbooks.book.entities.models.ReaderDto;

public class BookSubscriptionPayload {

    private Long bookId;
    private Long subscriptionId;
    private ReaderDto readerDto;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public ReaderDto getReaderDto() {
        return readerDto;
    }

    public void setReaderDto(ReaderDto readerDto) {
        this.readerDto = readerDto;
    }
}
