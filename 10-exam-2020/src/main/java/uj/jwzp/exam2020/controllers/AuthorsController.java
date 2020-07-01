package uj.jwzp.exam2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.jwzp.exam2020.models.Author;
import uj.jwzp.exam2020.services.AuthorsService;

import java.util.List;

@RestController
@RequestMapping(value = "/authors", produces = "application/json;charset=UTF-8")
public class AuthorsController {
    private AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService){
        this.authorsService = authorsService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAuthors(){
        return authorsService.getAuthors();
    }

    @GetMapping("{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id){
        return authorsService.getAuthor(id);
    }

    @PostMapping
    public ResponseEntity<Author> postAuthor(@RequestBody Author author){
        return authorsService.postAuthor(author);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        return authorsService.deleteAuthor(id);
    }
}
