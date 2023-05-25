package de.andrena.hibernateworkshop.service.customer;

import de.andrena.hibernateworkshop.persistence.customer.Customer;
import de.andrena.hibernateworkshop.test.IntegrationTest;
import de.andrena.hibernateworkshop.test.customer.CustomerDtoCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static de.andrena.hibernateworkshop.test.customer.CustomerCreator.randomCustomerBuilder;
import static de.andrena.hibernateworkshop.test.customer.CustomerDtoCreator.customerDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerServiceTest extends IntegrationTest {

    @Autowired
    private CustomerService classUnderTest;

    @Test
    void getCustomerById_OneBookAndOneAuthor() {
        var customer = defaultCustomer();
        saveCustomers(customer);

        var customerDto = classUnderTest.getCustomerById(customer.getId());

        assertThat(customerDto).isEqualTo(customerDtoFrom(customer));
    }

    @Test
    void getCustomerByName_OneBookAndOneAuthor() {
        var customer = defaultCustomer();
        saveCustomers(customer);

        var customerDto = classUnderTest.getCustomerByName(customer.getName());

        assertThat(customerDto).isEqualTo(customerDtoFrom(customer));
    }

    @Test
    void getCustomers_OneCustomer_OneBookAndOneAuthor() {
        var customer = defaultCustomer();
        saveCustomers(customer);

        var customerDtos = classUnderTest.getCustomers();

        assertThat(customerDtos).containsExactly(customerDtoFrom(customer));
    }

    @Test
    void getCustomers_OverNineThousandCustomers_OneBookAndOneAuthorEach() {
        var customers = IntStream.range(0, 9001)
                .mapToObj(i -> defaultCustomer())
                .toList();
        saveCustomers(customers);

        var customerDtos = classUnderTest.getCustomers();

        var expectedCustomerDtos = customers.stream().map(CustomerDtoCreator::customerDtoFrom).toList();
        assertThat(customerDtos).containsExactlyElementsOf(expectedCustomerDtos);
    }

    @Test
    void getCustomersUsingEntityGraph_OverNineThousandCustomers_OneBookAndOneAuthorEach() {
        var customers = IntStream.range(0, 9001)
                .mapToObj(i -> defaultCustomer())
                .toList();
        saveCustomers(customers);

        var customerDtos = classUnderTest.getCustomersUsingEntityGraph();

        var expectedCustomerDtos = customers.stream().map(CustomerDtoCreator::customerDtoFrom).toList();
        assertThat(customerDtos).containsExactlyElementsOf(expectedCustomerDtos);
    }

    @Test
    void getCustomersPersonalInfoUsingProjection_OverNineThousandCustomers_OneBookAndOneAuthorEach() {
        var customers = IntStream.range(0, 9001)
                .mapToObj(i -> defaultCustomer())
                .toList();
        saveCustomers(customers);

        var customerDtos = classUnderTest.getCustomersPersonalInfoUsingProjection();

        var expectedCustomerDtos = customers.stream().map(CustomerDtoCreator::customerDtoFrom).toList();
        assertThat(customerDtos).containsExactlyInAnyOrderElementsOf(expectedCustomerDtos);
    }

    @Test
    void getCustomersPaginated_OverNineThousandCustomers_OneBookAndOneAuthorEach_GetFirstPage() {
        var customers = IntStream.range(0, 9001)
                .mapToObj(i -> defaultCustomer())
                .toList();
        saveCustomers(customers);

        var pageSize = 50;
        var customerDtos = classUnderTest.getCustomersPaginated(Pageable.ofSize(pageSize));

        var expectedCustomerDtos = customers.stream()
                .limit(pageSize)
                .map(CustomerDtoCreator::customerDtoFrom)
                .toList();
        assertThat(customerDtos).containsExactlyElementsOf(expectedCustomerDtos);
    }

    private Customer defaultCustomer() {
        return randomCustomerBuilder()
                .withRandomCheckedOutBook()
                .withRandomFavoriteAuthor()
                .build();
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

}