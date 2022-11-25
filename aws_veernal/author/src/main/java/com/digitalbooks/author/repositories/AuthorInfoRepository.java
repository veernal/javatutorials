package com.digitalbooks.author.repositories;

import java.util.Optional;

import com.digitalbooks.author.entities.database.AuthorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorInfoRepository extends JpaRepository<AuthorInfo, Long> {

    public Optional<AuthorInfo> findByName(String name);
}
