package de.andrena.hibernateworkshop.persistence.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

	@Query("""
		SELECT c
		FROM Customer c
		JOIN c.address
		JOIN c.checkedOutBooks
		JOIN c.favoriteAuthors
		WHERE c.name = :name
		""")
	Optional<Customer> findByName(String name);

	@Query("SELECT c FROM Customer c")
		//    @EntityGraph(attributePaths = ...)
	List<Customer> findAllUsingEntityGraph();

	<T> List<T> findAllProjectionsBy(Class<T> projectionClass);

}
