package com.digitalbooks.reader.repositories;

import com.digitalbooks.reader.entities.database.ReaderSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaderSubscriptionRepository extends JpaRepository<ReaderSubscription, Long> {

    public List<ReaderSubscription> findByBookId(Long bookId);

}
