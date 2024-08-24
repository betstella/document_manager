package com.krieger.document.manager.service;

import com.krieger.document.manager.dto.AuthorDto;
import com.krieger.document.manager.dto.AuthorWithDocumentsDto;
import com.krieger.document.manager.entity.Author;
import com.krieger.document.manager.exception.DocumentManagerNotFoundException;
import com.krieger.document.manager.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthorServiceTest {
    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAuthorSuccessfully() {
        AuthorDto authorDto = AuthorDto.builder()
                .firstname("firstname")
                .lastname("lastname")
                .build();
        Author author = new Author();
        author.setLastname("lastname");
        author.setFirstname("firstname");
        author.setId(1L);
        Mockito.when(authorRepository.save(Mockito.any())).thenReturn(author);

        AuthorDto newAuthor = authorService.createAuthor(authorDto);
        assertEquals(author.getFirstname(), newAuthor.getFirstname());
        assertEquals(author.getLastname(), newAuthor.getLastname());
        assertEquals(author.getId(), newAuthor.getId());
        verify(authorRepository, times(1)).save(Mockito.any());
    }

    @Test
    void getAuthorByIdTest() {
        Author author = new Author();
        author.setId(1L);
        author.setFirstname("firstname");
        author.setLastname("lastname");
        author.setDocuments(new ArrayList<>());
        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        AuthorWithDocumentsDto authorDto = authorService.getAuthorById(1L);
        assertEquals(author.getId(), authorDto.getId());
        assertEquals(author.getFirstname(), authorDto.getFirstname());
        assertEquals(author.getLastname(), authorDto.getLastname());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void processAuthorsNonExistingWithoutIdTest() {
        Set<AuthorDto> authorDtos = new HashSet<>();
        AuthorDto authorDto = AuthorDto.builder()
                .firstname("firstname")
                .lastname("lastname")
                .build();
        authorDtos.add(authorDto);
        Author author = new Author();
        author.setFirstname("firstname");
        author.setLastname("lastname");
        author.setId(1L);
        Mockito.when(authorRepository.findByFirstnameAndLastname("firstname", "lastname")).thenReturn(null);
        Mockito.when(authorRepository.save(Mockito.any())).thenReturn(author);
        authorService.processAuthors(authorDtos);

        verify(authorRepository, times(1)).findByFirstnameAndLastname("firstname", "lastname");
        verify(authorRepository, times(1)).save(Mockito.any());
    }

    @Test
    void processAuthorsNonExistingWithIdTest() {
        Set<AuthorDto> authorDtos = new HashSet<>();
        AuthorDto authorDto = AuthorDto.builder()
                .firstname("firstname")
                .lastname("lastname")
                .id(1L)
                .build();
        authorDtos.add(authorDto);
        Author author = new Author();
        author.setFirstname("firstname");
        author.setLastname("lastname");
        author.setId(1L);
        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(authorRepository.save(Mockito.any())).thenReturn(author);
        authorService.processAuthors(authorDtos);

        verify(authorRepository, times(1)).findById(1L);
        verify(authorRepository, times(1)).save(Mockito.any());
    }

    @Test
    void processAuthorsExistingWithId() {
        Set<AuthorDto> authorDtos = new HashSet<>();
        AuthorDto authorDto = AuthorDto.builder()
                .firstname("firstname")
                .lastname("lastname")
                .id(1L)
                .build();
        authorDtos.add(authorDto);
        Author author = new Author();
        author.setFirstname("firstname");
        author.setLastname("lastname");
        author.setId(1L);
        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        authorService.processAuthors(authorDtos);

        verify(authorRepository, times(1)).findById(1L);
        verify(authorRepository, times(0)).save(Mockito.any());
    }

    @Test
    void updateAuthorTest() {
        AuthorDto authorDto = AuthorDto.builder()
                .firstname("new name")
                .lastname("lastname")
                .build();
        Author author = new Author();
        author.setId(1L);
        author.setFirstname("firstname");
        author.setLastname("lastname");

        Author author2 = new Author();
        author.setId(1L);
        author.setFirstname("new name");
        author.setLastname("lastname");
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));
        when(authorRepository.save(author)).thenReturn(author2);

        authorService.updateAuthor(1L, authorDto);
        verify(authorRepository, times(1)).findById(1L);
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    void authorByIdEmpty() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(DocumentManagerNotFoundException.class, () -> authorService.getAuthorById(1L));
    }
}
