package com.krieger.document.manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {
    private Long id;
    @NotBlank(message = "Firstname must not be blank")
    @Pattern(regexp = "[a-zA-Z]")
    private String firstname;
    @NotBlank(message = "Lastname must not be blank")
    @Pattern(regexp = "[a-zA-Z]")
    private String lastname;
}
