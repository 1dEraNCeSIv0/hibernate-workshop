package de.andrena.hibernateworkshop.service;

import de.andrena.hibernateworkshop.persistence.entity.Author;
import de.andrena.hibernateworkshop.persistence.entity.Book;
import de.andrena.hibernateworkshop.service.dto.AuthorDto;
import de.andrena.hibernateworkshop.service.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

class BookServiceTest extends IntegrationTest {

    @Autowired
    private BookService classUnderTest;

    // TODO: 06.02.2023 Factory methods for test objects are much needed it feels.

    @Test
    void getBooks_SingleBook() {
        var bookId = randomUUID();
        var authorId = randomUUID();


        var author = new Author(authorId, "author", "address", List.of());
        var book = new Book(bookId, "title", author);
        bookRepository.save(book);


        var bookDtos = classUnderTest.getBooks();

        var expectedBookDto = new BookDto(bookId, "title", new AuthorDto(authorId, "author", "address", List.of()));
        assertThat(bookDtos).containsExactly(expectedBookDto);
    }

    @Test
    void getBooks_MultipleBooks() {
        var bookId1 = randomUUID();
        var bookId2 = randomUUID();
        var authorId1 = randomUUID();
        var authorId2 = randomUUID();


        var author1 = new Author(authorId1, "author 1", "address 1", List.of());
        var book1 = new Book(bookId1, "title 1", author1);
        bookRepository.save(book1);

        var author2 = new Author(authorId2, "author 2", "address 2", List.of());
        var book2 = new Book(bookId2, "title 2", author2);
        bookRepository.save(book2);

        var bookDtos = classUnderTest.getBooks();

        var expectedBookDto1 = new BookDto(bookId1, "title 1", new AuthorDto(authorId1, "author 1", "address 1", List.of()));
        var expectedBookDto2 = new BookDto(bookId2, "title 2", new AuthorDto(authorId2, "author 2", "address 2", List.of()));
        assertThat(bookDtos).containsExactly(expectedBookDto1, expectedBookDto2);
    }

}