package com.krieger.document.manager.mapper;

import com.krieger.document.manager.dto.DocumentDto;
import com.krieger.document.manager.dto.DocumentWithDetailsDto;
import com.krieger.document.manager.entity.Document;
import com.krieger.document.manager.util.InputSanitizer;

//Mapper class to facilitate the conversion between Document entity and DocumentDto objects
public class DocumentMapper {
    public static DocumentDto mapDocumentToDto(Document document) {
        return DocumentDto.builder()
                .id(document.getId())
                .title(document.getTitle())
                .body(InputSanitizer.sanitize(document.getBody()))
                .build();
    }

    public static DocumentWithDetailsDto mapDocumentToDetailedDto(Document document) {
        return DocumentWithDetailsDto.builder()
                .id(document.getId())
                .title(document.getTitle())
                .body(document.getBody())
                .authors(AuthorMapper.mapAuthorsToDto(document.getAuthors()))
                .references(ReferenceMapper.mapReferencesToDto(document.getReferences()))
                .build();
    }

    public static Document mapDocumentWithDetailsDtoToDocument(DocumentWithDetailsDto documentDto) {
        Document document = new Document();
        document.setId(documentDto.getId());
        document.setTitle(documentDto.getTitle());
        document.setBody(InputSanitizer.sanitize(documentDto.getBody()));
        document.setAuthors(AuthorMapper.mapAuthorsDtoToAuthor(documentDto.getAuthors()));
        document.setReferences(ReferenceMapper.mapDtosToReferences(documentDto.getReferences()));
        return document;
    }
}
