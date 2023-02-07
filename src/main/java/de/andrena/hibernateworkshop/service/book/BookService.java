package de.andrena.hibernateworkshop.service.book;

import de.andrena.hibernateworkshop.persistence.book.BookRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream().map(BookDto::toFullDto).toList();
    }

}
