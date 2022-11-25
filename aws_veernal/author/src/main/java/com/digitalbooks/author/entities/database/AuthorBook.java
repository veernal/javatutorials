package com.digitalbooks.author.entities.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AuthorBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTHOR_BOOK_ID")
    private Long authorBookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private AuthorInfo parentAuthorInfo;

    @Column(name = "BOOK_ID")
    private Long bookId;


    public Long getAuthorBookId() {
        return authorBookId;
    }

    public void setAuthorBookId(Long authorBookId) {
        this.authorBookId = authorBookId;
    }

    public AuthorInfo getParentAuthorInfo() {
        return parentAuthorInfo;
    }

    public void setParentAuthorInfo(AuthorInfo parentAuthorInfo) {
        this.parentAuthorInfo = parentAuthorInfo;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
