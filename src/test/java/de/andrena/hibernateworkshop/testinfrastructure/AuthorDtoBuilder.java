package de.andrena.hibernateworkshop.testinfrastructure;

import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.service.author.AuthorDto;
import de.andrena.hibernateworkshop.service.book.BookDto;

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
                .map(book -> BookDtoBuilder.fromBookMinimal(book).build())
                .toList();
        return new AuthorDtoBuilder(author.getId(), author.getName(), author.getAddress(), bookDtos);
    }

    public AuthorDto build() {
        return new AuthorDto(id, name, address, books);
    }

}