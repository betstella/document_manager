package com.krieger.document.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorWithDocumentsDto {
    private Long id;
    private String firstname;
    private String lastname;
    private Set<DocumentDto> documents;
}
