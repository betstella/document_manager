package com.krieger.document.manager.mapper;

import com.krieger.document.manager.dto.DocumentDto;
import com.krieger.document.manager.dto.DocumentWithDetailsDto;
import com.krieger.document.manager.entity.Document;

public class DocumentMapper {
    public static DocumentDto mapDocumentToDto(Document document) {
        return DocumentDto.builder()
                .id(document.getId())
                .title(document.getTitle())
                .body(document.getBody())
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
        document.setBody(documentDto.getBody());
        document.setAuthors(AuthorMapper.mapAuthorsDtoToAuthor(documentDto.getAuthors()));
        document.setReferences(ReferenceMapper.mapDtosToReferences(documentDto.getReferences()));
        return document;
    }
}
