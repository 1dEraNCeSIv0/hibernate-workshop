package de.andrena.hibernateworkshop.persistence.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @Query("""
            SELECT a
            FROM Author a
            JOIN a.books
            """)
    List<Author> findAllUsingQueryAnnotation();

}
