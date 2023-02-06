package de.andrena.hibernateworkshop.persistence.repository;

import de.andrena.hibernateworkshop.persistence.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {}
