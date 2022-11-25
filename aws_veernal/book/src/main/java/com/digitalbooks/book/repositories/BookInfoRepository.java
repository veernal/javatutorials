package com.digitalbooks.book.repositories;

import com.digitalbooks.book.entities.database.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookInfoRepository extends JpaRepository<BookInfo, Long> {

    public List<BookInfo> findByAuthorId(Long authorId);

}
