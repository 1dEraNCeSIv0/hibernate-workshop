package de.andrena.hibernateworkshop.test.address;

import de.andrena.hibernateworkshop.persistence.address.Address;
import de.andrena.hibernateworkshop.service.address.AddressDto;

import java.util.UUID;

public final class AddressDtoCreator {

    public static AddressDto addressDtoFrom(Address address) {
        return new AddressDtoBuilder()
                .withId(address.getId())
                .withLocation(address.getLocation())
                .withStreetName(address.getStreetName())
                .withStreetNumber(address.getStreetNumber())
                .withZipCode(address.getZipCode())
                .build();
    }

    private static class AddressDtoBuilder {

        private UUID id;
        private String location;
        private String streetName;
        private int streetNumber;
        private int zipCode;

        private AddressDtoBuilder() {}

        private AddressDtoBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        private AddressDtoBuilder withLocation(String location) {
            this.location = location;
            return this;
        }

        private AddressDtoBuilder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        private AddressDtoBuilder withStreetNumber(int streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        private AddressDtoBuilder withZipCode(int zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        private AddressDto build() {
            return new AddressDto(id, location, streetName, streetNumber, zipCode);
        }

    }

}