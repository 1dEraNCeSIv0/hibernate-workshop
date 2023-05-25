package de.andrena.hibernateworkshop.test.address;

import de.andrena.hibernateworkshop.persistence.address.Address;

import java.util.UUID;

import static de.andrena.hibernateworkshop.test.RandomUtil.randomInt;
import static de.andrena.hibernateworkshop.test.RandomUtil.withRandomSuffix;
import static java.util.UUID.randomUUID;

public final class AddressCreator {

    public static Address randomAddress() {
        return new AddressBuilder()
                .withId(randomUUID())
                .withLocation(withRandomSuffix("location"))
                .withStreetName(withRandomSuffix("streetName"))
                .withStreetNumber(randomInt())
                .withZipCode(randomInt())
                .build();
    }

    public static AddressBuilder addressBuilder() {
        return new AddressBuilder();
    }

    public static class AddressBuilder {

        private UUID id;
        private String location;
        private String streetName;
        private int streetNumber;
        private int zipCode;

        private AddressBuilder() {}

        public AddressBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public AddressBuilder withLocation(String location) {
            this.location = location;
            return this;
        }

        public AddressBuilder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public AddressBuilder withStreetNumber(int streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public AddressBuilder withZipCode(int zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Address build() {
            return new Address(id, location, streetName, streetNumber, zipCode);
        }

    }

}