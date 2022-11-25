package com.digitalbooks.author.services;


import static com.digitalbooks.author.constants.DigitalBooksExceptionConstants.BOOK_BLOCKED_OR_DOESNT_EXIST;
import static com.digitalbooks.author.constants.DigitalBooksExceptionConstants.INVALID_AUTHOR_ID;
import static com.digitalbooks.author.constants.DigitalBooksExceptionConstants.INVALID_PAYLOAD;
import static com.digitalbooks.author.constants.DigitalBooksExceptionConstants.STATUS_CODE_BOOK_BLOCKED_OR_DOESNT_EXIST;
import static com.digitalbooks.author.constants.DigitalBooksExceptionConstants.STATUS_CODE_INVALID_AUTHOR_ID;
import static com.digitalbooks.author.constants.DigitalBooksExceptionConstants.STATUS_CODE_INVALID_PAYLOAD;
import static com.digitalbooks.author.constants.DigitalBooksExceptionConstants.STATUS_CODE_SOMETHING_WENT_WRONG;
import static com.digitalbooks.author.predicates.AuthorPredicates.IS_VALID_AUTHOR_PAYLOAD;

import java.util.Optional;

import javax.transaction.Transactional;

import com.digitalbooks.author.entities.database.AuthorBook;
import com.digitalbooks.author.entities.database.AuthorCredential;
import com.digitalbooks.author.entities.database.AuthorInfo;
import com.digitalbooks.author.interfaces.Author;
import com.digitalbooks.author.repositories.AuthorBookRepository;
import com.digitalbooks.author.repositories.AuthorCredentialRepository;
import com.digitalbooks.author.repositories.AuthorInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalbooks.author.clients.BookClient;
import com.digitalbooks.author.constants.DigitalBooksExceptionConstants;
import com.digitalbooks.author.entities.payload.AuthorPayload;
import com.digitalbooks.author.entities.payload.BookPayload;
import com.digitalbooks.author.exceptions.DigitalBooksException;
import com.digitalbooks.author.security.config.PasswordEncoderConfig;
import com.digitalbooks.author.security.payload.CredentialPayload;
import feign.FeignException;

@Service
@Transactional
public class AuthorService implements Author {

    @Autowired
    private BookClient bookClient;

    @Autowired
    private AuthorInfoRepository tblAuthorInfoRepository;

    @Autowired
    private AuthorCredentialRepository tblAuthorCredentialRepository;

    @Autowired
    private AuthorBookRepository tblAuthorBookRepository;

    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;

    @Override
    public AuthorPayload signup(CredentialPayload credentialPayload) throws DigitalBooksException {

        AuthorPayload authorPayload = new AuthorPayload();
        Optional<AuthorCredential> tblAuthorCredentialOptional = tblAuthorCredentialRepository
                .findByUsername(credentialPayload.getUsername());

        if (tblAuthorCredentialOptional.isEmpty()) {
            AuthorCredential tblAuthorCredential = new AuthorCredential();
            tblAuthorCredential.setUsername(credentialPayload.getUsername());
            tblAuthorCredential
                    .setPassword(passwordEncoderConfig.passwordEncoder().encode(credentialPayload.getPassword()));
            tblAuthorCredentialRepository.saveAndFlush(tblAuthorCredential);
            AuthorInfo tblAuthorInfo = new AuthorInfo();
            tblAuthorInfo.setName(credentialPayload.getUsername());
            authorPayload.setAuthorId(tblAuthorInfoRepository.saveAndFlush(tblAuthorInfo).getAuthorId());
            authorPayload.setName(tblAuthorInfo.getName());
        } else {
            throw new DigitalBooksException(DigitalBooksExceptionConstants.STATUS_CODE_USERNAME_TAKEN,
                    DigitalBooksExceptionConstants.USERNAME_TAKEN);
        }
        return authorPayload;
    }

