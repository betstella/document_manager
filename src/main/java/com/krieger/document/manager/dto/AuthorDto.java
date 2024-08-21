package com.krieger.document.manager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {
    private Long id;
    private String firstname;
    private String lastname;
}
