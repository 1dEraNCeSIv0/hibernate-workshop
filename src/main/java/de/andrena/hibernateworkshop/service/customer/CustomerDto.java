package de.andrena.hibernateworkshop.service.customer;

import de.andrena.hibernateworkshop.persistence.customer.Customer;
import de.andrena.hibernateworkshop.service.address.AddressDto;
import de.andrena.hibernateworkshop.service.author.AuthorDto;
import de.andrena.hibernateworkshop.service.book.BookDto;

import java.util.List;
import java.util.UUID;

public record CustomerDto(UUID id, String name, AddressDto address, List<BookDto> checkedOutBooks, List<AuthorDto> favoriteAuthors) {

    public static CustomerDto toDto(Customer customer) {
        AddressDto address = AddressDto.toDto(customer.getAddress());
        List<BookDto> checkedOutBooks = customer.getCheckedOutBooks().stream().map(BookDto::toMinimalDto).toList();
        List<AuthorDto> favoriteAuthors = customer.getFavoriteAuthors().stream().map(AuthorDto::toMinimalDto).toList();
        return new CustomerDto(customer.getId(), customer.getName(), address, checkedOutBooks, favoriteAuthors);
    }

}
