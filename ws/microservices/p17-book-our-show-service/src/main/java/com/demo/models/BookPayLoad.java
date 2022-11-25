package com.demo.models;

import java.util.List;

public class BookPayLoad {

    private List<BookDto> bookDtoList;

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }

    public void setBookDtoList(List<BookDto> bookDtoList) {
        this.bookDtoList = bookDtoList;
    }
}
