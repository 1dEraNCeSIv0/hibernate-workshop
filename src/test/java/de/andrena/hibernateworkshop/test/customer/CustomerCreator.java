package de.andrena.hibernateworkshop.test.customer;

import de.andrena.hibernateworkshop.persistence.address.Address;
import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.persistence.book.Book;
import de.andrena.hibernateworkshop.persistence.customer.Customer;

import java.util.List;
import java.util.UUID;

import static de.andrena.hibernateworkshop.test.RandomUtil.withRandomSuffix;
import static de.andrena.hibernateworkshop.test.address.AddressCreator.randomAddress;
import static de.andrena.hibernateworkshop.test.author.AuthorCreator.randomAuthor;
import static de.andrena.hibernateworkshop.test.book.BookCreator.randomBook;
import static java.util.UUID.randomUUID;

public final class CustomerCreator {

    public static CustomerBuilder randomCustomerBuilder() {
        return new CustomerBuilder()
                .withId(randomUUID())
                .withName(withRandomSuffix("name"))
                .withAddress(randomAddress());
    }

    public static class CustomerBuilder {

        private Customer customer;

        private CustomerBuilder() {
            this.customer = new Customer();
        }

        public CustomerBuilder withId(UUID id) {
            customer.setId(id);
            return this;
        }

        public CustomerBuilder withName(String name) {
            customer.setName(name);
            return this;
        }

        public CustomerBuilder withAddress(Address address) {
            customer.setAddress(address);
            return this;
        }

        public CustomerBuilder withCheckedOutBooks(List<Book> books) {
            customer.setCheckedOutBooks(books);
            return this;
        }

        public CustomerBuilder withCheckedOutBooks(Book... books) {
            return withCheckedOutBooks(List.of(books));
        }

        public CustomerBuilder withFavoriteAuthors(List<Author> authors) {
            customer.setFavoriteAuthors(authors);
            return this;
        }

        public CustomerBuilder withFavoriteAuthors(Author... authors) {
            return withFavoriteAuthors(List.of(authors));
        }

        public CustomerBuilder withRandomCheckedOutBook() {
            return withCheckedOutBooks(randomBook());
        }

        public CustomerBuilder withRandomFavoriteAuthor() {
            return withFavoriteAuthors(randomAuthor());
        }

        public Customer build() {
            return customer;
        }

    }

}
