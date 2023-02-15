package de.andrena.hibernateworkshop.service.book;

import org.junit.jupiter.api.Test;

import static de.andrena.hibernateworkshop.test.book.BookBuilder.randomBook;
import static de.andrena.hibernateworkshop.test.book.BookDtoBuilder.bookDtoFrom;
import static de.andrena.hibernateworkshop.test.book.BookDtoBuilder.minimalBookDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class BookDtoTest {

    @Test
    void toFullDto() {
        var book = randomBook().withRandomAuthor().build();

        var bookDto = BookDto.toFullDto(book);

        assertThat(bookDto).isEqualTo(bookDtoFrom(book).build());
    }

    @Test
    void toMinimalDto() {
        var book = randomBook().withRandomAuthor().build();

        var bookDto = BookDto.toMinimalDto(book);

        assertThat(bookDto).isEqualTo(minimalBookDtoFrom(book).build());
    }

}