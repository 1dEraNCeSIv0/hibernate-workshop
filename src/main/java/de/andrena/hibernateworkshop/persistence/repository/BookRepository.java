package de.andrena.hibernateworkshop.persistence.repository;

import de.andrena.hibernateworkshop.persistence.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
