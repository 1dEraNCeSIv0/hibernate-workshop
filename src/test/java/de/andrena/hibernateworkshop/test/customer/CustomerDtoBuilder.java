package de.andrena.hibernateworkshop.test.customer;

import de.andrena.hibernateworkshop.persistence.customer.Customer;
import de.andrena.hibernateworkshop.service.author.AuthorDto;
import de.andrena.hibernateworkshop.service.book.BookDto;
import de.andrena.hibernateworkshop.service.customer.CustomerDto;

import java.util.List;
import java.util.UUID;

import static de.andrena.hibernateworkshop.test.author.AuthorDtoBuilder.minimalAuthorDtoFrom;
import static de.andrena.hibernateworkshop.test.book.BookDtoBuilder.minimalBookDtoFrom;

public final class CustomerDtoBuilder {

    // TODO Ruben Gehring 10.02.2023: These things need not be builders so far, or do they?

    private UUID id;
    private String name;
    private String address;
    private List<BookDto> checkedOutBooks;
    private List<AuthorDto> favoriteAuthors;

    public CustomerDtoBuilder(UUID id, String name, String address, List<BookDto> checkedOutBooks, List<AuthorDto> favoriteAuthors) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.checkedOutBooks = checkedOutBooks;
        this.favoriteAuthors = favoriteAuthors;
    }

    public static CustomerDtoBuilder customerDtoFrom(Customer customer) {
        var checkedOutBooks = customer.getCheckedOutBooks().stream()
                .map(book -> minimalBookDtoFrom(book).build())
                .toList();
        var favoriteAuthors = customer.getFavoriteAuthors().stream()
                .map(author -> minimalAuthorDtoFrom(author).build())
                .toList();
        return new CustomerDtoBuilder(customer.getId(), customer.getName(), customer.getAddress(), checkedOutBooks, favoriteAuthors);
    }

    public CustomerDto build() {
        return new CustomerDto(id, name, address, checkedOutBooks, favoriteAuthors);
    }

}
