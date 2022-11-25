package com.digitalbooks.author.entities.payload;

import com.digitalbooks.author.entities.models.BookDto;

import java.util.List;

public class BookPayload {

    private List<BookDto> bookDtoList;

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }

    public void setBookDtoList(List<BookDto> bookDtoList) {
        this.bookDtoList = bookDtoList;
    }
}
