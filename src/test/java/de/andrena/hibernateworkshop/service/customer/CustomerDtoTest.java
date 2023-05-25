package de.andrena.hibernateworkshop.service.customer;

import org.junit.jupiter.api.Test;

import static de.andrena.hibernateworkshop.test.customer.CustomerCreator.randomCustomerBuilder;
import static de.andrena.hibernateworkshop.test.customer.CustomerDtoCreator.customerDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerDtoTest {

    @Test
    void toDto() {
        var customer = randomCustomerBuilder()
                .withRandomCheckedOutBook()
                .withRandomFavoriteAuthor()
                .build();

        var customerDto = CustomerDto.toDto(customer);

        assertThat(customerDto).isEqualTo(customerDtoFrom(customer));
    }

}