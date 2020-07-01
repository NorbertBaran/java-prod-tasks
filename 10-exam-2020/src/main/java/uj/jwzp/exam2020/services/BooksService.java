package uj.jwzp.exam2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import uj.jwzp.exam2020.models.Book;
import uj.jwzp.exam2020.repositories.AuthorsRepo;
import uj.jwzp.exam2020.repositories.BooksRepo;

import java.util.List;

@Service
public class BooksService {
    private BooksRepo booksRepo;
    private AuthorsRepo authorsRepo;

    @Autowired
    public BooksService(BooksRepo booksRepo, AuthorsRepo authorsRepo){
        this.booksRepo = booksRepo;
        this.authorsRepo = authorsRepo;
    }

    public ResponseEntity<List<Book>> getBooks(){
        return new ResponseEntity<>(
                booksRepo.findAll(),
                HttpStatus.OK
        );
    }

    public ResponseEntity<Book> getBook(Long id){
        Book book = booksRepo.findById(id).orElse(null);
        return new ResponseEntity<>(
                book,
                book == null ? HttpStatus.NOT_FOUND : HttpStatus.OK
        );
    }

    public ResponseEntity<Book> postBook(Book book){
        if(book.getAuthor() != null && authorsRepo.findById(book.getAuthor()).orElse(null) == null)
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );
        booksRepo.save(book);
        return new ResponseEntity<>(
                book,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Void> deleteBook(Long id){
        Book book=booksRepo.findById(id).orElse(null);
        booksRepo.deleteById(id);
        return new ResponseEntity<>(
                book == null ? HttpStatus.NOT_FOUND : HttpStatus.OK
        );
    }

    public ResponseEntity<List<Book>> getBooksByTitle(String byTitle){
        if(byTitle.length() < 2)
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );
        return new ResponseEntity<>(
                booksRepo.findByTitleContainingIgnoreCase(byTitle),
                HttpStatus.OK
        );
    }

}
