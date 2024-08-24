package com.krieger.document.manager.service;

import com.krieger.document.manager.dto.ReferenceDto;
import com.krieger.document.manager.entity.Reference;
import com.krieger.document.manager.exception.DocumentManagerInvalidInput;
import com.krieger.document.manager.mapper.ReferenceMapper;
import com.krieger.document.manager.repository.ReferenceRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ReferenceService {
    @Autowired
    private ReferenceRepository referenceRepository;

    private Reference saveReference(Reference reference) {
        return referenceRepository.save(reference);
    }

    public void processReferences(Set<ReferenceDto> referenceData) {
        for (ReferenceDto reference : referenceData) {
            if (StringUtils.isBlank(reference.getReferenceText())) {
                throw new DocumentManagerInvalidInput("Reference text cannot be null");
            }
            long id = saveReference(ReferenceMapper.mapDtoToReference(reference)).getId();
            reference.setId(id);
        }
    }
}
