package com.krieger.document.manager.controller;

import com.krieger.document.manager.controller.v1.AuthorController;
import com.krieger.document.manager.dto.AuthorDto;
import com.krieger.document.manager.dto.AuthorWithDocumentsDto;
import com.krieger.document.manager.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {
    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testCreateAuthor() throws Exception {
        AuthorDto authorDto = AuthorDto.builder()
                .firstname("firstname")
                .lastname("lastname")
                .build();
        Mockito.when(authorService.createAuthor(Mockito.any(AuthorDto.class))).thenReturn(authorDto);
        this.mockMvc.perform(post("/v1/authors")
                .contentType("application/json")
                .content("""
                        {
                            "firstname": "firstname",
                            "lastname": "lastname"
                        }""")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetAuthorById() throws Exception {
        Mockito.when(authorService.getAuthorById(Mockito.anyLong())).thenReturn(new AuthorWithDocumentsDto());
        this.mockMvc.perform(get("/v1/authors/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetAllAuthors() throws Exception {
        Mockito.when(authorService.getAllAuthors()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/v1/authors")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateAuthorById() throws Exception {
        Mockito.when(authorService.updateAuthor(1L, new AuthorDto())).thenReturn(new AuthorDto());
        this.mockMvc.perform(put("/v1/authors/1")
                .contentType("application/json")
                .content("""
                {
                    "firstname": "firstname",
                    "lastname": "lastname"
                }
                """)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteResponse() throws Exception {
        this.mockMvc.perform(delete("/v1/authors/1")).andDo(print()).andExpect(status().isNoContent());
    }
}