    @Override
    public AuthorPayload createBook(AuthorPayload authorPayload) throws DigitalBooksException {
        if (IS_VALID_AUTHOR_PAYLOAD.test(authorPayload)) {
            Optional<AuthorInfo> tblAuthorInfoOptional = tblAuthorInfoRepository
                    .findByName(authorPayload.getName());
            if (tblAuthorInfoOptional.isPresent()) {
                try {
                    createNewBook(authorPayload, tblAuthorInfoOptional.get());
                } catch (DigitalBooksException digitalBooksException) {
                    rethrowDigitalBooksException(digitalBooksException);
                }
            } else {
                throw new DigitalBooksException(STATUS_CODE_INVALID_AUTHOR_ID, INVALID_AUTHOR_ID);
            }
        } else {
            throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD, INVALID_PAYLOAD);
        }
        return authorPayload;
    }

    private void createNewBook(AuthorPayload authorPayload, AuthorInfo tblAuthorInfo) throws DigitalBooksException {
        BookPayload bookPayload = new BookPayload();
        bookPayload.setBookDtoList(authorPayload.getBookDtoList());
        bookPayload.getBookDtoList().get(0).setAuthorId(tblAuthorInfo.getAuthorId());
        bookPayload.getBookDtoList().get(0).setAuthor(tblAuthorInfo.getName());
        try {
            bookPayload = bookClient.createBook(bookPayload);
        } catch (FeignException feignException) {
            throw new DigitalBooksException(STATUS_CODE_SOMETHING_WENT_WRONG, feignException.getMessage());
        }
        if (null != bookPayload.getBookDtoList().get(0).getBookId()) {
            AuthorBook tblAuthorBook = new AuthorBook();
            tblAuthorBook.setBookId(bookPayload.getBookDtoList().get(0).getBookId());
            tblAuthorBook.setParentAuthorInfo(tblAuthorInfo);
            tblAuthorBookRepository.saveAndFlush(tblAuthorBook);
            authorPayload.setBookDtoList(bookPayload.getBookDtoList());
        } else {
            throw new DigitalBooksException(DigitalBooksExceptionConstants.STATUS_CODE_SOMETHING_WENT_WRONG,
                    DigitalBooksExceptionConstants.SOMETHING_WENT_WRONG);
        }
    }

    private void rethrowDigitalBooksException(DigitalBooksException digitalBooksException)
            throws DigitalBooksException {
        throw new DigitalBooksException(digitalBooksException.getStatusCode(), digitalBooksException.getMessage());
    }

    @Override
    public AuthorPayload editBook(AuthorPayload authorPayload, Long bookId)
            throws DigitalBooksException {
        if (IS_VALID_AUTHOR_PAYLOAD.test(authorPayload)) {
            Optional<AuthorInfo> tblAuthorInfoOptional = tblAuthorInfoRepository.findByName(authorPayload.getName());
            if (tblAuthorInfoOptional.isPresent()) {
                Optional<AuthorBook> tblAuthorBookOptional = tblAuthorBookRepository.findByBookId(bookId);
                try {
                    authorPayload.getBookDtoList().get(0).setAuthorId(tblAuthorInfoOptional.get().getAuthorId());
                    editBookIfValid(authorPayload, tblAuthorBookOptional);
                } catch (DigitalBooksException digitalBooksException) {
                    rethrowDigitalBooksException(digitalBooksException);
                }
            } else {
                throw new DigitalBooksException(STATUS_CODE_INVALID_AUTHOR_ID, INVALID_AUTHOR_ID);
            }
        } else {
            throw new DigitalBooksException(STATUS_CODE_INVALID_PAYLOAD, INVALID_PAYLOAD);
        }
        return authorPayload;
    }

    private void editBookIfValid(AuthorPayload authorPayload,
                                 Optional<AuthorBook> tblAuthorBookOptional) throws DigitalBooksException {
        if (tblAuthorBookOptional.isPresent()) {
            AuthorBook tblAuthorBook = tblAuthorBookOptional.get();
            if (authorPayload.getName().equals(tblAuthorBook.getParentAuthorInfo().getName())) {
                BookPayload bookPayload = new BookPayload();
                bookPayload.setBookDtoList(authorPayload.getBookDtoList());
                try {
                    bookClient.editBook(bookPayload);
                } catch (FeignException feignException) {
                    throw new DigitalBooksException(STATUS_CODE_SOMETHING_WENT_WRONG, feignException.getMessage());
                }
            } else {
                throw new DigitalBooksException(STATUS_CODE_INVALID_AUTHOR_ID, INVALID_AUTHOR_ID);
            }
        } else {
            throw new DigitalBooksException(STATUS_CODE_BOOK_BLOCKED_OR_DOESNT_EXIST, BOOK_BLOCKED_OR_DOESNT_EXIST);
        }
    }

    @Override
    public BookPayload getAllBooksForAuthor(String authorName) throws DigitalBooksException {
        BookPayload bookPayload=new BookPayload();
        Optional<AuthorInfo> tblAuthorInfoOptional = tblAuthorInfoRepository.findByName(authorName);
        if(tblAuthorInfoOptional.isPresent()) {
            bookPayload= bookClient.getAllBooksForAuthor(tblAuthorInfoOptional.get().getAuthorId());
        }
        return bookPayload;
    }

}