package com.digitalbooks.author.controllers;


import com.digitalbooks.author.interfaces.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.digitalbooks.author.entities.payload.AuthorPayload;
import com.digitalbooks.author.entities.payload.BookPayload;
import com.digitalbooks.author.exceptions.DigitalBooksException;


@CrossOrigin
@RestController
@RequestMapping("api/v1/digitalbooks/author")
public class AuthorController {

    @Autowired
    private Author author;

    @PostMapping("books")
    public AuthorPayload createBook(@RequestBody AuthorPayload authorPayload)
            throws DigitalBooksException {
        return author.createBook(authorPayload);
    }

    @PutMapping("books/{bookId}")
    public AuthorPayload editBook(@RequestBody AuthorPayload authorPayload, @PathVariable Long bookId)
            throws DigitalBooksException {
        return author.editBook(authorPayload, bookId);
    }

    @GetMapping("{authorName}/all")
    public BookPayload getAllBooks(@PathVariable String authorName)
            throws DigitalBooksException {
        return author.getAllBooksForAuthor(authorName);
    }
}