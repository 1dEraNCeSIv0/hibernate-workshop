package de.andrena.hibernateworkshop.test.customer;

import de.andrena.hibernateworkshop.persistence.customer.Customer;
import de.andrena.hibernateworkshop.service.address.AddressDto;
import de.andrena.hibernateworkshop.service.author.AuthorDto;
import de.andrena.hibernateworkshop.service.book.BookDto;
import de.andrena.hibernateworkshop.service.customer.CustomerDto;

import java.util.List;
import java.util.UUID;

import static de.andrena.hibernateworkshop.test.address.AddressDtoBuilder.addressDtoFrom;
import static de.andrena.hibernateworkshop.test.author.AuthorDtoBuilder.minimalAuthorDtoFrom;
import static de.andrena.hibernateworkshop.test.book.BookDtoBuilder.minimalBookDtoFrom;

public final class CustomerDtoBuilder {

    // TODO Ruben Gehring 10.02.2023: Change (dto) builders to creators, expose fac and (where required) builder methods.
    // TODO Ruben Gehring 15.02.2023: Have a builder internally, use it for the fac methods
    // TODO Ruben Gehring 15.02.2023: Naming pattern will be: randomCustomerBuilder().withX(...).build() vs randomCustomer()

    private UUID id;
    private String name;
    private AddressDto address;
    private List<BookDto> checkedOutBooks;
    private List<AuthorDto> favoriteAuthors;

    private CustomerDtoBuilder(UUID id, String name, AddressDto address, List<BookDto> checkedOutBooks, List<AuthorDto> favoriteAuthors) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.checkedOutBooks = checkedOutBooks;
        this.favoriteAuthors = favoriteAuthors;
    }

    public static CustomerDtoBuilder customerDtoFrom(Customer customer) {
        var address = addressDtoFrom(customer.getAddress()).build();
        var checkedOutBooks = customer.getCheckedOutBooks().stream()
                .map(book -> minimalBookDtoFrom(book).build())
                .toList();
        var favoriteAuthors = customer.getFavoriteAuthors().stream()
                .map(author -> minimalAuthorDtoFrom(author).build())
                .toList();
        return new CustomerDtoBuilder(customer.getId(), customer.getName(), address, checkedOutBooks, favoriteAuthors);
    }

    public CustomerDto build() {
        return new CustomerDto(id, name, address, checkedOutBooks, favoriteAuthors);
    }

}
