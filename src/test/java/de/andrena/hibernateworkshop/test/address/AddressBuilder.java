package de.andrena.hibernateworkshop.test.address;

import de.andrena.hibernateworkshop.persistence.address.Address;

import static de.andrena.hibernateworkshop.test.RandomUtil.randomInt;
import static de.andrena.hibernateworkshop.test.RandomUtil.withRandomSuffix;
import static java.util.UUID.randomUUID;

public final class AddressBuilder {

    private Address address;

    private AddressBuilder(Address address) {
        this.address = address;
    }

    public static AddressBuilder randomAddress() {
        var initialAddress = new Address(randomUUID(), withRandomSuffix("location"), withRandomSuffix("streetName"), randomInt(), randomInt());
        return new AddressBuilder(initialAddress);
    }

    public Address build() {
        return address;
    }

}