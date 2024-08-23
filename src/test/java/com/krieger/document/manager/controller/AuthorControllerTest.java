package com.krieger.document.manager.controller;

import com.krieger.document.manager.controller.v1.AuthorController;
import com.krieger.document.manager.dto.AuthorDto;
import com.krieger.document.manager.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorControllerTest {
    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGenerateASuccessfulResponseWhenCreatingAnAuthor() {
        AuthorDto authorDto = new AuthorDto();
        Mockito.when(authorService.createAuthor(Mockito.any(AuthorDto.class))).thenReturn(authorDto);

        ResponseEntity<AuthorDto> response = authorController.createAuthor(authorDto);

        Mockito.verify(authorService, Mockito.times(1)).createAuthor(authorDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authorDto, response.getBody());
    }


}
