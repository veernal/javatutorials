package com.digitalbooks.reader.interfaces;

import com.digitalbooks.reader.entities.payload.BookSubscriptionPayload;
import com.digitalbooks.reader.entities.payload.InvoicePayload;
import com.digitalbooks.reader.entities.payload.NotificationPayload;
import com.digitalbooks.reader.entities.payload.ReaderPayload;
import com.digitalbooks.reader.exceptions.DigitalBooksException;

public interface Reader {

    public InvoicePayload subscribeBook(BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException;

    public ReaderPayload getSubscribedBooks(String emailId) throws DigitalBooksException;

    public ReaderPayload readBook(String emailId, Long bookId) throws DigitalBooksException;

    public InvoicePayload findBookBySubscriptionId(String emailId, Long subscriptionId) throws DigitalBooksException;

    public ReaderPayload cancelSubscribedBook(BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException;

    public void postNotification(NotificationPayload notificationPayload);

    public ReaderPayload getNotifications(String emailId);
}
