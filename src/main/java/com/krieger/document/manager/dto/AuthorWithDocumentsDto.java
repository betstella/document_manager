package com.krieger.document.manager.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AuthorWithDocumentsDto {
    private Long id;
    private String firstname;
    private String lastname;
    private Set<DocumentDto> documents;
}
