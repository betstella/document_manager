package com.krieger.document.manager.mapper;

import com.krieger.document.manager.dto.AuthorDto;
import com.krieger.document.manager.entity.Author;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorMapperTest {

    @Test
    void testAuthorToAuthorDto() {
        Author author = new Author();
        author.setId(1L);
        author.setFirstname("firstname");
        author.setLastname("lastname");

        AuthorDto authorDto = AuthorMapper.mapAuthorToDto(author);
        assertEquals(author.getId(), authorDto.getId());
        assertEquals(author.getFirstname(), authorDto.getFirstname());
        assertEquals(author.getLastname(), authorDto.getLastname());
    }

    @Test
    void testMapDtoToAuthor() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(1L);
        authorDto.setFirstname("firstname");
        authorDto.setLastname("lastname");

        Author author = AuthorMapper.mapDtoToAuthor(authorDto);
        assertEquals(author.getId(), authorDto.getId());
        assertEquals(author.getFirstname(), authorDto.getFirstname());
        assertEquals(author.getLastname(), authorDto.getLastname());
    }
}
