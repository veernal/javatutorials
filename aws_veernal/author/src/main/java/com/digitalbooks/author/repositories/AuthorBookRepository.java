package com.digitalbooks.author.repositories;

import com.digitalbooks.author.entities.database.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorBookRepository extends JpaRepository<AuthorBook, Long> {

    public Optional<AuthorBook> findByBookId(Long bookId);
}