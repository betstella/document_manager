package com.krieger.document.manager.mapper;

import com.krieger.document.manager.dto.ReferenceDto;
import com.krieger.document.manager.entity.Reference;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReferenceMapper {
    public static ReferenceDto mapReferenceToDto(Reference reference) {
        return ReferenceDto.builder()
                .id(reference.getId())
                .referenceText(reference.getReferenceText())
                .build();
    }
    public static Set<ReferenceDto> mapReferencesToDto(List<Reference> references) {
        return references.stream()
                .map(ReferenceMapper::mapReferenceToDto)
                .collect(Collectors.toSet());
    }

    public static Reference mapDtoToReference(ReferenceDto referenceDto) {
        Reference reference = new Reference();
        reference.setReferenceText(referenceDto.getReferenceText());
        reference.setId(referenceDto.getId());
        return reference;
    }

    public static List<Reference> mapDtosToReferences(Set<ReferenceDto> referenceDtos) {
        return referenceDtos.stream()
                .map(ReferenceMapper::mapDtoToReference)
                .collect(Collectors.toList());
    }
}
