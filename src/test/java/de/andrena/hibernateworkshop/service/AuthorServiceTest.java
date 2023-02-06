package de.andrena.hibernateworkshop.service;

import de.andrena.hibernateworkshop.persistence.entity.Author;
import de.andrena.hibernateworkshop.persistence.entity.Book;
import de.andrena.hibernateworkshop.service.dto.AuthorDto;
import de.andrena.hibernateworkshop.service.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

class AuthorServiceTest extends IntegrationTest {

    @Autowired
    private AuthorService classUnderTest;

    // TODO: 06.02.2023 Go over all tests, ensure consistent style
    @Test
    void getAuthor() {
        var authorId = randomUUID();
        var bookId = randomUUID();

        var author = new Author(authorId, "name", "address", new ArrayList<>());
        var book = new Book(bookId, "title", author);
        author.getBooks().add(book);

        authorRepository.save(author);

        var expectedBookDto = new BookDto(bookId, "title", null);
        var expectedAuthorDto = new AuthorDto(authorId, "name", "address", List.of(expectedBookDto));

        var authorDto = classUnderTest.getAuthor(authorId);

        assertThat(authorDto).isEqualTo(expectedAuthorDto);
    }

}