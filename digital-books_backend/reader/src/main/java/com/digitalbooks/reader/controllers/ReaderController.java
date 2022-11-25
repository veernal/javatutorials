package com.digitalbooks.reader.controllers;

import com.digitalbooks.reader.entities.payload.BookSubscriptionPayload;
import com.digitalbooks.reader.entities.payload.InvoicePayload;
import com.digitalbooks.reader.interfaces.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.digitalbooks.reader.entities.payload.ReaderPayload;
import com.digitalbooks.reader.exceptions.DigitalBooksException;

@CrossOrigin
@RestController
@RequestMapping("api/v1/digitalbooks/readers")
public class ReaderController {

    @Autowired
    private Reader reader;

    @PostMapping("books/subscribe")
    public InvoicePayload subscribeBook(@RequestBody BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException {
        return reader.subscribeBook(bookSubscriptionPayload);
    }

    @GetMapping("{emailId}/books")
    public ReaderPayload getSubscribedBooks(@PathVariable String emailId) throws DigitalBooksException {
        return reader.getSubscribedBooks(emailId);
    }

    @GetMapping("{emailId}/books/{bookId}")
    public ReaderPayload readBook(@PathVariable String emailId, @PathVariable Long bookId) throws DigitalBooksException {
        return reader.readBook(emailId, bookId);
    }

    @PostMapping("{emailId}/books/find/{subscriptionId}")
    public InvoicePayload findBookBySubscriptionId(@PathVariable String emailId, @PathVariable Long subscriptionId)
            throws DigitalBooksException {
        return reader.findBookBySubscriptionId(emailId, subscriptionId);
    }

    @PostMapping("{emailId}/books/{bookId}/cancel")
    public ReaderPayload cancelSubscribedBook(@RequestBody BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException {
        return reader.cancelSubscribedBook(bookSubscriptionPayload);
    }

    @GetMapping("{emailId}/notification")
    public ReaderPayload getNotifications(@PathVariable String emailId) {
        return reader.getNotifications(emailId);
    }

}