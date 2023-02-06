package de.andrena.hibernateworkshop.service.dto;

import de.andrena.hibernateworkshop.persistence.entity.Book;

import java.util.UUID;

public record BookDto(UUID id, String title, AuthorDto author) {

    public static BookDto toFullDto(Book book) {
        AuthorDto author = AuthorDto.toMinimalDto(book.getAuthor());
        return new BookDto(book.getId(), book.getTitle(), author);
    }

    public static BookDto toMinimalDto(Book book) {
        AuthorDto noAuthor = null;
        return new BookDto(book.getId(), book.getTitle(), noAuthor);
    }

}
