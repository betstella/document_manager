package com.krieger.document.manager.controller.v1;

import com.krieger.document.manager.dto.DocumentDto;
import com.krieger.document.manager.dto.DocumentWithDetailsDto;
import com.krieger.document.manager.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentWithDetailsDto> createDocument(@RequestBody DocumentWithDetailsDto document) {
        DocumentWithDetailsDto createdDocument = documentService.createDocument(document);
        return ResponseEntity.ok(createdDocument);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentWithDetailsDto> getDocumentById(@PathVariable long id) {
        DocumentWithDetailsDto document = documentService.getDocumentById(id);
        return ResponseEntity.ok(document);
    }

    @GetMapping
    public ResponseEntity<List<DocumentWithDetailsDto>> getAllDocuments() {
        List<DocumentWithDetailsDto> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentWithDetailsDto> updateDocument(@PathVariable long id, @RequestBody DocumentDto documentDetails) {
        DocumentWithDetailsDto updatedDocument = documentService.updateDocument(id, documentDetails);
        return ResponseEntity.ok(updatedDocument);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DocumentWithDetailsDto> partialUpdateDocument(@PathVariable long id, @RequestBody DocumentWithDetailsDto documentDetails) {
        DocumentWithDetailsDto updatedDocument = documentService.partialUpdateDocument(id, documentDetails);
        return ResponseEntity.ok(updatedDocument);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
}