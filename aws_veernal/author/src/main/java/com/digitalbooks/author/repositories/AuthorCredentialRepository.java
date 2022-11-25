package com.digitalbooks.author.repositories;

import java.util.Optional;

import com.digitalbooks.author.entities.database.AuthorCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorCredentialRepository extends JpaRepository<AuthorCredential, Long> {

    public Optional<AuthorCredential> findByUsername(String username);
}
