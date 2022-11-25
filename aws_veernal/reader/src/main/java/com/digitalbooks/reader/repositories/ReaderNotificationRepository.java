package com.digitalbooks.reader.repositories;

import com.digitalbooks.reader.entities.database.ReaderNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderNotificationRepository  extends JpaRepository<ReaderNotification, Long> {

}