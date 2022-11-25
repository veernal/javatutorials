package com.digitalbooks;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.function.Supplier;

import com.digitalbooks.book.entities.database.BookInfo;
import com.digitalbooks.book.entities.database.BookSub;
import com.digitalbooks.book.entities.models.ReaderDto;
import com.digitalbooks.book.entities.payload.BookSubscriptionPayload;
import com.digitalbooks.book.repositories.BookInfoRepository;
import com.digitalbooks.book.repositories.BookSubRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.digitalbooks.book.entities.payload.BookPayload;
import com.digitalbooks.book.exceptions.DigitalBooksException;

import com.digitalbooks.book.services.BookService;

@SpringBootTest
class BookApplicationTests {

	@Autowired
	private BookService bookService;

	@MockBean
	private BookInfoRepository tblBookInfoRepository;

	@MockBean
	private BookSubRepository tblBookSubRepository;

	@Test
	void testSubscribeBook() throws DigitalBooksException {
		BookSubscriptionPayload bookPurchasePayload = dummyBookPurchasePayloadSupplier.get();
		BookInfo tblBookInfo = new BookInfo();
		tblBookInfo = dummyTblBookInfoSupplier.get();
		when(tblBookInfoRepository.findById(anyLong())).thenReturn(Optional.ofNullable(tblBookInfo));
		when(tblBookSubRepository.saveAndFlush(any(BookSub.class))).thenReturn(new BookSub());
		BookPayload bookPayload = bookService.subscribeBook(bookPurchasePayload);

		assertEquals(1L, bookPayload.getBookDtoList().get(0).getBookId());
		assertEquals(true, bookPayload.getBookDtoList().get(0).getActive());
		assertEquals("author", bookPayload.getBookDtoList().get(0).getAuthor());
		assertEquals("Education", bookPayload.getBookDtoList().get(0).getCategory());
	}

	private Supplier<BookSubscriptionPayload> dummyBookPurchasePayloadSupplier = () -> {
		BookSubscriptionPayload bookPurchasePayload = new BookSubscriptionPayload();
		bookPurchasePayload.setBookId(1L);
		bookPurchasePayload.setSubscriptionId(2L);
		ReaderDto readerDto = new ReaderDto();
		readerDto.setEmailId("abc@gmail.com");
		readerDto.setName("abcde");
		readerDto.setReaderId(1L);
		bookPurchasePayload.setReaderDto(readerDto);
		return bookPurchasePayload;
	};

	private Supplier<BookInfo> dummyTblBookInfoSupplier = () -> {
		BookInfo tblBookInfo = new BookInfo();
		tblBookInfo.setBookId(1L);
		tblBookInfo.setActive(true);
		tblBookInfo.setAuthor("author");
		tblBookInfo.setCategory("Education");
		return tblBookInfo;
	};

}
