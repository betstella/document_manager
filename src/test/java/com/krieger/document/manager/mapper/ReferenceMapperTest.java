package com.krieger.document.manager.mapper;

import com.krieger.document.manager.dto.ReferenceDto;
import com.krieger.document.manager.entity.Reference;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReferenceMapperTest {
    @Test
    void mapReferenceToDtoTest() {
        Reference reference = new Reference(1L, "reference1", new ArrayList<>());
        ReferenceDto referenceDto = ReferenceMapper.mapReferenceToDto(reference);
        assertEquals(reference.getId(), referenceDto.getId());
        assertEquals(reference.getReferenceText(), referenceDto.getReferenceText());
    }

    @Test
    void mapDtoToReferenceTest() {
        ReferenceDto referenceDto = ReferenceDto.builder()
                .id(1L)
                .referenceText("reference1")
                .build();
        Reference reference = ReferenceMapper.mapDtoToReference(referenceDto);
        assertEquals(referenceDto.getId(), reference.getId());
        assertEquals(referenceDto.getReferenceText(), reference.getReferenceText());
    }
}
