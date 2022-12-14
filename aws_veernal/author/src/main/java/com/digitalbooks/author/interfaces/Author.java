package com.digitalbooks.author.interfaces;

import com.digitalbooks.author.entities.payload.AuthorPayload;
import com.digitalbooks.author.entities.payload.BookPayload;
import com.digitalbooks.author.exceptions.DigitalBooksException;
import com.digitalbooks.author.security.payload.CredentialPayload;

public interface Author {

    public AuthorPayload signup(CredentialPayload credentialPayload) throws DigitalBooksException;

    public AuthorPayload createBook(AuthorPayload authorPayload) throws DigitalBooksException;

    public AuthorPayload editBook(AuthorPayload authorPayload, Long bookId) throws DigitalBooksException;

    public BookPayload getAllBooksForAuthor(String authorName) throws DigitalBooksException;
}
