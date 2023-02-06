package de.andrena.hibernateworkshop.service.dto;

import de.andrena.hibernateworkshop.persistence.entity.Author;
import de.andrena.hibernateworkshop.persistence.entity.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

class BookDtoTest {

    @Test
    void toFullDto() {
        var bookId = randomUUID();
        var authorId = randomUUID();

        var author = new Author(authorId, "name", "address", new ArrayList<>());
        var book = new Book(bookId, "title", author);
        author.getBooks().add(book);

        var bookDto = BookDto.toFullDto(book);

        var expectedBookDto = new BookDto(bookId, "title", new AuthorDto(authorId, "name", "address", List.of()));
        assertThat(bookDto).isEqualTo(expectedBookDto);
    }

    @Test
    void toMinimalDto() {
        var bookId = randomUUID();

        var author = new Author(randomUUID(), "name", "address", new ArrayList<>());
        var book = new Book(bookId, "title", author);
        author.getBooks().add(book);

        var bookDto = BookDto.toMinimalDto(book);

        assertThat(bookDto).isEqualTo(new BookDto(bookId, "title", null));
    }

}