package de.andrena.hibernateworkshop.service.book;

import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.persistence.book.Book;
import de.andrena.hibernateworkshop.service.author.AuthorDto;
import de.andrena.hibernateworkshop.testinfrastructure.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

class BookServiceTest extends IntegrationTest {

    @Autowired
    private BookService classUnderTest;

    @Test
    void getBooks_SingleBook() {
        var book = book();
        bookRepository.save(book);

        var bookDtos = classUnderTest.getBooks();

        var expectedBookDto = bookDto(book);
        assertThat(bookDtos).containsExactly(expectedBookDto);
    }

    @Test
    void getBooks_TwoBooks() {
        var book1 = book(1);
        var book2 = book(2);
        bookRepository.saveAll(List.of(book1, book2));

        var bookDtos = classUnderTest.getBooks();

        var expectedBookDto1 = bookDto(book1);
        var expectedBookDto2 = bookDto(book2);
        assertThat(bookDtos).containsExactly(expectedBookDto1, expectedBookDto2);
    }

    @Test
    void getBooks_OverNineThousandBooks() {
        List<Book> books = IntStream.range(0, 9001).mapToObj(this::book).toList();
        bookRepository.saveAll(books);

        var bookDtos = classUnderTest.getBooks();

        List<BookDto> expectedBookDtos = books.stream().map(this::bookDto).toList();
        assertThat(bookDtos).containsExactlyElementsOf(expectedBookDtos);
    }

    private Book book() {
        return book(0);
    }

    private Book book(int index) {
        Author author = author(index);
        String title = String.format("title %d", index);
        return new Book(randomUUID(), title, author);
    }

    private Author author(int index) {
        String name = String.format("name %d", index);
        String address = String.format("address %d", index);
        return new Author(randomUUID(), name, address, List.of());
    }

    private BookDto bookDto(Book book) {
        Author author = book.getAuthor();
        AuthorDto authorDto = new AuthorDto(author.getId(), author.getName(), author.getAddress(), List.of());
        return new BookDto(book.getId(), book.getTitle(), authorDto);
    }

}