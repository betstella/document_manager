package com.krieger.document.manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
// The use of dtos to avoid recursion in the json response
public class DocumentWithDetailsDto {
    private Long id;

    @NotBlank(message = "Title must not be blank")
    @Pattern(regexp = "[a-zA-Z]")
    private String title;
    @NotBlank(message = "Body must not be blank")
    private String body;
    @NotEmpty(message = "Authors must not be empty")
    private Set<AuthorDto> authors;
    @NotEmpty(message = "References must not be empty")
    private Set<ReferenceDto> references;
}
