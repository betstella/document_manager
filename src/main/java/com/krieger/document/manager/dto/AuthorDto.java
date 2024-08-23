package com.krieger.document.manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private Long id;
    @NotBlank(message = "Firstname must not be blank")
    @Pattern(regexp = "[a-zA-Z]")
    private String firstname;
    @NotBlank(message = "Lastname must not be blank")
    @Pattern(regexp = "[a-zA-Z]")
    private String lastname;
}
