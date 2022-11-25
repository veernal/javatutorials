package com.digitalbooks.reader.clients;

import com.digitalbooks.reader.entities.payload.BookSubscriptionPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.digitalbooks.reader.entities.payload.BookPayload;
import com.digitalbooks.reader.exceptions.DigitalBooksException;

@FeignClient(value = "Book", url = "http://localhost:9092")
public interface BookClient {

    public static final String BOOK_URL = "api/v1/digitalbooks/books";

    @PostMapping(BOOK_URL + "/subscribe")
    public BookPayload subscribeBook(@RequestBody BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException;

    @GetMapping(BOOK_URL + "/subscriptions/{readerId}")
    public BookPayload getSubscribedBooks(@PathVariable Long readerId) throws DigitalBooksException;

    @PostMapping(BOOK_URL + "/read")
    public BookPayload readBook(@RequestBody BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException;

    @PostMapping(BOOK_URL + "/unsubscribe")
    public BookPayload unsubscribeBook(@RequestBody BookSubscriptionPayload bookSubscriptionPayload)
            throws DigitalBooksException;
}
