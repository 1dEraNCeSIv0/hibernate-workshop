package de.andrena.hibernateworkshop.service.customer;

import de.andrena.hibernateworkshop.persistence.customer.Customer;
import de.andrena.hibernateworkshop.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static de.andrena.hibernateworkshop.test.customer.CustomerBuilder.randomCustomer;
import static de.andrena.hibernateworkshop.test.customer.CustomerDtoBuilder.customerDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerServiceTest extends IntegrationTest {

    @Autowired
    private CustomerService classUnderTest;

    @Test
    void getCustomerById_OneBookAndOneAuthor() {
        var customer = defaultCustomer();
        saveCustomers(customer);

        var customerDto = classUnderTest.getCustomerById(customer.getId());

        assertThat(customerDto).isEqualTo(customerDtoFrom(customer).build());
    }

    @Test
    void getCustomerByName_OneBookAndOneAuthor() {
        var customer = defaultCustomer();
        saveCustomers(customer);

        var customerDto = classUnderTest.getCustomerByName(customer.getName());

        assertThat(customerDto).isEqualTo(customerDtoFrom(customer).build());
    }

    @Test
    void getCustomers_OneCustomer_OneBookAndOneAuthor() {
        var customer = defaultCustomer();
        saveCustomers(customer);

        var customerDtos = classUnderTest.getCustomers();

        assertThat(customerDtos).containsExactly(customerDtoFrom(customer).build());
    }

    @Test
    void getCustomersUsingEntityGraph_OverNineThousandCustomers_OneBookAndOneAuthorEach() {
        // TODO Ruben Gehring 15.02.2023: 9001
        var customers = IntStream.range(0, 201)
                .mapToObj(i -> defaultCustomer())
                .toList();
        saveCustomers(customers);

        var customerDtos = classUnderTest.getCustomersUsingEntityGraph();

        assertThat(customerDtos).containsExactlyElementsOf(customers.stream().map(customer -> customerDtoFrom(customer).build()).toList());
    }

    private void saveCustomers(Customer... customers) {
        saveCustomers(Stream.of(customers).toList());
    }

    private void saveCustomers(List<Customer> customers) {
        var authors = customers.stream()
                .flatMap(customer -> customer.getFavoriteAuthors().stream())
                .toList();
        var books = customers.stream()
                .flatMap(customer -> customer.getCheckedOutBooks().stream())
                .toList();
        authorRepository.saveAll(authors);
        bookRepository.saveAll(books);
        customerRepository.saveAll(customers);
    }

    private Customer defaultCustomer() {
        return randomCustomer()
                .withRandomCheckedOutBook()
                .withRandomFavoriteAuthor()
                .build();
    }

}