package com.krieger.document.manager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReferenceDto {
    private Long id;
    private String referenceText;
}
