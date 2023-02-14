package de.andrena.hibernateworkshop.persistence.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    // TODO Ruben Gehring 10.02.2023: Lever x
    @Query("""
            SELECT c
            FROM Customer c
            JOIN c.checkedOutBooks
            JOIN c.favoriteAuthors
            WHERE c.name = :name
            """)
    Optional<Customer> findByName(String name);

}
