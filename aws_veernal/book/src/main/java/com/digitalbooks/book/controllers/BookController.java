package com.digitalbooks.book.controllers;

import com.digitalbooks.book.interfaces.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.digitalbooks.book.entities.payload.BookPayload;
import com.digitalbooks.book.entities.payload.BookSubscriptionPayload;
import com.digitalbooks.book.exceptions.DigitalBooksException;

@CrossOrigin
@RestController
@RequestMapping("api/v1/digitalbooks/books")
public class BookController {

    @Autowired
    private Book bookIf;

    @GetMapping("search")
    public BookPayload searchBooks(@RequestParam(name = "category", required = false) String category,
                                   @RequestParam(name = "author", required = false) String author,
                                   @RequestParam(name = "price", required = false) Double price,
                                   @RequestParam(name = "publisher", required = false) String publisher) {
        return bookIf.searchBooks(category, author, price, publisher);
    }

    @PostMapping("subscribe")
    public BookPayload buyBook(@RequestBody BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException {
        return bookIf.subscribeBook(bookSubscriptionPayload);
    }

    @GetMapping("subscriptions/{readerId}")
    public BookPayload getSubscribedBooks(@PathVariable Long readerId) {
        return bookIf.getSubscribedBooks(readerId);
    }

    @PostMapping("read")
    public BookPayload readBook(@RequestBody BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException {
        return bookIf.readBook(bookSubscriptionPayload);
    }

    @PostMapping("create")
    public BookPayload createBook(@RequestBody BookPayload bookPayload) throws DigitalBooksException {
        return bookIf.createBook(bookPayload);
    }

    @PutMapping("edit")
    public BookPayload editBook(@RequestBody BookPayload bookPayload) throws DigitalBooksException {
        return bookIf.editBook(bookPayload);
    }

    @PostMapping("unsubscribe")
    public BookPayload unsubscribeBook(@RequestBody BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException {
        return bookIf.unsubscribeBook(bookSubscriptionPayload);
    }

    @GetMapping("{authorId}/all")
    public BookPayload getAllBooksForAuthor(@PathVariable Long authorId) throws DigitalBooksException {
        return bookIf.getAllBooksForAuthor(authorId);
    }

}