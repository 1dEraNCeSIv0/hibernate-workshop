package de.andrena.hibernateworkshop.testinfrastructure;

import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.persistence.book.Book;

import static de.andrena.hibernateworkshop.testinfrastructure.AuthorBuilder.randomAuthor;
import static de.andrena.hibernateworkshop.testinfrastructure.StringUtil.withRandomSuffix;
import static java.util.UUID.randomUUID;

public final class BookBuilder {

    private static final Author NO_AUTHOR = null;

    private Book book;

    private BookBuilder(Book book) {
        this.book = book;
    }

    public static BookBuilder randomBook() {
        var book = new Book(randomUUID(), withRandomSuffix("title"), NO_AUTHOR);
        return new BookBuilder(book);
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