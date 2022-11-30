package com.demo.labmanagement.labmanagement;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping("/author")
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @GetMapping("/author")
    public Iterable<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/author/{id}")
    public Optional<Author> getAuthor(@PathVariable("id") Long id) {
        return authorRepository.findById(id);
    }

    @PutMapping("/author/{id}")
    public Author updateAuthor(@RequestBody Author author, 
            @PathVariable("id") Long id) {
        if (!author.getId().equals(id)) {
            throw new IllegalArgumentException("Id value provided on URL does not match id on request body");
        }
        return authorRepository.save(author);
    }

    @DeleteMapping("/author/{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorRepository.deleteById(id);
    }
}