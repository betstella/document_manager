package com.krieger.document.manager.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
// The use of dtos to avoid recursion in the json response
public class DocumentWithDetailsDto {
    private Long id;
    private String title;
    private String body;
    private Set<AuthorDto> authors;
    private Set<ReferenceDto> references;
}
