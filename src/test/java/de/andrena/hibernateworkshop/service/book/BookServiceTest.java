package de.andrena.hibernateworkshop.service.book;

import de.andrena.hibernateworkshop.persistence.book.Book;
import de.andrena.hibernateworkshop.testinfrastructure.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.IntStream;

import static de.andrena.hibernateworkshop.testinfrastructure.AuthorBuilder.randomAuthor;
import static de.andrena.hibernateworkshop.testinfrastructure.BookBuilder.randomBook;
import static de.andrena.hibernateworkshop.testinfrastructure.BookDtoBuilder.bookDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class BookServiceTest extends IntegrationTest {

    @Autowired
    private BookService classUnderTest;

    @Test
    void getBooks_SingleBook() {
        var book = randomBook().withRandomAuthor().build();
        bookRepository.save(book);

        var bookDtos = classUnderTest.getBooks();

        var expectedBookDto = bookDtoFrom(book).build();
        assertThat(bookDtos).containsExactly(expectedBookDto);
    }

    @Test
    void getBooks_TwoBooks() {
        var book1 = randomBook().withRandomAuthor().build();
        var book2 = randomBook().withRandomAuthor().build();
        bookRepository.saveAll(List.of(book1, book2));

        var bookDtos = classUnderTest.getBooks();

        var expectedBookDto1 = bookDtoFrom(book1).build();
        var expectedBookDto2 = bookDtoFrom(book2).build();
        assertThat(bookDtos).containsExactly(expectedBookDto1, expectedBookDto2);
    }

    @Test
    void getBooks_OverNineThousandBooks() {
        // TODO Ruben Gehring 08.02.2023: Not yet sure how to fix this... But O(# authors) queries seems wrong.
        var author = randomAuthor().build();
        List<Book> books = IntStream.range(0, 251)
                .mapToObj(i -> randomBook().withRandomAuthor().build())
                .toList();
        bookRepository.saveAll(books);

        var bookDtos = classUnderTest.getBooks();

        List<BookDto> expectedBookDtos = books.stream()
                .map(book -> bookDtoFrom(book).build())
                .toList();
        assertThat(bookDtos).containsExactlyElementsOf(expectedBookDtos);
    }

}