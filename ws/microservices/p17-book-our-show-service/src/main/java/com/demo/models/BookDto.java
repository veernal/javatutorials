package com.demo.models;

import java.time.LocalDate;

public class BookDto {

    private Long bookId;
    private String logo;
    private String title;
    private LocalDate publisherDate;
    private Boolean active;
    private Double price;



    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublisherDate() {
        return publisherDate;
    }

    public void setPublisherDate(LocalDate publisherDate) {
        this.publisherDate = publisherDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
