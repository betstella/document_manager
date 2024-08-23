package com.krieger.document.manager.service;

import com.krieger.document.manager.dto.AuthorDto;
import com.krieger.document.manager.dto.AuthorWithDocumentsDto;
import com.krieger.document.manager.entity.Author;
import com.krieger.document.manager.exception.DocumentManagerNotFoundException;
import com.krieger.document.manager.exception.DocumentManagerServerErrorException;
import com.krieger.document.manager.mapper.AuthorMapper;
import com.krieger.document.manager.repository.AuthorRepository;
import com.krieger.document.manager.util.InputSanitizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    private final String notFoundMessage = "Author not found with id: %d";

    public AuthorDto createAuthor(AuthorDto author) {
        try {
            Author authorToSave = AuthorMapper.mapDtoToAuthor(author);
            Author savedAuthor = authorRepository.save(authorToSave);
            return AuthorMapper.mapAuthorToDto(savedAuthor);
        } catch (Exception e) {
            throw new DocumentManagerServerErrorException("Error creating a new author", e);
        }
    }

    public AuthorWithDocumentsDto getAuthorById(long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return AuthorMapper.mapAuthorToWithDocumentsDto(author.get());
        }
        throw new DocumentManagerNotFoundException(String.format(notFoundMessage, id));
    }

    public List<AuthorWithDocumentsDto> getAllAuthors() {
        return AuthorMapper.mapAuthorsToWithDocumentsDto(authorRepository.findAll());
    }

    public void processAuthors(Set<AuthorDto> authors) {
        for (AuthorDto author : authors) {
            Author existingAuthor = getAuthorByFirstAndLastName(author.getFirstname(), author.getLastname());
            if (existingAuthor == null) {
                long id = createAuthor(author).getId();
                author.setId(id);
            } else {
                author.setId(existingAuthor.getId());
            }
        }
    }

    public Author getAuthorByFirstAndLastName(String firstname, String lastname) {
        return authorRepository.findByFirstnameAndLastname(firstname, lastname);
    }

    public AuthorDto updateAuthor(long id, AuthorDto authorDetails) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            try {
                Author author = optionalAuthor.get();
                author.setFirstname(InputSanitizer.sanitize(authorDetails.getFirstname()));
                author.setLastname(InputSanitizer.sanitize(authorDetails.getLastname()));
                return AuthorMapper.mapAuthorToDto(authorRepository.save(author));
            } catch (Exception e) {
                throw new DocumentManagerServerErrorException("Error updating author id="+id, e);
            }
        } else {
            throw new DocumentManagerNotFoundException(String.format(notFoundMessage, id));
        }
    }

    public AuthorDto patchUpdateAuthor(long id, AuthorDto authorDetails) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            try {
                Author author = optionalAuthor.get();
                if (authorDetails.getFirstname() != null) {
                    author.setFirstname(InputSanitizer.sanitize(authorDetails.getFirstname()));
                }
                if (authorDetails.getLastname() != null) {
                    author.setLastname(InputSanitizer.sanitize(authorDetails.getLastname()));
                }
                return AuthorMapper.mapAuthorToDto(authorRepository.save(author));
            } catch (Exception e) {
                throw new DocumentManagerServerErrorException("Error updating author id="+id, e);
            }
        } else {
            throw new DocumentManagerNotFoundException(String.format(notFoundMessage, id));
        }
    }

    public void deleteAuthor(long id) {
        try {
            authorRepository.deleteById(id);
        } catch (Exception e) {
            throw new DocumentManagerServerErrorException("Error deleting author", e);
        }
    }
}