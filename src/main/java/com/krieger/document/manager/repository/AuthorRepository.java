package com.krieger.document.manager.repository;

import com.krieger.document.manager.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Using JpaRepository prevents sql injection
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByFirstnameAndLastname(String firstname, String lastname);
}
