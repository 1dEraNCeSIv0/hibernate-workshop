package de.andrena.hibernateworkshop.test.book;

import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.persistence.book.Book;

import static de.andrena.hibernateworkshop.test.RandomUtil.withRandomSuffix;
import static de.andrena.hibernateworkshop.test.author.AuthorBuilder.randomAuthor;
import static java.util.UUID.randomUUID;

public final class BookBuilder {

    private static final Author NO_AUTHOR = null;

    private Book book;

    private BookBuilder(Book book) {
        this.book = book;
    }

    public static BookBuilder randomBook() {
        var initialBook = new Book(randomUUID(), withRandomSuffix("title"), NO_AUTHOR);
        return new BookBuilder(initialBook);
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
        book.setAuthor(randomAuthor().build());
        return this;
    }

    public Book build() {
        return book;
    }

}