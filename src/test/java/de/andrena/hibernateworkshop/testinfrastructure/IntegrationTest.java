package de.andrena.hibernateworkshop.testinfrastructure;

import de.andrena.hibernateworkshop.persistence.author.AuthorRepository;
import de.andrena.hibernateworkshop.persistence.book.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest {

    @Autowired
    protected BookRepository bookRepository;
    @Autowired
    protected AuthorRepository authorRepository;

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

}
