package com.digitalbooks.reader.entities.payload;

import com.digitalbooks.reader.entities.models.BookDto;
import com.digitalbooks.reader.entities.models.ReaderDto;

import java.time.LocalDateTime;

public class InvoicePayload {

    private Long subscriptionId;
    private LocalDateTime subscriptionDateTime;
    private BookDto bookDto;
    private ReaderDto readerDto;

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public LocalDateTime getSubscriptionDateTime() {
        return subscriptionDateTime;
    }

    public void setSubscriptionDateTime(LocalDateTime subscriptionDateTime) {
        this.subscriptionDateTime = subscriptionDateTime;
    }

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public ReaderDto getReaderDto() {
        return readerDto;
    }

    public void setReaderDto(ReaderDto readerDto) {
        this.readerDto = readerDto;
    }
}
