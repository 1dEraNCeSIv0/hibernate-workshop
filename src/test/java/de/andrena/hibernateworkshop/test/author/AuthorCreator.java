package de.andrena.hibernateworkshop.test.author;

import de.andrena.hibernateworkshop.persistence.address.Address;
import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.persistence.book.Book;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static de.andrena.hibernateworkshop.test.RandomUtil.withRandomSuffix;
import static de.andrena.hibernateworkshop.test.address.AddressCreator.randomAddress;
import static de.andrena.hibernateworkshop.test.book.BookCreator.randomBookBuilder;
import static java.util.UUID.randomUUID;

public final class AuthorCreator {

    public static Author randomAuthor() {
        return randomAuthorBuilder().build();
    }

    public static AuthorBuilder randomAuthorBuilder() {
        return new AuthorBuilder()
                .withId(randomUUID())
                .withName(withRandomSuffix("name"))
                .withAddress(randomAddress());
    }

    public static class AuthorBuilder {

        private Author author;

        private AuthorBuilder() {
            this.author = new Author();
        }

        public AuthorBuilder withId(UUID id) {
            author.setId(id);
            return this;
        }

        public AuthorBuilder withName(String name) {
            author.setName(name);
            return this;
        }

        public AuthorBuilder withAddress(Address address) {
            author.setAddress(address);
            return this;
        }

        public AuthorBuilder withBooks(List<Book> books) {
            author.setBooks(books);
            return this;
        }

        public AuthorBuilder withBooks(Book... books) {
            return withBooks(List.of(books));
        }

        public AuthorBuilder withRandomBook() {
            var book = randomBookBuilder().withAuthor(author).build();
            return withBooks(book);
        }

        public AuthorBuilder withRandomBooks(int number) {
            var books = IntStream.range(0, number)
                    .mapToObj(i -> randomBookBuilder().withAuthor(author).build())
                    .toList();
            return withBooks(books);
        }

        public Author build() {
            return author;
        }

    }

}