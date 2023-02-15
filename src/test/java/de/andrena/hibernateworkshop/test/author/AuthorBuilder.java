package de.andrena.hibernateworkshop.test.author;

import de.andrena.hibernateworkshop.persistence.author.Author;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static de.andrena.hibernateworkshop.test.RandomUtil.withRandomSuffix;
import static de.andrena.hibernateworkshop.test.address.AddressBuilder.randomAddress;
import static de.andrena.hibernateworkshop.test.book.BookBuilder.randomBook;
import static java.util.UUID.randomUUID;

public final class AuthorBuilder {

    private Author author;

    private AuthorBuilder(Author author) {
        this.author = author;
    }

    public static AuthorBuilder randomAuthor() {
        var initialAuthor = new Author(randomUUID(), withRandomSuffix("name"), randomAddress().build(), new ArrayList<>());
        return new AuthorBuilder(initialAuthor);
    }

    public AuthorBuilder withRandomBook() {
        var book = randomBook().withAuthor(author).build();
        author.getBooks().add(book);
        return this;
    }

    public AuthorBuilder withRandomBooks(int number) {
        var books = IntStream.range(0, number)
                .mapToObj(i -> randomBook().withAuthor(author).build())
                .toList();
        author.getBooks().addAll(books);
        return this;
    }

    public Author build() {
        return author;
    }

}