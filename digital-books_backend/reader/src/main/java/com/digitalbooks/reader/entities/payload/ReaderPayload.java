package com.digitalbooks.reader.entities.payload;

import com.digitalbooks.reader.entities.models.BookDto;
import com.digitalbooks.reader.entities.models.ReaderDto;

import java.util.List;

public class ReaderPayload {

    private ReaderDto readerDto;
    private List<BookDto> bookDtoList;
    private List<String> notifications;

    public ReaderDto getReaderDto() {
        return readerDto;
    }

    public void setReaderDto(ReaderDto readerDto) {
        this.readerDto = readerDto;
    }

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }

    public void setBookDtoList(List<BookDto> bookDtoList) {
        this.bookDtoList = bookDtoList;
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<String> notifications) {
        this.notifications = notifications;
    }
}
