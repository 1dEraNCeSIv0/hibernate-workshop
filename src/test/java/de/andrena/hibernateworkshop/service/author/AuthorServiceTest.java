package de.andrena.hibernateworkshop.service.author;

import de.andrena.hibernateworkshop.testinfrastructure.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.IntStream;

import static de.andrena.hibernateworkshop.testinfrastructure.AuthorBuilder.randomAuthor;
import static de.andrena.hibernateworkshop.testinfrastructure.AuthorDtoBuilder.authorDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class AuthorServiceTest extends IntegrationTest {

    @Autowired
    private AuthorService classUnderTest;

    // TODO: 06.02.2023 Go over all tests, ensure consistent style
    @Test
    void getAuthor_OneBook() {
        var author = randomAuthor().withRandomBook().build();
        authorRepository.save(author);

        var authorDto = classUnderTest.getAuthor(author.getId());

        var expectedAuthorDto = authorDtoFrom(author).build();
        assertThat(authorDto).isEqualTo(expectedAuthorDto);
    }

    @Test
    void getAuthor_TwoBooks() {
        var author = randomAuthor()
                .withRandomBook()
                .withRandomBook()
                .build();
        authorRepository.save(author);

        var authorDto = classUnderTest.getAuthor(author.getId());

        var expectedAuthorDto = authorDtoFrom(author).build();
        assertThat(authorDto).isEqualTo(expectedAuthorDto);
    }

    @Test
    void getAuthor_OverTwoHundredBooks() {
        var authorBuilder = randomAuthor();
        IntStream.range(0, 201).forEach(i -> authorBuilder.withRandomBook());
        var author = authorBuilder.build();

        authorRepository.save(author);

        var authorDto = classUnderTest.getAuthor(author.getId());

        var expectedAuthorDto = authorDtoFrom(author).build();
        assertThat(authorDto).isEqualTo(expectedAuthorDto);
    }

}