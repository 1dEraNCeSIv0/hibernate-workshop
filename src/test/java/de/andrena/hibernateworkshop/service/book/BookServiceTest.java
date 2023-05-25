package de.andrena.hibernateworkshop.service.book;

import de.andrena.hibernateworkshop.test.IntegrationTest;
import de.andrena.hibernateworkshop.test.book.BookDtoCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.IntStream;

import static de.andrena.hibernateworkshop.test.book.BookCreator.randomBookBuilder;
import static de.andrena.hibernateworkshop.test.book.BookDtoCreator.bookDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class BookServiceTest extends IntegrationTest {

    @Autowired
    private BookService classUnderTest;

    @Test
    void getBook() {
        var book = randomBookBuilder().withRandomAuthor().build();
        bookRepository.save(book);

        var bookDtos = classUnderTest.getBook(book.getId());

        assertThat(bookDtos).isEqualTo(bookDtoFrom(book));
    }

    @Test
    void getBooks_SingleBook() {
        var book = randomBookBuilder().withRandomAuthor().build();
        bookRepository.save(book);

        var bookDtos = classUnderTest.getBooks();

        assertThat(bookDtos).containsExactly(bookDtoFrom(book));
    }

    @Test
    void getBooks_TwoBooks() {
        var book1 = randomBookBuilder().withRandomAuthor().build();
        var book2 = randomBookBuilder().withRandomAuthor().build();
        bookRepository.saveAll(List.of(book1, book2));

        var bookDtos = classUnderTest.getBooks();

        assertThat(bookDtos).containsExactly(bookDtoFrom(book1), bookDtoFrom(book2));
    }

    @Test
    void getBooks_OverTwoHundredBooks() {
        var books = IntStream.range(0, 201)
                .mapToObj(i -> randomBookBuilder().withRandomAuthor().build())
                .toList();
        bookRepository.saveAll(books);

        var bookDtos = classUnderTest.getBooks();

        var expectedBookDtos = books.stream()
                .map(BookDtoCreator::bookDtoFrom)
                .toList();
        assertThat(bookDtos).containsExactlyElementsOf(expectedBookDtos);
    }

}