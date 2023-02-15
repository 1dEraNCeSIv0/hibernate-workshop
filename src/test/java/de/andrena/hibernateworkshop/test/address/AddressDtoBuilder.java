package de.andrena.hibernateworkshop.test.address;

import de.andrena.hibernateworkshop.persistence.address.Address;
import de.andrena.hibernateworkshop.service.address.AddressDto;

import java.util.UUID;

public final class AddressDtoBuilder {

    private UUID id;
    private String location;
    private String streetName;
    private int streetNumber;
    private int zipCode;

    private AddressDtoBuilder(UUID id, String location, String streetName, int streetNumber, int zipCode) {
        this.id = id;
        this.location = location;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }

    public static AddressDtoBuilder addressDtoFrom(Address address) {
        return new AddressDtoBuilder(address.getId(), address.getLocation(), address.getStreetName(), address.getStreetNumber(), address.getZipCode());
    }

    public AddressDto build() {
        return new AddressDto(id, location, streetName, streetNumber, zipCode);
    }

}