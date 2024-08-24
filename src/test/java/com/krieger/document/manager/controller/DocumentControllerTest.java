package com.krieger.document.manager.controller;

import com.krieger.document.manager.controller.v1.DocumentController;
import com.krieger.document.manager.dto.AuthorDto;
import com.krieger.document.manager.dto.DocumentDto;
import com.krieger.document.manager.dto.DocumentWithDetailsDto;
import com.krieger.document.manager.dto.ReferenceDto;
import com.krieger.document.manager.service.DocumentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(DocumentController.class)
public class DocumentControllerTest {
    @MockBean
    private DocumentService documentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateDocument() throws Exception {
        DocumentWithDetailsDto document = DocumentWithDetailsDto.builder()
                .title("title")
                .body("body")
                .authors(new HashSet<>(Collections.singletonList(AuthorDto.builder().firstname("firstname").lastname("lastname").build())))
                .references(new HashSet<>(Collections.singletonList(ReferenceDto.builder().referenceText("reference").build())))
                .build();
        Mockito.when(documentService.createDocument(Mockito.any(DocumentWithDetailsDto.class))).thenReturn(document);

        this.mockMvc.perform(post("/v1/documents")
                .contentType("application/json")
                .content("""
                        {
                            "title": "Title",
                            "body": "body",
                            "authors": [
                                {
                                    "firstname": "firstname",
                                    "lastname": "lastname"
                                }
                            ],
                            "references": [
                                {
                                    "referenceText": "reference"
                                }
                            ]
                        }""")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetDocumentById() throws Exception {
        DocumentWithDetailsDto document = DocumentWithDetailsDto.builder()
                .title("title")
                .body("body")
                .build();
        Mockito.when(documentService.getDocumentById(Mockito.anyLong())).thenReturn(document);
        this.mockMvc.perform(get("/v1/documents/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetAllDocuments() throws Exception {
        Mockito.when(documentService.getAllDocuments()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/v1/documents")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testPutDocument() throws Exception {
        DocumentWithDetailsDto documentDto = DocumentWithDetailsDto.builder()
                .id(1L)
                .body("body")
                .title("title")
                .build();
        Mockito.when(documentService.updateDocument(Mockito.anyLong(), Mockito.any(DocumentDto.class))).thenReturn(documentDto);
        this.mockMvc.perform(put("/v1/documents/1")
                .contentType("application/json")
                .content("""
                        {
                            "id": 1,
                            "title": "title",
                            "body": "body"
                        }""")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "title": "title",
                            "body": "body",
                            "authors": null,
                            "references": null
                        }"""));
    }

    @Test
    void testPatchDocument() throws Exception {
        DocumentWithDetailsDto documentDto = DocumentWithDetailsDto.builder()
                .id(1L)
                .body("body")
                .title("title2")
                .build();
        Mockito.when(documentService.updateDocument(Mockito.anyLong(), Mockito.any(DocumentDto.class))).thenReturn(documentDto);
        this.mockMvc.perform(patch("/v1/documents/1")
                        .contentType("application/json")
                        .content("""
                        {
                            "title": "title2"
                        }""")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteResponse() throws Exception {
        this.mockMvc.perform(delete("/v1/documents/1")).andDo(print()).andExpect(status().isNoContent());
    }

}
