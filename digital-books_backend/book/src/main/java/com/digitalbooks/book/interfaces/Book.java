package com.digitalbooks.book.interfaces;

import com.digitalbooks.book.entities.payload.BookPayload;
import com.digitalbooks.book.entities.payload.BookSubscriptionPayload;
import com.digitalbooks.book.exceptions.DigitalBooksException;

public interface Book {

    public BookPayload searchBooks(String category, String author, Double price, String publisher);

    public BookPayload subscribeBook(BookSubscriptionPayload BookSubscriptionPayload) throws DigitalBooksException;

    public BookPayload getSubscribedBooks(Long readerId);

    public BookPayload readBook(BookSubscriptionPayload BookSubscriptionPayload) throws DigitalBooksException;

    public BookPayload createBook(BookPayload bookPayload) throws DigitalBooksException;

    public BookPayload editBook(BookPayload bookPayload) throws DigitalBooksException;

    public BookPayload unsubscribeBook(BookSubscriptionPayload BookSubscriptionPayload) throws DigitalBooksException;

    public BookPayload getAllBooksForAuthor(Long authorId);
}
