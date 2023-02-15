package de.andrena.hibernateworkshop.service.author;

import de.andrena.hibernateworkshop.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.IntStream;

import static de.andrena.hibernateworkshop.test.author.AuthorBuilder.randomAuthor;
import static de.andrena.hibernateworkshop.test.author.AuthorDtoBuilder.authorDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class AuthorServiceTest extends IntegrationTest {

    @Autowired
    private AuthorService classUnderTest;

    // TODO: 06.02.2023 Go over all tests, ensure consistent style
    @Test
    void getAuthorById_OneBook() {
        var author = randomAuthor().withRandomBook().build();
        authorRepository.save(author);

        var authorDto = classUnderTest.getAuthorById(author.getId());

        var expectedAuthorDto = authorDtoFrom(author).build();
        assertThat(authorDto).isEqualTo(expectedAuthorDto);
    }

    @Test
    void getAuthorById_TwoBooks() {
        var author = randomAuthor()
                .withRandomBook()
                .withRandomBook()
                .build();
        authorRepository.save(author);

        var authorDto = classUnderTest.getAuthorById(author.getId());

        var expectedAuthorDto = authorDtoFrom(author).build();
        assertThat(authorDto).isEqualTo(expectedAuthorDto);
    }

    @Test
    void getAuthorById_OverTwoHundredBooks() {
        var authorBuilder = randomAuthor();
        IntStream.range(0, 201).forEach(i -> authorBuilder.withRandomBook());
        var author = authorBuilder.build();

        authorRepository.save(author);

        var authorDto = classUnderTest.getAuthorById(author.getId());

        var expectedAuthorDto = authorDtoFrom(author).build();
        assertThat(authorDto).isEqualTo(expectedAuthorDto);
    }

    @Test
    void getAuthors_OneAuthorWithOneBook() {
        var author = randomAuthor().withRandomBook().build();
        authorRepository.save(author);

        var authorDto = classUnderTest.getAuthors();

        var expectedAuthorDto = authorDtoFrom(author).build();
        assertThat(authorDto).containsExactly(expectedAuthorDto);
    }

    @Test
    void getAuthors_OneAuthorWithTwoBooks() {
        var author = randomAuthor().withRandomBook().withRandomBook().build();
        authorRepository.save(author);

        var authorDto = classUnderTest.getAuthors();

        var expectedAuthorDto = authorDtoFrom(author).build();
        assertThat(authorDto).containsExactly(expectedAuthorDto);
    }

    @Test
    void getAuthors_TwoAuthorsWithOneBooksEach() {
        var authors = IntStream.range(0, 2)
                .mapToObj(i -> randomAuthor().withRandomBook().build())
                .toList();
        authorRepository.saveAll(authors);

        var authorDto = classUnderTest.getAuthors();

        var expectedAuthorDtos = authors.stream()
                .map(author -> authorDtoFrom(author).build())
                .toList();
        assertThat(authorDto).containsExactlyElementsOf(expectedAuthorDtos);
    }

    @Test
    void getAuthors_OverTwoHundredAuthorsWithOneBooksEach() {
        var authors = IntStream.range(0, 201)
                .mapToObj(i -> randomAuthor().withRandomBook().build())
                .toList();
        authorRepository.saveAll(authors);

        var authorDto = classUnderTest.getAuthors();

        var expectedAuthorDtos = authors.stream()
                .map(author -> authorDtoFrom(author).build())
                .toList();
        assertThat(authorDto).containsExactlyElementsOf(expectedAuthorDtos);
    }

    @Test
    void getAuthors_OverTwoHundredAuthorsWithOverTwoHundredBooksEach() {
        var authors = IntStream.range(0, 201)
                .mapToObj(i -> randomAuthor().withRandomBooks(201).build())
                .toList();
        authorRepository.saveAll(authors);

        var authorDto = classUnderTest.getAuthors();

        var expectedAuthorDtos = authors.stream()
                .map(author -> authorDtoFrom(author).build())
                .toList();
        assertThat(authorDto).containsExactlyElementsOf(expectedAuthorDtos);
    }

    @Test
    void getAuthors_FiftyAuthorsWithTwentyBooksEach() {
        var authors = IntStream.range(0, 50)
                .mapToObj(i -> randomAuthor().withRandomBooks(20).build())
                .toList();
        authorRepository.saveAll(authors);

        var authorDto = classUnderTest.getAuthors();

        var expectedAuthorDtos = authors.stream()
                .map(author -> authorDtoFrom(author).build())
                .toList();
        assertThat(authorDto).containsExactlyElementsOf(expectedAuthorDtos);
    }

    @Test
    void getAuthors_OverNineThousandAuthorsWithOneBookEach() {
        var authors = IntStream.range(0, 9001)
                .mapToObj(i -> randomAuthor().withRandomBook().build())
                .toList();
        authorRepository.saveAll(authors);

        var authorDto = classUnderTest.getAuthors();

        var expectedAuthorDtos = authors.stream()
                .map(author -> authorDtoFrom(author).build())
                .toList();
        assertThat(authorDto).containsExactlyElementsOf(expectedAuthorDtos);
    }

    @Test
    void getAuthorsUsingQueryAnnotation_OverNineThousandAuthorsWithOneBookEach() {
        var authors = IntStream.range(0, 9001)
                .mapToObj(i -> randomAuthor().withRandomBook().build())
                .toList();
        authorRepository.saveAll(authors);

        var authorDto = classUnderTest.getAuthorsUsingQueryAnnotation();

        var expectedAuthorDtos = authors.stream()
                .map(author -> authorDtoFrom(author).build())
                .toList();
        assertThat(authorDto).containsExactlyElementsOf(expectedAuthorDtos);
    }

}