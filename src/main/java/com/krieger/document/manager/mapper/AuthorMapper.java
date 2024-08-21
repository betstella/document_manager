package com.krieger.document.manager.mapper;

import com.krieger.document.manager.dto.AuthorDto;
import com.krieger.document.manager.entity.Author;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorMapper {
    public static AuthorDto mapAuthorToDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .firstname(author.getFirstname())
                .lastname(author.getLastname())
                .build();
    }
    public static Set<AuthorDto> mapAuthorsToDto(List<Author> authors) {
        return authors.stream()
                .map(AuthorMapper::mapAuthorToDto)
                .collect(Collectors.toSet());
    }

    public static Author mapDtoToAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setFirstname(authorDto.getFirstname());
        author.setLastname(authorDto.getLastname());
        return author;
    }

    public static List<Author> mapAuthorsDtoToAuthor(Set<AuthorDto> authorDtos) {
        return authorDtos.stream()
                .map(AuthorMapper::mapDtoToAuthor)
                .collect(Collectors.toList());
    }
}
