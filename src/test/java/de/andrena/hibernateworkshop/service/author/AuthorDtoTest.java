package de.andrena.hibernateworkshop.service.author;

import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.persistence.book.Book;
import de.andrena.hibernateworkshop.service.book.BookDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

// TODO Ruben Gehring 08.02.2023: This is disabled?
@Disabled
class AuthorDtoTest {

    @Test
    void toMinimalDto() {
        var authorId = randomUUID();
        var bookId = randomUUID();

        var author = new Author(authorId, "name", "address", new ArrayList<>());
        var book = new Book(bookId, "title", author);
        author.getBooks().add(book);

        var authorDto = AuthorDto.toMinimalDto(author);

        assertThat(authorDto).isEqualTo(new AuthorDto(authorId, "name", "address", List.of()));
    }

    @Test
    void toFullDto() {
        var authorId = randomUUID();
        var bookId = randomUUID();

        var author = new Author(authorId, "name", "address", new ArrayList<>());
        var book = new Book(bookId, "title", author);
        author.getBooks().add(book);

        var expectedBook = new BookDto(bookId, "title", null);
        var expectedAuthor = new AuthorDto(authorId, "name", "address", List.of(expectedBook));

        var authorDto = AuthorDto.toFullDto(author);

        assertThat(authorDto).isEqualTo(expectedAuthor);
    }

}