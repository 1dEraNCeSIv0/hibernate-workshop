package de.andrena.hibernateworkshop.test.customer;

import de.andrena.hibernateworkshop.persistence.customer.Customer;
import de.andrena.hibernateworkshop.service.address.AddressDto;
import de.andrena.hibernateworkshop.service.author.AuthorDto;
import de.andrena.hibernateworkshop.service.book.BookDto;
import de.andrena.hibernateworkshop.service.customer.CustomerDto;
import de.andrena.hibernateworkshop.test.author.AuthorDtoCreator;
import de.andrena.hibernateworkshop.test.book.BookDtoCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static de.andrena.hibernateworkshop.test.address.AddressDtoCreator.addressDtoFrom;

public final class CustomerDtoCreator {

    public static CustomerDto customerDtoFrom(Customer customer) {
        var checkedOutBooks = customer.getCheckedOutBooks().stream()
                .map(BookDtoCreator::minimalBookDtoFrom)
                .toList();
        var favoriteAuthors = customer.getFavoriteAuthors().stream()
                .map(AuthorDtoCreator::minimalAuthorDtoFrom)
                .toList();
        return new CustomerDtoBuilder()
                .withId(customer.getId())
                .withName(customer.getName())
                .withAddress(addressDtoFrom(customer.getAddress()))
                .withCheckedOutBooks(checkedOutBooks)
                .withFavoriteAuthors(favoriteAuthors)
                .build();
    }

    private static class CustomerDtoBuilder {

        private UUID id;
        private String name;
        private AddressDto address;
        private List<BookDto> checkedOutBooks = new ArrayList<>();
        private List<AuthorDto> favoriteAuthors = new ArrayList<>();

        private CustomerDtoBuilder() {}

        private CustomerDtoBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        private CustomerDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        private CustomerDtoBuilder withAddress(AddressDto address) {
            this.address = address;
            return this;
        }

        private CustomerDtoBuilder withCheckedOutBooks(List<BookDto> checkedOutBooks) {
            this.checkedOutBooks = checkedOutBooks;
            return this;
        }

        private CustomerDtoBuilder withFavoriteAuthors(List<AuthorDto> favoriteAuthors) {
            this.favoriteAuthors = favoriteAuthors;
            return this;
        }

        private CustomerDto build() {
            return new CustomerDto(id, name, address, checkedOutBooks, favoriteAuthors);
        }

    }

}
