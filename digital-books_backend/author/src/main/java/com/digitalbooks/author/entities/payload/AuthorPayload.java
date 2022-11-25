package com.digitalbooks.author.entities.payload;

import com.digitalbooks.author.entities.models.BookDto;

import java.util.List;

public class AuthorPayload {
    private Long authorId;
    private String name;
    private List<BookDto> bookDtoList;

    public Long getAuthorId() {
        return authorId;
    }
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }
    public void setBookDtoList(List<BookDto> bookDtoList) {
        this.bookDtoList = bookDtoList;
    }
}
