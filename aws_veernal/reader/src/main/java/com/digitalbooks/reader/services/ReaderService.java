package com.digitalbooks.reader.services;

import com.digitalbooks.reader.clients.BookClient;
import com.digitalbooks.reader.entities.database.ReaderInfo;
import com.digitalbooks.reader.entities.database.ReaderNotification;
import com.digitalbooks.reader.entities.database.ReaderSubscription;
import com.digitalbooks.reader.entities.models.ReaderDto;
import com.digitalbooks.reader.entities.payload.*;
import com.digitalbooks.reader.exceptions.DigitalBooksException;
import com.digitalbooks.reader.interfaces.Reader;
import com.digitalbooks.reader.predicates.ReaderPredicates;
import com.digitalbooks.reader.repositories.ReaderInfoRepository;
import com.digitalbooks.reader.repositories.ReaderNotificationRepository;
import com.digitalbooks.reader.repositories.ReaderSubscriptionRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.digitalbooks.reader.constants.DigitalBooksExceptionConstants.ALREADY_SUBSCRIBED;
import static com.digitalbooks.reader.constants.DigitalBooksExceptionConstants.ERROR_RETRIEVING_BOOK_CONTENT;
import static com.digitalbooks.reader.constants.DigitalBooksExceptionConstants.INVALID_PAYLOAD;
import static com.digitalbooks.reader.constants.DigitalBooksExceptionConstants.INVALID_PAYMENT_ID;
import static com.digitalbooks.reader.constants.DigitalBooksExceptionConstants.NOT_SUBSCRIBED;
import static com.digitalbooks.reader.constants.DigitalBooksExceptionConstants.SOMETHING_WENT_WRONG;
import static com.digitalbooks.reader.constants.DigitalBooksExceptionConstants.STATUS_CODE_ALREADY_SUBSCRIBED;
import static com.digitalbooks.reader.constants.DigitalBooksExceptionConstants.STATUS_CODE_ERROR_RETRIEVING_BOOK_CONTENT;
import static com.digitalbooks.reader.constants.DigitalBooksExceptionConstants.STATUS_CODE_INVALID_PAYLOAD;
import static com.digitalbooks.reader.constants.DigitalBooksExceptionConstants.STATUS_CODE_INVALID_PAYMENT_ID;
import static com.digitalbooks.reader.constants.DigitalBooksExceptionConstants.STATUS_CODE_NOT_SUBSCRIBED;
import static com.digitalbooks.reader.constants.DigitalBooksExceptionConstants.STATUS_CODE_SOMETHING_WENT_WRONG;
import static com.digitalbooks.reader.functions.ReaderFunctions.TBLREADERINFO_TO_READERDTO;
import static com.digitalbooks.reader.predicates.ReaderPredicates.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class ReaderService implements Reader {

    @Autowired
    private BookClient bookClient;

    @Autowired
    private ReaderInfoRepository tblReaderInfoRepository;

    @Autowired
    private ReaderSubscriptionRepository readerSubscriptionRepository;

    @Autowired
    private ReaderNotificationRepository readerNotificationRepository;


    @Override
    public InvoicePayload subscribeBook(BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException {
        InvoicePayload paymentInvoicePayload = new InvoicePayload();

        if (IS_VALID_FIRST_TIME_BOOK_SUBSCRIPTION_PAYLOAD.test(bookSubscriptionPayload)) {

            Optional<ReaderInfo> tblReaderInfoOptional = tblReaderInfoRepository
                    .findByEmailId(bookSubscriptionPayload.getReaderDto().getEmailId());

            ReaderInfo tblReaderInfo = (tblReaderInfoOptional.isPresent()) ? tblReaderInfoOptional.get()
                    : new ReaderInfo();
            List<ReaderSubscription> ReaderSubscriptionList = new ArrayList<>();
            try {
                ReaderSubscriptionList = populateReaderSubscriptionList(bookSubscriptionPayload, tblReaderInfoOptional,
                        tblReaderInfo);
            } catch (DigitalBooksException digitalBooksException) {
                rethrowDigitalBooksException(digitalBooksException);
            }

            tblReaderInfo.setReaderSubscriptionList(ReaderSubscriptionList);
            tblReaderInfoRepository.saveAndFlush(tblReaderInfo);
            bookSubscriptionPayload.getReaderDto().setReaderId(tblReaderInfo.getReaderId());
            BookPayload bookPayload = new BookPayload();
            try {
                bookPayload = bookClient.subscribeBook(bookSubscriptionPayload);
            } catch (FeignException feignException) {
                throw new DigitalBooksException(STATUS_CODE_SOMETHING_WENT_WRONG, feignException.getMessage());
            }
            if (IS_VALID_BOOK_PAYLOAD_WITH_ONE_BOOK_WITHOUT_CONTENT.test(bookPayload)) {
                setPaymentInvoice(bookSubscriptionPayload, paymentInvoicePayload, tblReaderInfo, bookPayload);
            } else {
                throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD, INVALID_PAYLOAD);
            }
        } else {
            throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD, INVALID_PAYLOAD);
        }

        return paymentInvoicePayload;
    }

    private void setPaymentInvoice(BookSubscriptionPayload bookSubscriptionPayload, InvoicePayload paymentInvoicePayload,
                                   ReaderInfo tblReaderInfo, BookPayload bookPayload) {
        Optional<ReaderSubscription> ReaderSubscriptionOptional = tblReaderInfo.getReaderSubscriptionList().stream()
                .filter(ReaderSubscription -> !ReaderSubscription.getCancelled()
                        && bookSubscriptionPayload.getBookId().equals(ReaderSubscription.getBookId()))
                .findFirst();
        if (ReaderSubscriptionOptional.isPresent()) {
            setReaderDtoInPaymentInvoice(paymentInvoicePayload, ReaderSubscriptionOptional);
            paymentInvoicePayload.setBookDto(bookPayload.getBookDtoList().get(0));
            paymentInvoicePayload.setSubscriptionDateTime(ReaderSubscriptionOptional.get().getSubscriptionDateTime());
            paymentInvoicePayload.setSubscriptionId(ReaderSubscriptionOptional.get().getSubscriptionId());
        }
    }

    private void setReaderDtoInPaymentInvoice(InvoicePayload paymentInvoicePayload,
                                              Optional<ReaderSubscription> ReaderSubscriptionOptional) {
        ReaderDto readerDto=new ReaderDto();
        readerDto.setName(ReaderSubscriptionOptional.get().getParentReaderInfo().getName());
        readerDto.setEmailId(ReaderSubscriptionOptional.get().getParentReaderInfo().getEmailId());
        readerDto.setReaderId(ReaderSubscriptionOptional.get().getParentReaderInfo().getReaderId());
        paymentInvoicePayload.setReaderDto(readerDto);
    }

    private void rethrowDigitalBooksException(DigitalBooksException digitalBooksException)
            throws DigitalBooksException {
        throw new DigitalBooksException(digitalBooksException.getStatusCode(), digitalBooksException.getMessage());
    }

    private List<ReaderSubscription> populateReaderSubscriptionList(BookSubscriptionPayload bookSubscriptionPayload,
                                                                Optional<ReaderInfo> tblReaderInfoOptional, ReaderInfo tblReaderInfo) throws DigitalBooksException {
        List<ReaderSubscription> ReaderSubscriptionList = new ArrayList<>();
        if (tblReaderInfoOptional.isPresent()) {
            if (IS_VALID_ACCOUNT_INFO.test(bookSubscriptionPayload, tblReaderInfo)) {
                try {
                    ReaderSubscriptionList = setReaderPaymentListForExistingReader(bookSubscriptionPayload, tblReaderInfo);
                } catch (DigitalBooksException digitalBooksException) {
                    rethrowDigitalBooksException(digitalBooksException);
                }
            } else {
                throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD, INVALID_PAYLOAD);
            }
        } else {
            ReaderSubscriptionList = setReaderPaymentListForNewReader(bookSubscriptionPayload, tblReaderInfo);
        }
        return ReaderSubscriptionList;
    }

    private List<ReaderSubscription> setReaderPaymentListForNewReader(BookSubscriptionPayload bookSubscriptionPayload,
                                                                    ReaderInfo tblReaderInfo) {
        List<ReaderSubscription> ReaderSubscriptionList = new ArrayList<>();
        tblReaderInfo.setName(bookSubscriptionPayload.getReaderDto().getName());
        tblReaderInfo.setEmailId(bookSubscriptionPayload.getReaderDto().getEmailId());
        ReaderSubscription ReaderSubscription = setNewReaderSubscription(bookSubscriptionPayload, tblReaderInfo);
        ReaderSubscriptionList.add(ReaderSubscription);
        return ReaderSubscriptionList;
    }

    private List<ReaderSubscription> setReaderPaymentListForExistingReader(BookSubscriptionPayload bookSubscriptionPayload, ReaderInfo tblReaderInfo) throws DigitalBooksException {
        List<ReaderSubscription> ReaderSubscriptionList = tblReaderInfo.getReaderSubscriptionList();
        if (READER_ALREADY_SUBSCRIBED.test(ReaderSubscriptionList, bookSubscriptionPayload.getBookId())) {
            throw new DigitalBooksException(STATUS_CODE_ALREADY_SUBSCRIBED, ALREADY_SUBSCRIBED);
        } else {
            ReaderSubscription ReaderSubscription = setNewReaderSubscription(bookSubscriptionPayload, tblReaderInfo);
            ReaderSubscriptionList.add(ReaderSubscription);
        }
        return ReaderSubscriptionList;
    }

    private ReaderSubscription setNewReaderSubscription(BookSubscriptionPayload bookSubscriptionPayload,
                                                    ReaderInfo tblReaderInfo) {
        ReaderSubscription ReaderSubscription = new ReaderSubscription();
        ReaderSubscription.setBookId(bookSubscriptionPayload.getBookId());
        ReaderSubscription.setParentReaderInfo(tblReaderInfo);
        ReaderSubscription.setSubscriptionDateTime(LocalDateTime.now());
        ReaderSubscription.setCancelled(false);
        return ReaderSubscription;
    }


    @Override
    public ReaderPayload getSubscribedBooks(String emailId) throws DigitalBooksException {
        ReaderPayload readerPayload = new ReaderPayload();
        Optional<ReaderInfo> tblReaderInfoOptional = tblReaderInfoRepository.findByEmailId(emailId);
        if (tblReaderInfoOptional.isPresent()) {
            ReaderInfo tblReaderInfo = tblReaderInfoOptional.get();
            try {
                readerPayload
                        .setBookDtoList(bookClient.getSubscribedBooks(tblReaderInfo.getReaderId()).getBookDtoList());
            } catch (FeignException feignException) {
                throw new DigitalBooksException(STATUS_CODE_SOMETHING_WENT_WRONG, feignException.getMessage());
            }
            ReaderDto readerDto = new ReaderDto();
            readerDto.setReaderId(tblReaderInfo.getReaderId());
            readerDto.setName(tblReaderInfo.getName());
            readerDto.setEmailId(tblReaderInfo.getEmailId());
            readerPayload.setReaderDto(readerDto);
        } else {
            throw new DigitalBooksException(STATUS_CODE_NOT_SUBSCRIBED, NOT_SUBSCRIBED);
        }
        return readerPayload;
    }


    @Override
    public ReaderPayload readBook(String emailId, Long bookId) throws DigitalBooksException {
        ReaderPayload readerPayload = new ReaderPayload();
        Optional<ReaderInfo> tblReaderInfoOptional = tblReaderInfoRepository.findByEmailId(emailId);
        if (tblReaderInfoOptional.isPresent()) {
            ReaderInfo tblReaderInfo = tblReaderInfoOptional.get();
            if (READER_ALREADY_SUBSCRIBED.test(tblReaderInfo.getReaderSubscriptionList(), bookId)) {
                BookSubscriptionPayload bookPurchasePayload = new BookSubscriptionPayload();
                bookPurchasePayload.setBookId(bookId);
                ReaderDto readerDto = TBLREADERINFO_TO_READERDTO.apply(tblReaderInfo);
                readerPayload.setReaderDto(readerDto);
                bookPurchasePayload.setReaderDto(readerDto);
                BookPayload bookPayload = new BookPayload();
                try {
                    bookPayload = bookClient.readBook(bookPurchasePayload);
                } catch (FeignException feignException) {
                    throw new DigitalBooksException(STATUS_CODE_SOMETHING_WENT_WRONG, feignException.getMessage());
                }
                if (IS_VALID_BOOK_PAYLOAD_WITH_ONE_BOOK_WITH_CONTENT.test(bookPayload)) {
                    readerPayload.setBookDtoList(bookPayload.getBookDtoList());
                } else {
                    throw new DigitalBooksException(STATUS_CODE_ERROR_RETRIEVING_BOOK_CONTENT,
                            ERROR_RETRIEVING_BOOK_CONTENT);
                }
            }
        } else {
            throw new DigitalBooksException(STATUS_CODE_NOT_SUBSCRIBED, NOT_SUBSCRIBED);
        }
        return readerPayload;
    }



    @Override
    public InvoicePayload findBookBySubscriptionId(String emailId, Long subscriptionId) throws DigitalBooksException {
        InvoicePayload paymentInvoicePayload=new InvoicePayload();
        Optional<ReaderSubscription> tblReaderPaymentOptional = readerSubscriptionRepository.findById(subscriptionId);
        if (tblReaderPaymentOptional.isPresent()) {
            ReaderInfo tblReaderInfo = tblReaderPaymentOptional.get().getParentReaderInfo();
            if (EMAIL_ID_MATCHES.test(tblReaderInfo, emailId)) {
                paymentInvoicePayload.setSubscriptionId(tblReaderPaymentOptional.get().getSubscriptionId());
                paymentInvoicePayload.setSubscriptionDateTime(tblReaderPaymentOptional.get().getSubscriptionDateTime());
                paymentInvoicePayload.setReaderDto(TBLREADERINFO_TO_READERDTO.apply(tblReaderInfo));
                BookPayload bookPayload = new BookPayload();
                try {
                    bookPayload = bookClient.getSubscribedBooks(tblReaderInfo.getReaderId());
                } catch (FeignException feignException) {
                    throw new DigitalBooksException(STATUS_CODE_SOMETHING_WENT_WRONG, feignException.getMessage());
                }
                if (IS_VALID_BOOK_PAYLOAD_WITH_ONE_BOOK_WITHOUT_CONTENT.test(bookPayload)) {
                    paymentInvoicePayload.setBookDto(bookPayload.getBookDtoList().get(0));
                } else {
                    throw new DigitalBooksException(STATUS_CODE_ERROR_RETRIEVING_BOOK_CONTENT,
                            ERROR_RETRIEVING_BOOK_CONTENT);
                }
            } else {
                throw new DigitalBooksException(STATUS_CODE_INVALID_PAYMENT_ID, INVALID_PAYMENT_ID);
            }
        } else {
            throw new DigitalBooksException(STATUS_CODE_INVALID_PAYMENT_ID, INVALID_PAYMENT_ID);
        }
        return paymentInvoicePayload;
    }


    @Override
    public ReaderPayload cancelSubscribedBook(BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException {
        List<String> message = new ArrayList<>();
        ReaderPayload readerPayload = new ReaderPayload();

        if (IS_VALID_BOOK_SUBSCRIPTION_PAYLOAD.test(bookSubscriptionPayload)) {
            Optional<ReaderInfo> tblReaderInfoOptional = tblReaderInfoRepository
                    .findByEmailId(bookSubscriptionPayload.getReaderDto().getEmailId());
            if (tblReaderInfoOptional.isPresent()
                    && IS_VALID_ACCOUNT_INFO.test(bookSubscriptionPayload, tblReaderInfoOptional.get())) {
                ReaderInfo tblReaderInfo = tblReaderInfoOptional.get();
                bookSubscriptionPayload.getReaderDto().setReaderId(tblReaderInfo.getReaderId());
                readerPayload.setReaderDto(TBLREADERINFO_TO_READERDTO.apply(tblReaderInfo));
                Optional<ReaderSubscription> tblReaderPaymentOptional = tblReaderInfo.getReaderSubscriptionList().stream()
                        .filter(tblReaderPayment -> bookSubscriptionPayload.getBookId().equals(tblReaderPayment.getBookId())
                                && !tblReaderPayment.getCancelled())
                        .findFirst();
                message.add("Book subscription has been cancelled");
                readerPayload.setNotifications(message);
                try {
                    unsubscribeBookIfSubscribed(bookSubscriptionPayload, readerPayload, tblReaderInfo,
                            tblReaderPaymentOptional);
                } catch (DigitalBooksException digitalBooksException) {
                    rethrowDigitalBooksException(digitalBooksException);
                }
            } else {
                throw new DigitalBooksException(STATUS_CODE_NOT_SUBSCRIBED, NOT_SUBSCRIBED);
            }
        } else {
            throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD, INVALID_PAYLOAD);
        }
        return readerPayload;
    }

    private void unsubscribeBookIfSubscribed(BookSubscriptionPayload bookSubscriptionPayload, ReaderPayload readerPayload,
                                             ReaderInfo tblReaderInfo, Optional<ReaderSubscription> tblReaderPaymentOptional)
            throws DigitalBooksException {
        if (tblReaderPaymentOptional.isPresent()
                && ReaderPredicates.IS_WITHIN_REFUND_DURATION.test(tblReaderPaymentOptional.get())) {
            ReaderSubscription tblReaderSubscription = tblReaderPaymentOptional.get();
            BookPayload bookPayload = new BookPayload();
            try {
                bookPayload = bookClient.unsubscribeBook(bookSubscriptionPayload);
            } catch (FeignException feignException) {
                throw new DigitalBooksException(STATUS_CODE_SOMETHING_WENT_WRONG, feignException.getMessage());
            }

            if (IS_VALID_BOOK_PAYLOAD_WITH_ONE_BOOK_WITHOUT_CONTENT.test(bookPayload)) {
                tblReaderSubscription.setCancelled(true);
                readerPayload.setBookDtoList(bookPayload.getBookDtoList());
                tblReaderInfoRepository.saveAndFlush(tblReaderInfo);
            } else {
                throw new DigitalBooksException(STATUS_CODE_SOMETHING_WENT_WRONG, SOMETHING_WENT_WRONG);
            }
        } else {
            throw new DigitalBooksException(STATUS_CODE_NOT_SUBSCRIBED, NOT_SUBSCRIBED);
        }
    }



    @Override
    public void postNotification(NotificationPayload notificationPayload) {
        List<ReaderNotification> tblReaderNotificationList = new ArrayList<>();
        List<ReaderSubscription> tblReaderPaymentList = readerSubscriptionRepository
                .findByBookId(notificationPayload.getBookId());

        tblReaderPaymentList.stream().filter(tblReaderPayment -> !tblReaderPayment.getCancelled())
                .map(ReaderSubscription::getParentReaderInfo).forEach(tblReaderInfo -> {
            ReaderNotification tblReaderNotification = new ReaderNotification();
            tblReaderNotification.setParentReaderInfo(tblReaderInfo);
            tblReaderNotification.setMessage(notificationPayload.getMessage());
            tblReaderNotification.setNotificationDateTime(LocalDateTime.now());
            tblReaderNotificationList.add(tblReaderNotification);
        });
        readerNotificationRepository.saveAllAndFlush(tblReaderNotificationList);

    }

    @Override
    public ReaderPayload getNotifications(String emailId) {
        ReaderPayload readerPayload = new ReaderPayload();
        Optional<ReaderInfo> tblReaderInfoOptional = tblReaderInfoRepository.findByEmailId(emailId);
        if (tblReaderInfoOptional.isPresent()) {
            ReaderInfo tblReaderInfo = tblReaderInfoOptional.get();
            readerPayload.setReaderDto(TBLREADERINFO_TO_READERDTO.apply(tblReaderInfo));
            readerPayload.setNotifications(tblReaderInfo.getReaderNotificationList().stream()
                    .map(ReaderNotification::getMessage).collect(Collectors.toList()));
        }
        return readerPayload;
    }
}
