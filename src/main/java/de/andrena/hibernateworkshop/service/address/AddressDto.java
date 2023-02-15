package de.andrena.hibernateworkshop.service.address;

import de.andrena.hibernateworkshop.persistence.address.Address;

import java.util.UUID;

public record AddressDto(UUID id, String location, String streetName, int streetNumber, int zipCode) {

    public static AddressDto toDto(Address address) {
        return new AddressDto(address.getId(), address.getLocation(), address.getStreetName(), address.getStreetNumber(), address.getZipCode());
    }

}
