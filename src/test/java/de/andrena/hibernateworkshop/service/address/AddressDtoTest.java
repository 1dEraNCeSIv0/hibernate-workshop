package de.andrena.hibernateworkshop.service.address;

import org.junit.jupiter.api.Test;

import static de.andrena.hibernateworkshop.test.address.AddressBuilder.randomAddress;
import static de.andrena.hibernateworkshop.test.address.AddressDtoBuilder.addressDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class AddressDtoTest {

    @Test
    void toDto() {
        var address = randomAddress().build();

        var addressDto = AddressDto.toDto(address);

        assertThat(addressDto).isEqualTo(addressDtoFrom(address).build());
    }

}