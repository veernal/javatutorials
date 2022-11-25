package com.digitalbooks.reader.repositories;

import com.digitalbooks.reader.entities.database.ReaderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderInfoRepository extends JpaRepository<ReaderInfo, Long> {

    public Optional<ReaderInfo> findByEmailId(String emailId);
}