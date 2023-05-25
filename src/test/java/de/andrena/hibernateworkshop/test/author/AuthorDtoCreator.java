package de.andrena.hibernateworkshop.test.author;

import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.service.address.AddressDto;
import de.andrena.hibernateworkshop.service.author.AuthorDto;
import de.andrena.hibernateworkshop.service.book.BookDto;
import de.andrena.hibernateworkshop.test.book.BookDtoCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static de.andrena.hibernateworkshop.test.address.AddressDtoCreator.addressDtoFrom;

public final class AuthorDtoCreator {

    public static AuthorDto authorDtoFrom(Author author) {
        var bookDtos = author.getBooks().stream()
                .map(BookDtoCreator::minimalBookDtoFrom)
                .toList();
        return new AuthorDtoBuilder()
                .withId(author.getId())
                .withName(author.getName())
                .withAddress(addressDtoFrom(author.getAddress()))
                .withBooks(bookDtos)
                .build();
    }

    public static AuthorDto minimalAuthorDtoFrom(Author author) {
        return new AuthorDtoBuilder()
                .withId(author.getId())
                .withName(author.getName())
                .withAddress(addressDtoFrom(author.getAddress()))
                .build();
    }

    private static class AuthorDtoBuilder {

        private UUID id;
        private String name;
        private AddressDto address;
        private List<BookDto> books = new ArrayList<>();

        private AuthorDtoBuilder() {}

        private AuthorDtoBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        private AuthorDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        private AuthorDtoBuilder withAddress(AddressDto address) {
            this.address = address;
            return this;
        }

        private AuthorDtoBuilder withBooks(List<BookDto> books) {
            this.books = books;
            return this;
        }

        private AuthorDto build() {
            return new AuthorDto(id, name, address, books);
        }

    }

}
