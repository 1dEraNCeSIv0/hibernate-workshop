package de.andrena.hibernateworkshop.test.book;

import de.andrena.hibernateworkshop.persistence.book.Book;
import de.andrena.hibernateworkshop.service.author.AuthorDto;
import de.andrena.hibernateworkshop.service.book.BookDto;
import de.andrena.hibernateworkshop.test.author.AuthorDtoBuilder;

import java.util.UUID;

public final class BookDtoBuilder {

    private static final AuthorDto NO_AUTHOR = null;

    private UUID id;
    private String title;
    private AuthorDto author;

    private BookDtoBuilder(UUID id, String title, AuthorDto author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public static BookDtoBuilder bookDtoFrom(Book book) {
        var authorDto = book.getAuthor()
                .map(author -> AuthorDtoBuilder.authorDtoFrom(author).build())
                .orElse(null);
        return minimalBookDtoFrom(book).withAuthor(authorDto);
    }

    public static BookDtoBuilder minimalBookDtoFrom(Book book) {
        return new BookDtoBuilder(book.getId(), book.getTitle(), NO_AUTHOR);
    }

    public BookDtoBuilder withAuthor(AuthorDto author) {
        this.author = author;
        return this;
    }

    public BookDto build() {
        return new BookDto(id, title, author);
    }

}
