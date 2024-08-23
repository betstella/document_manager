package com.krieger.document.manager.repository;

import com.krieger.document.manager.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Using JpaRepository prevents sql injection
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
