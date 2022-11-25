package com.digitalbooks.book.repositories;


import com.digitalbooks.book.entities.database.BookSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookSubRepository extends JpaRepository<BookSub, Long> {

    public List<BookSub> findByReaderId(Long readerId);


}