package com.digitalbooks.book.functions;

import java.time.LocalDate;
import java.util.function.Function;
import com.digitalbooks.book.entities.database.BookInfo;
import com.digitalbooks.book.entities.models.BookDto;


public abstract class BookFunctions {

    public static final Function<BookDto, BookInfo> BOOKDTO_TO_BOOKINFO = bookDto -> {
        BookInfo bookInfoEntity = new BookInfo();
        bookInfoEntity.setAuthorId(bookDto.getAuthorId());
        bookInfoEntity.setLogo(bookDto.getLogo());
        bookInfoEntity.setTitle(bookDto.getTitle());
        bookInfoEntity.setCategory(bookDto.getCategory());
        bookInfoEntity.setPrice(bookDto.getPrice());
        bookInfoEntity.setAuthor(bookDto.getAuthor());
        bookInfoEntity.setPublisher(bookDto.getPublisher());
        bookInfoEntity.setPublishedDate(bookDto.getPublishedDate());
        bookInfoEntity.setActive(bookDto.getActive());
        return bookInfoEntity;
    };

    public static final Function<BookInfo, BookDto> BOOKINFO_TO_BOOKDTO = bookInfoEntity -> {
        BookDto bookDto = new BookDto();
        bookDto.setBookId(bookInfoEntity.getBookId());
        bookDto.setLogo(bookInfoEntity.getLogo());
        bookDto.setTitle(bookInfoEntity.getTitle());
        bookDto.setCategory(bookInfoEntity.getCategory());
        bookDto.setPrice(bookInfoEntity.getPrice());
        bookDto.setAuthor(bookInfoEntity.getAuthor());
        bookDto.setAuthorId(bookInfoEntity.getAuthorId());
        bookDto.setPublisher(bookInfoEntity.getPublisher());
        bookDto.setPublishedDate(LocalDate.now());
        //bookDto.setPublishedDate(bookInfoEntity.getPublishedDate());
        bookDto.setActive(bookInfoEntity.getActive());
        return bookDto;
    };

    private BookFunctions() {

    }
}
