package com.krieger.document.manager.mapper;

import com.krieger.document.manager.dto.DocumentDto;
import com.krieger.document.manager.dto.DocumentWithDetailsDto;
import com.krieger.document.manager.entity.Author;
import com.krieger.document.manager.entity.Document;
import com.krieger.document.manager.entity.Reference;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentMapperTest {
    @Test
    void mapDocumentToDtoTest() {
        Document document = new Document();
        document.setId(1L);
        document.setTitle("title");
        document.setBody("body");

        DocumentDto documentDto = DocumentMapper.mapDocumentToDto(document);
        assertEquals(document.getId(), documentDto.getId());
        assertEquals(document.getTitle(), documentDto.getTitle());
        assertEquals(document.getBody(), documentDto.getBody());
    }

    @Test
    void mapDocumentToDetailDto() {
        Document document = new Document();
        document.setReferences(
            new ArrayList<>(Arrays.asList(
                new Reference(1L, "reference1", new ArrayList<>()),
                new Reference(2L, "reference2", new ArrayList<>())
            ))
        );
        document.setAuthors(new ArrayList<>(Arrays.asList(
            new Author(1L, "Jose", "Gomez", new ArrayList<>()),
            new Author(2L, "Leo", "Cascante", new ArrayList<>())
        )));
        document.setBody("document body");
        document.setTitle("document title");
        document.setId(1L);

        final DocumentWithDetailsDto documentWithDetailsDto = DocumentMapper.mapDocumentToDetailedDto(document);
        assertEquals(document.getId(), documentWithDetailsDto.getId());
        assertEquals(document.getAuthors().size(), documentWithDetailsDto.getAuthors().size());
        assertEquals(document.getReferences().size(), documentWithDetailsDto.getReferences().size());
        assertEquals(document.getTitle(), documentWithDetailsDto.getTitle());
        assertEquals(document.getBody(), documentWithDetailsDto.getBody());
        document.getAuthors().forEach(author -> {
            assertEquals(author.getId(), documentWithDetailsDto.getAuthors().stream().filter(authorDto -> authorDto.getId().equals(author.getId())).findFirst().get().getId());
            assertEquals(author.getFirstname(), documentWithDetailsDto.getAuthors().stream().filter(authorDto -> authorDto.getId().equals(author.getId())).findFirst().get().getFirstname());
            assertEquals(author.getLastname(), documentWithDetailsDto.getAuthors().stream().filter(authorDto -> authorDto.getId().equals(author.getId())).findFirst().get().getLastname());
        });
        document.getReferences().forEach(reference -> {
            assertEquals(reference.getId(), documentWithDetailsDto.getReferences().stream().filter(referenceDto -> referenceDto.getId().equals(reference.getId())).findFirst().get().getId());
            assertEquals(reference.getReferenceText(), documentWithDetailsDto.getReferences().stream().filter(referenceDto -> referenceDto.getId().equals(reference.getId())).findFirst().get().getReferenceText());
        });
    }

    @Test
    void mapDocumentWithDetailsDtoToDocumentTest() {
        Document document = new Document(1L, "title", "body", new ArrayList<>(), new ArrayList<>());

        DocumentWithDetailsDto documentDto = DocumentMapper.mapDocumentToDetailedDto(document);
        assertEquals(document.getId(), documentDto.getId());
        assertEquals(document.getTitle(), documentDto.getTitle());
        assertEquals(document.getBody(), documentDto.getBody());
    }
}
