package com.digitalbooks.reader.predicates;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import com.digitalbooks.reader.entities.database.ReaderInfo;
import com.digitalbooks.reader.entities.database.ReaderSubscription;
import com.digitalbooks.reader.entities.models.BookDto;
import com.digitalbooks.reader.entities.payload.BookPayload;
import com.digitalbooks.reader.entities.payload.BookSubscriptionPayload;

public abstract class ReaderPredicates {

    public static final Predicate<Object> IS_NOT_NULL = (Objects::nonNull);

    public static final Predicate<Object> IS_NULL = IS_NOT_NULL.negate();

    public static final Predicate<String> IS_NOT_NULL_OR_BLANK_STRING = str -> IS_NOT_NULL.test(str)
            && !str.trim().isEmpty();

    public static final Predicate<String> IS_NULL_OR_BLANK_STRING = str -> IS_NULL.test(str) || str.trim().isEmpty();

    public static final Predicate<Double> IS_POSITIVE_NON_NULL_DOUBLE = num -> IS_NOT_NULL.test(num) && 0.0 <= num;

    public static final Predicate<Boolean> IS_NON_NULL_BOOLEAN = (IS_NOT_NULL::test);

    public static final Predicate<String> IS_NOT_BLANK_OR_EMPTY_STRING = str -> null != str && 0 != str.trim().length();

    public static final Predicate<BookPayload> IS_BOOK_PAYLOAD_WITH_ONE_BOOK = bookPayload -> IS_NOT_NULL
            .test(bookPayload) && 1 == bookPayload.getBookDtoList().size();

    public static final Predicate<BookDto> IS_VALID_BOOKDTO_WITHOUT_CONTENT = bookDto -> IS_NOT_BLANK_OR_EMPTY_STRING
            .test(bookDto.getTitle()) && IS_NOT_BLANK_OR_EMPTY_STRING.test(bookDto.getAuthor())
            && IS_NOT_BLANK_OR_EMPTY_STRING.test(bookDto.getCategory())
            && IS_POSITIVE_NON_NULL_DOUBLE.test(bookDto.getPrice())
            && IS_NOT_BLANK_OR_EMPTY_STRING.test(bookDto.getPublisher())
            && IS_NON_NULL_BOOLEAN.test(bookDto.getActive());

    public static final Predicate<BookPayload> IS_VALID_BOOK_PAYLOAD_WITH_ONE_BOOK_WITHOUT_CONTENT = bookPayload -> IS_BOOK_PAYLOAD_WITH_ONE_BOOK
            .test(bookPayload) && IS_VALID_BOOKDTO_WITHOUT_CONTENT.test(bookPayload.getBookDtoList().get(0));

    public static final BiPredicate<List<ReaderSubscription>, Long> READER_ALREADY_SUBSCRIBED = (tblReaderPaymentList,
                                                                                                 bookId) -> tblReaderPaymentList.stream().anyMatch(tblReaderPayment -> !tblReaderPayment.getCancelled()
            && bookId.equals(tblReaderPayment.getBookId()));

    public static final Predicate<ReaderSubscription> IS_WITHIN_REFUND_DURATION = tblReaderPayment -> 24 > Duration
            .between(tblReaderPayment.getSubscriptionDateTime(), LocalDateTime.now()).toHours();

    public static final Predicate<BookDto> IS_VALID_BOOKDTO_WITH_CONTENT = bookDto -> IS_NOT_NULL_OR_BLANK_STRING
            .test(bookDto.getTitle()) && IS_NOT_NULL_OR_BLANK_STRING.test(bookDto.getAuthor())
            && IS_NOT_NULL_OR_BLANK_STRING.test(bookDto.getCategory())
            && IS_NOT_NULL_OR_BLANK_STRING.test(bookDto.getContent())
            && IS_POSITIVE_NON_NULL_DOUBLE.test(bookDto.getPrice())
            && IS_NOT_NULL_OR_BLANK_STRING.test(bookDto.getPublisher())
            && IS_NON_NULL_BOOLEAN.test(bookDto.getActive());

    public static final Predicate<BookPayload> IS_VALID_BOOK_PAYLOAD_WITH_ONE_BOOK_WITH_CONTENT = bookPayload -> IS_BOOK_PAYLOAD_WITH_ONE_BOOK
            .test(bookPayload) && IS_VALID_BOOKDTO_WITH_CONTENT.test(bookPayload.getBookDtoList().get(0));

    public static final Predicate<BookSubscriptionPayload> IS_VALID_BOOK_SUBSCRIPTION_PAYLOAD = bookSubscriptionPayload -> IS_NOT_NULL
            .test(bookSubscriptionPayload.getBookId()) && IS_NOT_NULL.test(bookSubscriptionPayload.getReaderDto())
            && IS_NOT_NULL.test(bookSubscriptionPayload.getReaderDto().getName())
            && IS_NOT_NULL.test(bookSubscriptionPayload.getReaderDto().getEmailId())
            && IS_NOT_NULL.test(bookSubscriptionPayload.getReaderDto().getReaderId());

    public static final Predicate<BookSubscriptionPayload> IS_VALID_FIRST_TIME_BOOK_SUBSCRIPTION_PAYLOAD = bookSubscriptionPayload -> IS_NOT_NULL
            .test(bookSubscriptionPayload.getBookId()) && IS_NOT_NULL.test(bookSubscriptionPayload.getReaderDto())
            && IS_NOT_NULL.test(bookSubscriptionPayload.getReaderDto().getName())
            && IS_NOT_NULL.test(bookSubscriptionPayload.getReaderDto().getEmailId());

    public static final BiPredicate<BookSubscriptionPayload, ReaderInfo> IS_VALID_ACCOUNT_INFO = (bookSubscriptionPayload,
                                                                                                  tblReaderInfo) ->
            bookSubscriptionPayload.getReaderDto().getName().equals(tblReaderInfo.getName())
            && bookSubscriptionPayload.getReaderDto().getEmailId().equals(tblReaderInfo.getEmailId());

    public static final BiPredicate<ReaderInfo, String> EMAIL_ID_MATCHES = (tblReaderInfo, emailId) -> tblReaderInfo
            .getEmailId().equals(emailId);

    private ReaderPredicates() {

    }

}