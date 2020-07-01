package uj.jwzp.exam2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uj.jwzp.exam2020.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepo extends JpaRepository<Book, Long> {
    public Optional<Book> findFirstByAuthor(Long author);
    public List<Book> findByTitleContainingIgnoreCase(String byTitle);
    //public List<Book> findByTitleContaining(String query);
}
