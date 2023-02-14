package de.andrena.hibernateworkshop.service.customer;

import de.andrena.hibernateworkshop.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static de.andrena.hibernateworkshop.test.customer.CustomerBuilder.randomCustomer;
import static de.andrena.hibernateworkshop.test.customer.CustomerDtoBuilder.customerDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerServiceTest extends IntegrationTest {

    @Autowired
    private CustomerService classUnderTest;

    @Test
    void getCustomerById_OneBookAndOneAuthor() {
        var customer = randomCustomer()
                .withRandomCheckedOutBook()
                .withRandomFavoriteAuthor()
                .build();
        authorRepository.saveAll(customer.getFavoriteAuthors());
        bookRepository.saveAll(customer.getCheckedOutBooks());
        customerRepository.save(customer);

        var customers = classUnderTest.getCustomerById(customer.getId());

        assertThat(customers).isEqualTo(customerDtoFrom(customer).build());
    }

    @Test
    void getCustomerByName_OneBookAndOneAuthor() {
        var customer = randomCustomer()
                .withRandomCheckedOutBook()
                .withRandomFavoriteAuthor()
                .build();
        authorRepository.saveAll(customer.getFavoriteAuthors());
        bookRepository.saveAll(customer.getCheckedOutBooks());
        customerRepository.save(customer);

        var customers = classUnderTest.getCustomerByName(customer.getName());

        assertThat(customers).isEqualTo(customerDtoFrom(customer).build());
    }

    @Test
    void getCustomers_OneCustomerWithOneBookAndOneAuthor() {
        var customer = randomCustomer()
                .withRandomCheckedOutBook()
                .withRandomFavoriteAuthor()
                .build();
        authorRepository.saveAll(customer.getFavoriteAuthors());
        bookRepository.saveAll(customer.getCheckedOutBooks());
        customerRepository.save(customer);

        var customers = classUnderTest.getCustomers();

        assertThat(customers).containsExactly(customerDtoFrom(customer).build());
    }

}