package de.andrena.hibernateworkshop.service.address;

import org.junit.jupiter.api.Test;

import static de.andrena.hibernateworkshop.test.address.AddressCreator.randomAddress;
import static de.andrena.hibernateworkshop.test.address.AddressDtoCreator.addressDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class AddressDtoTest {

    @Test
    void toDto() {
        var address = randomAddress();

        var addressDto = AddressDto.toDto(address);

        assertThat(addressDto).isEqualTo(addressDtoFrom(address));
    }

}