package com.krieger.document.manager.service;

import com.krieger.document.manager.dto.DocumentDto;
import com.krieger.document.manager.dto.DocumentWithDetailsDto;
import com.krieger.document.manager.entity.Document;
import com.krieger.document.manager.exception.DocumentManagerNotFoundException;
import com.krieger.document.manager.exception.DocumentManagerServerErrorException;
import com.krieger.document.manager.mapper.AuthorMapper;
import com.krieger.document.manager.mapper.DocumentMapper;
import com.krieger.document.manager.mapper.ReferenceMapper;
import com.krieger.document.manager.repository.DocumentRepository;
import com.krieger.document.manager.util.InputSanitizer;
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

    private final String notFoundMessage = "Document not found with id: %d";

    public DocumentWithDetailsDto createDocument(DocumentWithDetailsDto document) {
        try {
            authorService.processAuthors(document.getAuthors());
            referenceService.processReferences(document.getReferences());
            Document documentSaved = documentRepository.save(DocumentMapper.mapDocumentWithDetailsDtoToDocument(document));
            return DocumentMapper.mapDocumentToDetailedDto(documentSaved);
        } catch (Exception e) {
            throw new DocumentManagerServerErrorException("Error creating a new document", e);
        }
    }

    public DocumentWithDetailsDto getDocumentById(long id) {
        Optional<Document> document = documentRepository.findById(id);
        if (document.isPresent()) {
            return DocumentMapper.mapDocumentToDetailedDto(document.get());
        }
        throw new DocumentManagerNotFoundException(String.format(notFoundMessage, id));
    }

    public List<DocumentWithDetailsDto> getAllDocuments() {
        return documentRepository.findAll().stream()
                .map(DocumentMapper::mapDocumentToDetailedDto).collect(Collectors.toList());
    }

    public DocumentWithDetailsDto updateDocument(long id, DocumentDto documentDto) {
        Optional<Document> optionalDocument = documentRepository.findById(id);
        if (optionalDocument.isPresent()) {
            try {
                Document document = optionalDocument.get();
                document.setTitle(documentDto.getTitle());
                document.setBody(InputSanitizer.sanitize(documentDto.getBody()));
                return DocumentMapper.mapDocumentToDetailedDto(documentRepository.save(document));
            } catch (Exception e) {
                throw new DocumentManagerServerErrorException("Error updating document with id="+id, e);
            }

        } else {
            throw new DocumentManagerNotFoundException(String.format(notFoundMessage, id));
        }
    }

    public DocumentWithDetailsDto partialUpdateDocument(long id, DocumentWithDetailsDto documentDetails) {
        Optional<Document> optionalDocument = documentRepository.findById(id);
        if (optionalDocument.isPresent()) {
            try {
                Document document = optionalDocument.get();
                if (documentDetails.getTitle() != null) {
                    document.setTitle(documentDetails.getTitle());
                }
                if (documentDetails.getBody() != null) {
                    document.setBody(documentDetails.getBody());
                }
                if (documentDetails.getAuthors() != null) {
                    document.setAuthors(AuthorMapper.mapAuthorsDtoToAuthor(documentDetails.getAuthors()));
                }
                if (documentDetails.getReferences() != null) {
                    document.setReferences(ReferenceMapper.mapDtosToReferences(documentDetails.getReferences()));
                }
                return DocumentMapper.mapDocumentToDetailedDto(documentRepository.save(document));
            } catch (Exception e) {
                throw new DocumentManagerServerErrorException("Error updating document with id="+id, e);
            }
        } else {
            throw new DocumentManagerNotFoundException(String.format(notFoundMessage, id));
        }
    }

    public void deleteDocument(long id) {
        try {
            documentRepository.deleteById(id);
        } catch (Exception e) {
            throw new DocumentManagerServerErrorException(String.format(notFoundMessage, id));
        }
    }
}