package com.digitalbooks.author.security.service;

import java.util.ArrayList;
import java.util.Optional;

import com.digitalbooks.author.entities.database.AuthorCredential;
import com.digitalbooks.author.repositories.AuthorCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthorCredentialRepository tblAuthorCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AuthorCredential> tblAuthorCredentialOptional = tblAuthorCredentialRepository
                .findByUsername(username);
        if (tblAuthorCredentialOptional.isPresent()) {
            AuthorCredential tblAuthorCredential = tblAuthorCredentialOptional.get();
            return new User(tblAuthorCredential.getUsername(), tblAuthorCredential.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Author Not Found");
        }
    }

}
