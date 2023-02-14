package de.andrena.hibernateworkshop.test.author;

import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.persistence.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static de.andrena.hibernateworkshop.test.StringUtil.withRandomSuffix;
import static de.andrena.hibernateworkshop.test.book.BookBuilder.randomBook;
import static java.util.UUID.randomUUID;

public final class AuthorBuilder {

    private Author author;

    private AuthorBuilder(Author author) {
        this.author = author;
    }

    public static AuthorBuilder randomAuthor() {
        var author = new Author(randomUUID(), withRandomSuffix("name"), withRandomSuffix("address"), new ArrayList<>());
        return new AuthorBuilder(author);
    }

    public AuthorBuilder withName(String name) {
        author.setName(name);
        return this;
    }

    public AuthorBuilder withAddress(String address) {
        author.setAddress(address);
        return this;
    }

    public AuthorBuilder withBooks(List<Book> books) {
        author.setBooks(books);
        return this;
    }

    public AuthorBuilder withBook(Book book) {
        author.getBooks().add(book);
        return this;
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