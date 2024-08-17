package com.krieger.document.manager.service;

import com.krieger.document.manager.entity.Document;
import com.krieger.document.manager.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public Document getDocumentById(long id) {
        Optional<Document> document = documentRepository.findById(id);
        return document.orElse(null);
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document updateDocument(long id, Document documentDetails) {
        Optional<Document> optionalDocument = documentRepository.findById(id);
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            document.setTitle(documentDetails.getTitle());
            document.setBody(documentDetails.getBody());
            document.setAuthors(documentDetails.getAuthors());
            return documentRepository.save(document);
        } else {
            return null;
        }
    }

    public void deleteDocument(long id) {
        documentRepository.deleteById(id);
    }
}