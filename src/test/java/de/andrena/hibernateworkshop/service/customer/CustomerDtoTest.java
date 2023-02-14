package de.andrena.hibernateworkshop.service.customer;

import org.junit.jupiter.api.Test;

import static de.andrena.hibernateworkshop.test.customer.CustomerBuilder.randomCustomer;
import static de.andrena.hibernateworkshop.test.customer.CustomerDtoBuilder.customerDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerDtoTest {

    @Test
    void toDto() {
        var customer = randomCustomer()
                .withRandomCheckedOutBook()
                .withRandomFavoriteAuthor()
                .build();

        var customerDto = CustomerDto.toDto(customer);

        var expectedCustomerDto = customerDtoFrom(customer).build();
        assertThat(customerDto).isEqualTo(expectedCustomerDto);
    }

}