package com.krieger.document.manager.service;

import com.krieger.document.manager.dto.AuthorDto;
import com.krieger.document.manager.dto.DocumentWithDetailsDto;
import com.krieger.document.manager.dto.ReferenceDto;
import com.krieger.document.manager.entity.Author;
import com.krieger.document.manager.entity.Document;
import com.krieger.document.manager.entity.Reference;
import com.krieger.document.manager.exception.DocumentManagerNotFoundException;
import com.krieger.document.manager.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DocumentServiceTest {
    @InjectMocks
    private DocumentService documentService;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private AuthorService authorService;

    @Mock
    private ReferenceService referenceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDocumentTest() {
        DocumentWithDetailsDto documentDto = DocumentWithDetailsDto.builder()
                .body("body")
                .title("title")
                .authors(new HashSet<>(Arrays.asList(
                        AuthorDto.builder().firstname("firstname").lastname("lastname").build(),
                        AuthorDto.builder().firstname("firstname2").lastname("lastname2").build()
                )))
                .references(new HashSet<>(Arrays.asList(
                        ReferenceDto.builder().referenceText("reference1").build(),
                        ReferenceDto.builder().referenceText("reference2").build()
                )))
                .build();

        Document document = getDocument();
        Mockito.when(documentRepository.save(Mockito.any())).thenReturn(document);

        documentService.createDocument(documentDto);
        Mockito.verify(documentRepository, Mockito.times(1)).save(Mockito.any());
    }

    private Document getDocument() {
        Author author1 = new Author();
        author1.setLastname("lastname");
        author1.setFirstname("firstname");
        Author author2 = new Author();
        author2.setLastname("lastname2");
        author2.setFirstname("firstname2");
        Reference reference = new Reference();
        reference.setReferenceText("reference1");
        Reference reference1 = new Reference();
        reference1.setReferenceText("reference2");
        Document document = new Document();
        document.setBody("body");
        document.setTitle("title");
        document.setAuthors(new ArrayList<>(Arrays.asList(
                author1, author2
        )));
        document.setReferences(new ArrayList<>(Arrays.asList(
                reference, reference1
        )));
        return document;
    }

    @Test
    void getDocumentByIdTest() {
        Document document = getDocument();
        Mockito.when(documentRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(document));

        documentService.getDocumentById(1L);
        Mockito.verify(documentRepository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test
    void getDocumentByIdNoResults() {
        Mockito.when(documentRepository.findById(Mockito.any())).thenReturn(java.util.Optional.empty());
        assertThrows(DocumentManagerNotFoundException.class, () -> documentService.getDocumentById(1L));
    }
}
