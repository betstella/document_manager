package com.krieger.document.manager.service;

import com.krieger.document.manager.entity.Author;
import com.krieger.document.manager.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author getAuthorById(long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.orElse(null);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author updateAuthor(long id, Author authorDetails) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setFirstname(authorDetails.getFirstname());
            author.setLastname(authorDetails.getLastname());
            author.setDocuments(authorDetails.getDocuments());
            return authorRepository.save(author);
        } else {
            return null;
        }
    }

    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }
}