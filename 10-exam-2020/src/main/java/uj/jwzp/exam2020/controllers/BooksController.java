package uj.jwzp.exam2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.jwzp.exam2020.models.Book;
import uj.jwzp.exam2020.services.BooksService;

import java.util.List;

@RestController
@RequestMapping(value = "/books", produces = {"application/json;charset=UTF-8"})
public class BooksController {
    private BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService){
        this.booksService = booksService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String byTitle){
        return byTitle == null ? booksService.getBooks() : booksService.getBooksByTitle(byTitle);
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        return booksService.getBook(id);
    }

    @PostMapping
    public ResponseEntity<Book> postBook(@RequestBody Book book){
        return booksService.postBook(book);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        return booksService.deleteBook(id);
    }
}
