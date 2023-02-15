package de.andrena.hibernateworkshop.service.author;

import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.service.address.AddressDto;
import de.andrena.hibernateworkshop.service.book.BookDto;

import java.util.List;
import java.util.UUID;

public record AuthorDto(UUID id, String name, AddressDto address, List<BookDto> books) {

    public static AuthorDto toFullDto(Author author) {
        AddressDto address = AddressDto.toDto(author.getAddress());
        List<BookDto> bookDtos = author.getBooks().stream().map(BookDto::toMinimalDto).toList();
        return new AuthorDto(author.getId(), author.getName(), address, bookDtos);
    }

    public static AuthorDto toMinimalDto(Author author) {
        AddressDto address = AddressDto.toDto(author.getAddress());
        return new AuthorDto(author.getId(), author.getName(), address, List.of());
    }

}
