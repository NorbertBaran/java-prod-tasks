package uj.jwzp.exam2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uj.jwzp.exam2020.models.Author;
import uj.jwzp.exam2020.models.Book;
import uj.jwzp.exam2020.repositories.AuthorsRepo;
import uj.jwzp.exam2020.repositories.BooksRepo;

import java.util.List;

@Service
public class AuthorsService {
    private AuthorsRepo authorsRepo;
    private BooksRepo booksRepo;

    @Autowired
    public AuthorsService(AuthorsRepo authorsRepo, BooksRepo booksRepo){
        this.authorsRepo = authorsRepo;
        this.booksRepo = booksRepo;
    }

    public ResponseEntity<List<Author>> getAuthors(){
        return new ResponseEntity<>(
                authorsRepo.findAll(),
                HttpStatus.OK
        );
    }

    public ResponseEntity<Author> getAuthor(Long id){
        Author author = authorsRepo.findById(id).orElse(null);
        return new ResponseEntity<>(
                author,
                author == null ? HttpStatus.NOT_FOUND : HttpStatus.OK
        );
    }

    public ResponseEntity<Author> postAuthor(Author author){
        authorsRepo.save(author);
        return new ResponseEntity<>(
                author,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Void> deleteAuthor(Long id){
        Author author = authorsRepo.findById(id).orElse(null);
        if(booksRepo.findFirstByAuthor(id).orElse(null) != null)
            return new ResponseEntity<>(
              HttpStatus.BAD_REQUEST
            );
        authorsRepo.deleteById(id);
        return new ResponseEntity<>(
                author == null ? HttpStatus.NOT_FOUND : HttpStatus.OK
        );
    }

}
