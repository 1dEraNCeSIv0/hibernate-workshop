package de.andrena.hibernateworkshop.service.book;

import org.junit.jupiter.api.Test;

import static de.andrena.hibernateworkshop.test.book.BookCreator.randomBookBuilder;
import static de.andrena.hibernateworkshop.test.book.BookDtoCreator.bookDtoFrom;
import static de.andrena.hibernateworkshop.test.book.BookDtoCreator.minimalBookDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class BookDtoTest {

    @Test
    void toFullDto() {
        var book = randomBookBuilder().withRandomAuthor().build();

        var bookDto = BookDto.toFullDto(book);

        assertThat(bookDto).isEqualTo(bookDtoFrom(book));
    }

    @Test
    void toMinimalDto() {
        var book = randomBookBuilder().withRandomAuthor().build();

        var bookDto = BookDto.toMinimalDto(book);

        assertThat(bookDto).isEqualTo(minimalBookDtoFrom(book));
    }

}