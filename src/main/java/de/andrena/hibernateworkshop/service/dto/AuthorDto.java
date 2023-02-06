package de.andrena.hibernateworkshop.service.dto;

import de.andrena.hibernateworkshop.persistence.entity.Author;

import java.util.List;
import java.util.UUID;

public record AuthorDto(UUID id, String name, String address, List<BookDto> books) {

    public static AuthorDto toFullDto(Author author) {
        List<BookDto> bookDtos = author.getBooks().stream().map(BookDto::toMinimalDto).toList();
        return new AuthorDto(author.getId(), author.getName(), author.getAddress(), bookDtos);
    }

    public static AuthorDto toMinimalDto(Author author) {
        return new AuthorDto(author.getId(), author.getName(), author.getAddress(), List.of());
    }

}
