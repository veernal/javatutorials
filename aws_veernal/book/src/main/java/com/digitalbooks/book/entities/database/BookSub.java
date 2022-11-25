package com.digitalbooks.book.entities.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BookSub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_SUB_ID")
    private Long bookSubId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private BookInfo parentBookInfo;

    @Column(name = "READER_ID")
    private Long readerId;

    @Column(name="READER_EMAIL")
    private String readerEmail;

    @Column(name="READER_NAME")
    private String readerName;

    public Long getBookSubId() {
        return bookSubId;
    }
    public void setBookSubId(Long bookSubId) {
        this.bookSubId = bookSubId;
    }

    public BookInfo getParentBookInfo() {
        return parentBookInfo;
    }

    public void setParentBookInfo(BookInfo parentBookInfo) {
        this.parentBookInfo = parentBookInfo;
    }

    public Long getReaderId() {
        return readerId;
    }
    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }
    public String getReaderEmail() {
        return readerEmail;
    }
    public void setReaderEmail(String readerEmail) {
        this.readerEmail = readerEmail;
    }
    public String getReaderName() {
        return readerName;
    }
    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }
}
