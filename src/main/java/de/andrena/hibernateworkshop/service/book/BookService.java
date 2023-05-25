package de.andrena.hibernateworkshop.service.book;

import de.andrena.hibernateworkshop.exception.NotFoundException;
import de.andrena.hibernateworkshop.persistence.book.BookRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public BookDto getBook(UUID id) {
        return bookRepository.findById(id)
                .map(BookDto::toFullDto)
                .orElseThrow(() -> new NotFoundException("No book found for id %s.", id));
    }

    @Transactional(readOnly = true)
    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream()
                .map(BookDto::toFullDto)
                .toList();
    }

}
