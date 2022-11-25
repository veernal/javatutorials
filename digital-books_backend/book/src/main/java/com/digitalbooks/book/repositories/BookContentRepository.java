package com.digitalbooks.book.repositories;

import com.digitalbooks.book.entities.database.BookContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookContentRepository extends JpaRepository<BookContent, Long> {


}