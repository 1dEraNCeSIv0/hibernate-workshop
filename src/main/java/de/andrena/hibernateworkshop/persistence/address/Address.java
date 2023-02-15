package de.andrena.hibernateworkshop.persistence.address;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Address {

    @Id
    private UUID id;

    private String location;
    private String streetName;
    private int streetNumber;
    private int zipCode;

    public Address() {}

    public Address(UUID id, String location, String streetName, int streetNumber, int zipCode) {
        this.id = id;
        this.location = location;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }

    public UUID getId() {
        return id;
    }

    public Address setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Address setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getStreetName() {
        return streetName;
    }

    public Address setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public Address setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public int getZipCode() {
        return zipCode;
    }

    public Address setZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

}
