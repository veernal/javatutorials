package com.digitalbooks.book.services;

import com.digitalbooks.book.entities.database.BookContent;
import com.digitalbooks.book.entities.database.BookInfo;
import com.digitalbooks.book.entities.database.BookSub;
import com.digitalbooks.book.entities.models.BookDto;
import com.digitalbooks.book.entities.payload.BookSubscriptionPayload;
import com.digitalbooks.book.entities.payload.NotificationPayload;
import com.digitalbooks.book.interfaces.Book;
import static com.digitalbooks.book.constants.DigitalBooksExceptionConstants.BOOK_BLOCKED_OR_DOESNT_EXIST;
import static com.digitalbooks.book.constants.DigitalBooksExceptionConstants.INVALID_PAYLOAD;
import static com.digitalbooks.book.constants.DigitalBooksExceptionConstants.STATUS_CODE_BOOK_BLOCKED_OR_DOESNT_EXIST;
import static com.digitalbooks.book.constants.DigitalBooksExceptionConstants.STATUS_CODE_INVALID_PAYLOAD;
import static com.digitalbooks.book.functions.BookFunctions.*;
import static com.digitalbooks.book.predicates.BookPredicates.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.digitalbooks.book.repositories.BookInfoRepository;
import com.digitalbooks.book.repositories.BookSubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.digitalbooks.book.entities.payload.BookPayload;
import com.digitalbooks.book.exceptions.DigitalBooksException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookService implements Book {

    @Autowired
    private BookInfoRepository tblBookInfoRepository;

    @Autowired
    private BookSubRepository tblBookSubRepository;


    private static final String READER_NOTIFICATION_TOPIC= "digital_books_notification";

    @Override
    public BookPayload searchBooks(String category, String author, Double price, String publisher) {

        List<BookDto> bookDtoList= tblBookInfoRepository.findAll().stream()
                .filter(BookInfo::getActive)
                .filter(tblBookInfo->IS_NULL_OR_BLANK_STRING.test(category) || tblBookInfo.getCategory().equalsIgnoreCase(category))
                .filter(tblBookInfo-> IS_NULL_OR_BLANK_STRING.test(author) || tblBookInfo.getAuthor().equalsIgnoreCase(author))
                .filter(tblBookInfo-> IS_NULL.test(price) || tblBookInfo.getPrice()<=price)
                .filter(tblBookInfo-> IS_NULL_OR_BLANK_STRING.test(publisher) || tblBookInfo.getPublisher().equalsIgnoreCase(publisher))
                .map(BOOKINFO_TO_BOOKDTO)
                .collect(Collectors.toList());
        return setBookPayload(bookDtoList);
    }

    @Override
    public BookPayload subscribeBook(BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException {
        List<BookDto> bookDtoList = new ArrayList<>();
        if (IS_VALID_BOOK_SUBSCRIPTION_PAYLOAD.test(bookSubscriptionPayload)) {
            Optional<BookInfo> optionalTblBookInfo = tblBookInfoRepository.findById(bookSubscriptionPayload.getBookId());
            if (optionalTblBookInfo.isPresent() && IS_ACTIVE_BOOK.test(optionalTblBookInfo.get())) {
                BookInfo tblBookInfo = createTblBookSubEntry(bookSubscriptionPayload, optionalTblBookInfo.get());
                bookDtoList.add(BOOKINFO_TO_BOOKDTO.apply(tblBookInfo));
            } else {
                throw new DigitalBooksException(STATUS_CODE_BOOK_BLOCKED_OR_DOESNT_EXIST,BOOK_BLOCKED_OR_DOESNT_EXIST);
            }
        } else {
            throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD,INVALID_PAYLOAD);
        }
        return setBookPayload(bookDtoList);
    }

    private BookInfo createTblBookSubEntry(BookSubscriptionPayload bookSubscriptionPayload, BookInfo tblBookInfo) {
        BookSub tblBookSub = new BookSub();
        tblBookSub.setReaderId(bookSubscriptionPayload.getReaderDto().getReaderId());
        tblBookSub.setReaderName(bookSubscriptionPayload.getReaderDto().getName());
        tblBookSub.setReaderEmail(bookSubscriptionPayload.getReaderDto().getEmailId());
        tblBookSub.setParentBookInfo(tblBookInfo);
        tblBookSubRepository.saveAndFlush(tblBookSub);
        return tblBookInfo;
    }

    @Override
    public BookPayload getSubscribedBooks(Long readerId) {
        List<BookDto> bookDtoList=tblBookSubRepository.findByReaderId(readerId).stream()
                .map(BookSub::getParentBookInfo)
                .filter(IS_ACTIVE_BOOK)
                .map(BOOKINFO_TO_BOOKDTO)
                .collect(Collectors.toList());

        return setBookPayload(bookDtoList);
    }

    @Override
    public BookPayload readBook(BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException {

        List<BookDto> bookDtoList = new ArrayList<>();
        if (IS_VALID_BOOK_SUBSCRIPTION_PAYLOAD.test(bookSubscriptionPayload)) {
            Optional<BookInfo> optionalTblBookInfo = tblBookInfoRepository.findById(bookSubscriptionPayload.getBookId());
            if (optionalTblBookInfo.isPresent() && IS_ACTIVE_BOOK.test(optionalTblBookInfo.get())) {
                if (IS_VALID_SUBSCRIPTION.test(bookSubscriptionPayload, optionalTblBookInfo.get())) {
                    retrieveBookContent(bookDtoList, optionalTblBookInfo.get());
                } else {
                    throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD,INVALID_PAYLOAD);
                }
            } else {
                throw new DigitalBooksException(STATUS_CODE_BOOK_BLOCKED_OR_DOESNT_EXIST,BOOK_BLOCKED_OR_DOESNT_EXIST);
            }
        } else {
            throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD,INVALID_PAYLOAD);
        }
        return setBookPayload(bookDtoList);
    }

    private void retrieveBookContent(List<BookDto> bookDtoList, BookInfo tblBookInfo) {
        BookDto bookDto = BOOKINFO_TO_BOOKDTO.apply(tblBookInfo);
        if (IS_NOT_NULL.test(tblBookInfo.getChildBookContent()))
            bookDto.setContent(tblBookInfo.getChildBookContent().getContent());
        bookDtoList.add(bookDto);
    }

    @Override
    public BookPayload createBook(BookPayload bookPayload) throws DigitalBooksException {
        if (IS_VALID_BOOK_PAYLOAD_WITH_ONE_BOOK_WITH_CONTENT.test(bookPayload)) {
            BookDto bookDto = bookPayload.getBookDtoList().get(0);
            BookInfo tblBookInfo = BOOKDTO_TO_BOOKINFO.apply(bookDto);
            BookContent tblBookContent = new BookContent();
            tblBookContent.setParentBookInfo(tblBookInfo);
            tblBookContent.setContent(bookDto.getContent());
            tblBookInfo.setChildBookContent(tblBookContent);
            tblBookInfoRepository.saveAndFlush(tblBookInfo);
            bookDto.setBookId(tblBookInfo.getBookId());
            bookDto.setPublishedDate(tblBookInfo.getPublishedDate());
        }else {
            throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD,INVALID_PAYLOAD);
        }
        return bookPayload;
    }

    @Override
    public BookPayload editBook(BookPayload bookPayload) throws DigitalBooksException {

        if (IS_VALID_BOOK_PAYLOAD_WITH_ONE_BOOK_WITH_CONTENT.test(bookPayload)) {
            BookDto bookDto = bookPayload.getBookDtoList().get(0);
            Optional<BookInfo> optionalTblBookInfo = tblBookInfoRepository.findById(bookDto.getBookId());
            if (optionalTblBookInfo.isPresent() && IS_VALID_AUTHOR.test(bookDto, optionalTblBookInfo.get())) {
                BookInfo tblBookInfo=optionalTblBookInfo.get();
                Boolean isActiveStatusChanged = updateBookDetails(bookDto, tblBookInfo);
                if (isActiveStatusChanged.booleanValue()) {
                    notifyReader(tblBookInfo);
                }
            } else {
                throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD,INVALID_PAYLOAD);
            }
        } else {
            throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD,INVALID_PAYLOAD);
        }

        return bookPayload;
    }

    private Boolean updateBookDetails(BookDto bookDto, BookInfo tblBookInfo) {
        Boolean isActiveStatusChanged = !tblBookInfo.getActive().equals(bookDto.getActive());
        tblBookInfo.setActive(bookDto.getActive());
        tblBookInfo.setCategory(bookDto.getCategory());
        tblBookInfo.setLogo(bookDto.getLogo());
        tblBookInfo.setPrice(bookDto.getPrice());
        tblBookInfo.setPublishedDate(LocalDate.now());
        tblBookInfo.setPublisher(bookDto.getPublisher());
        tblBookInfo.setTitle(bookDto.getTitle());
        BookContent tblBookContent = tblBookInfo.getChildBookContent();
        if (IS_NOT_NULL.test(tblBookContent)) {
            tblBookContent.setContent(bookDto.getContent());
        }
        tblBookInfoRepository.saveAndFlush(tblBookInfo);
        return isActiveStatusChanged;
    }

    private NotificationPayload notifyReader(BookInfo tblBookInfo) {
        String notificationMessage = new StringBuilder().append(tblBookInfo.getAuthor()).append(" has ")
                .append((IS_ACTIVE_BOOK.test(tblBookInfo)) ? "UNBLOCKED" : "BLOCKED")
                .append(" your subscribed book: ").append(tblBookInfo.getTitle()).append(".").toString();
        NotificationPayload notificationPayload = new NotificationPayload();
        notificationPayload.setBookId(tblBookInfo.getBookId());
        notificationPayload.setMessage(notificationMessage);
        return notificationPayload;
    }

    @Override
    public BookPayload unsubscribeBook(BookSubscriptionPayload bookSubscriptionPayload) throws DigitalBooksException {
        List<BookDto> bookDtoList = new ArrayList<>();
        if (IS_VALID_BOOK_SUBSCRIPTION_PAYLOAD.test(bookSubscriptionPayload)) {
            Optional<BookSub> tblBookSubOptional = findTblBookSubByReaderIdAndBookId(bookSubscriptionPayload);
            if (tblBookSubOptional.isPresent()) {
                bookDtoList.add(BOOKINFO_TO_BOOKDTO.apply(tblBookSubOptional.get().getParentBookInfo()));
                tblBookSubRepository.delete(tblBookSubOptional.get());
            } else {
                throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD,INVALID_PAYLOAD);
            }
        } else {
            throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD,INVALID_PAYLOAD);
        }
        return setBookPayload(bookDtoList);
    }

    private Optional<BookSub> findTblBookSubByReaderIdAndBookId(BookSubscriptionPayload bookSubscriptionPayload) {
        return tblBookSubRepository
                .findByReaderId(bookSubscriptionPayload.getReaderDto().getReaderId()).stream()
                .filter(tblBookSub -> tblBookSub.getParentBookInfo().getBookId()
                        .equals(bookSubscriptionPayload.getBookId()))
                .findFirst();
    }

    private BookPayload setBookPayload(List<BookDto> bookDtoList) {
        BookPayload bookPayload=new BookPayload();
        bookPayload.setBookDtoList(bookDtoList);
        return bookPayload;
    }

    @Override
    public BookPayload getAllBooksForAuthor(Long authorId) {
        BookPayload bookPayload = new BookPayload();
        List<BookDto> bookDtoList=new ArrayList<>();
        tblBookInfoRepository.findByAuthorId(authorId).stream()
                .forEach(tblBookInfo->{
                    BookDto bookDto=BOOKINFO_TO_BOOKDTO.apply(tblBookInfo);
                    bookDto.setContent(tblBookInfo.getChildBookContent().getContent());
                    bookDtoList.add(bookDto);
                });

        bookPayload.setBookDtoList(bookDtoList);
        return bookPayload;
    }
}
