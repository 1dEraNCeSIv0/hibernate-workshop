package de.andrena.hibernateworkshop.test.author;

import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.service.author.AuthorDto;
import de.andrena.hibernateworkshop.service.book.BookDto;
import de.andrena.hibernateworkshop.test.book.BookDtoBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AuthorDtoBuilder {

    private UUID id;
    private String name;
    private String address;
    private List<BookDto> books;

    private AuthorDtoBuilder(UUID id, String name, String address, List<BookDto> books) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.books = books;
    }

    public static AuthorDtoBuilder authorDtoFrom(Author author) {
        var bookDtos = author.getBooks().stream()
                .map(book -> BookDtoBuilder.minimalBookDtoFrom(book).build())
                .toList();
        return minimalAuthorDtoFrom(author).withBooks(bookDtos);
    }

    public static AuthorDtoBuilder minimalAuthorDtoFrom(Author author) {
        return new AuthorDtoBuilder(author.getId(), author.getName(), author.getAddress(), new ArrayList<>());
    }

    public AuthorDtoBuilder withBooks(List<BookDto> books) {
        this.books = books;
        return this;
    }

    public AuthorDto build() {
        return new AuthorDto(id, name, address, books);
    }

}