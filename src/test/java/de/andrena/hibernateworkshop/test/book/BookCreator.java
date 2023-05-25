package de.andrena.hibernateworkshop.test.book;

import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.persistence.book.Book;

import java.util.UUID;

import static de.andrena.hibernateworkshop.test.RandomUtil.withRandomSuffix;
import static de.andrena.hibernateworkshop.test.author.AuthorCreator.randomAuthor;
import static java.util.UUID.randomUUID;

public final class BookCreator {

    public static Book randomBook() {
        return randomBookBuilder().build();
    }

    public static BookBuilder randomBookBuilder() {
        return new BookBuilder()
                .withId(randomUUID())
                .withTitle(withRandomSuffix("title"));
    }

    public static class BookBuilder {

        private Book book;

        private BookBuilder() {
            this.book = new Book();
        }

        public BookBuilder withId(UUID id) {
            book.setId(id);
            return this;
        }

        public BookBuilder withTitle(String title) {
            book.setTitle(title);
            return this;
        }

        public BookBuilder withAuthor(Author author) {
            book.setAuthor(author);
            return this;
        }

        public BookBuilder withRandomAuthor() {
            book.setAuthor(randomAuthor());
            return this;
        }

        public Book build() {
            return book;
        }

    }

}