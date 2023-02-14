package de.andrena.hibernateworkshop.test.customer;

import de.andrena.hibernateworkshop.persistence.customer.Customer;

import java.util.ArrayList;

import static de.andrena.hibernateworkshop.test.StringUtil.withRandomSuffix;
import static de.andrena.hibernateworkshop.test.author.AuthorBuilder.randomAuthor;
import static de.andrena.hibernateworkshop.test.book.BookBuilder.randomBook;
import static java.util.UUID.randomUUID;

public final class CustomerBuilder {

    private Customer customer;

    private CustomerBuilder(Customer customer) {
        this.customer = customer;
    }

    public static CustomerBuilder randomCustomer() {
        var customer = new Customer(randomUUID(), withRandomSuffix("name"), withRandomSuffix("address"), new ArrayList<>(), new ArrayList<>());
        return new CustomerBuilder(customer);
    }

    public CustomerBuilder withRandomCheckedOutBook() {
        var book = randomBook().build();
        customer.getCheckedOutBooks().add(book);
        return this;
    }

    public CustomerBuilder withRandomFavoriteAuthor() {
        var author = randomAuthor().build();
        customer.getFavoriteAuthors().add(author);
        return this;
    }

    public Customer build() {
        return customer;
    }

}
