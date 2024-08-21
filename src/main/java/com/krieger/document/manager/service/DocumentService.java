package com.krieger.document.manager.service;

import com.krieger.document.manager.dto.DocumentWithDetailsDto;
import com.krieger.document.manager.entity.Document;
import com.krieger.document.manager.mapper.DocumentMapper;
import com.krieger.document.manager.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ReferenceService referenceService;

    public DocumentWithDetailsDto createDocument(DocumentWithDetailsDto document) {
        authorService.processAuthors(document.getAuthors());
        referenceService.processReferences(document.getReferences());
        Document documentSaved = documentRepository.save(DocumentMapper.mapDocumentWithDetailsDtoToDocument(document));
        return DocumentMapper.mapDocumentToDetailedDto(documentSaved);
    }

    public DocumentWithDetailsDto getDocumentById(long id) {
        Optional<Document> document = documentRepository.findById(id);
        return document.map(DocumentMapper::mapDocumentToDetailedDto).orElse(null);
    }

    public List<DocumentWithDetailsDto> getAllDocuments() {
        return documentRepository.findAll().stream()
                .map(DocumentMapper::mapDocumentToDetailedDto).collect(Collectors.toList());
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