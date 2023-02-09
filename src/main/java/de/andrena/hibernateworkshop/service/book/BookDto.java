package de.andrena.hibernateworkshop.service.book;

import de.andrena.hibernateworkshop.exception.MandatoryFieldException;
import de.andrena.hibernateworkshop.persistence.book.Book;
import de.andrena.hibernateworkshop.service.author.AuthorDto;

import java.util.UUID;

public record BookDto(UUID id, String title, AuthorDto author) {

    public static BookDto toFullDto(Book book) {
        AuthorDto author = book.getAuthor()
                .map(AuthorDto::toMinimalDto)
                .orElseThrow(() -> new MandatoryFieldException("author"));
        return new BookDto(book.getId(), book.getTitle(), author);
    }

    public static BookDto toMinimalDto(Book book) {
        AuthorDto noAuthor = null;
        return new BookDto(book.getId(), book.getTitle(), noAuthor);
    }

}
