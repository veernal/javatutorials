package com.digitalbooks;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.function.Supplier;

import com.digitalbooks.author.entities.database.AuthorInfo;
import com.digitalbooks.author.repositories.AuthorCredentialRepository;
import com.digitalbooks.author.repositories.AuthorInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.digitalbooks.author.entities.payload.AuthorPayload;
import com.digitalbooks.author.exceptions.DigitalBooksException;
import com.digitalbooks.author.security.payload.CredentialPayload;
import com.digitalbooks.author.services.AuthorService;

@SpringBootTest
class AuthorApplicationTests {

	@Autowired
	private AuthorService authorService;

	@MockBean
	private AuthorCredentialRepository tblAuthorCredentialRepository;

	@MockBean
	private AuthorInfoRepository tblAuthorInfoRepository;

	@Test
	void testSignup() throws DigitalBooksException {
		when(tblAuthorCredentialRepository.findByUsername(anyString())).thenReturn(Optional.empty());
		when(tblAuthorInfoRepository.saveAndFlush(any(AuthorInfo.class)))
				.thenReturn(dummyTblAuthorInfoSupplier.get());
		AuthorPayload authorPayload = authorService.signup(dummyCredentialPayloadSupplier.get());
		assertEquals(1L, authorPayload.getAuthorId());
		assertEquals("author", authorPayload.getName());
	}

	private Supplier<AuthorInfo> dummyTblAuthorInfoSupplier = () -> {
		AuthorInfo tblAuthorInfo = new AuthorInfo();
		tblAuthorInfo.setAuthorId(1L);
		return tblAuthorInfo;
	};

	private Supplier<CredentialPayload> dummyCredentialPayloadSupplier = () -> {
		CredentialPayload credentialPayload = new CredentialPayload();
		credentialPayload.setUsername("author");
		credentialPayload.setPassword("author@123");
		return credentialPayload;
	};

}
