package de.andrena.hibernateworkshop.test.book;

import de.andrena.hibernateworkshop.persistence.book.Book;
import de.andrena.hibernateworkshop.service.author.AuthorDto;
import de.andrena.hibernateworkshop.service.book.BookDto;
import de.andrena.hibernateworkshop.test.author.AuthorDtoCreator;

import java.util.UUID;

public final class BookDtoCreator {

    public static BookDto bookDtoFrom(Book book) {
        var author = book.getAuthor()
                .map(AuthorDtoCreator::authorDtoFrom)
                .orElse(null);
        return new BookDtoBuilder()
                .withId(book.getId())
                .withTitle(book.getTitle())
                .withAuthor(author)
                .build();
    }

    public static BookDto minimalBookDtoFrom(Book book) {
        return new BookDtoBuilder()
                .withId(book.getId())
                .withTitle(book.getTitle())
                .build();
    }

    private static class BookDtoBuilder {

        private UUID id;
        private String title;
        private AuthorDto author;

        private BookDtoBuilder() {}

        private BookDtoBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        private BookDtoBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        private BookDtoBuilder withAuthor(AuthorDto author) {
            this.author = author;
            return this;
        }

        private BookDto build() {
            return new BookDto(id, title, author);
        }

    }

}
