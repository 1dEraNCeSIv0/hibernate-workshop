package de.andrena.hibernateworkshop.test;

import de.andrena.hibernateworkshop.persistence.author.AuthorRepository;
import de.andrena.hibernateworkshop.persistence.book.BookRepository;
import de.andrena.hibernateworkshop.persistence.customer.CustomerRepository;
import de.andrena.hibernateworkshop.test.infrastructure.TestProfilingExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(TestProfilingExtension.class)
public class IntegrationTest {

    @Autowired
    protected AuthorRepository authorRepository;
    @Autowired
    protected BookRepository bookRepository;
    @Autowired
    protected CustomerRepository customerRepository;

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
        authorRepository.deleteAll();
        bookRepository.deleteAll();
    }

}
