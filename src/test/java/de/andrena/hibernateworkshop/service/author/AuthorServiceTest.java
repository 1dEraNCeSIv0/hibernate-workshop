package de.andrena.hibernateworkshop.service.author;

import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.test.IntegrationTest;
import de.andrena.hibernateworkshop.test.author.AuthorDtoCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.IntStream;

import static de.andrena.hibernateworkshop.test.author.AuthorCreator.randomAuthorBuilder;
import static de.andrena.hibernateworkshop.test.author.AuthorDtoCreator.authorDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class AuthorServiceTest extends IntegrationTest {

    @Autowired
    private AuthorService classUnderTest;

    @Test
    void getAuthorById_OneBook() {
        var author = defaultAuthor();
        authorRepository.save(author);

        var authorDto = classUnderTest.getAuthorById(author.getId());

        assertThat(authorDto).isEqualTo(authorDtoFrom(author));
    }

    @Test
    void getAuthorById_TwoBooks() {
        var author = defaultAuthor(2);
        authorRepository.save(author);

        var authorDto = classUnderTest.getAuthorById(author.getId());

        assertThat(authorDto).isEqualTo(authorDtoFrom(author));
    }

    @Test
    void getAuthorById_OverTwoHundredBooks() {
        var author = defaultAuthor(201);
        authorRepository.save(author);

        var authorDto = classUnderTest.getAuthorById(author.getId());

        assertThat(authorDto).isEqualTo(authorDtoFrom(author));
    }

    @Test
    void getAuthors_OneAuthorWithOneBook() {
        var author = defaultAuthor();
        authorRepository.save(author);

        var authorDto = classUnderTest.getAuthors();

        assertThat(authorDto).containsExactly(authorDtoFrom(author));
    }

    @Test
    void getAuthors_OneAuthorWithTwoBooks() {
        var author = defaultAuthor(2);
        authorRepository.save(author);

        var authorDto = classUnderTest.getAuthors();

        assertThat(authorDto).containsExactly(authorDtoFrom(author));
    }

    @Test
    void getAuthors_TwoAuthorsWithOneBooksEach() {
        var authors = IntStream.range(0, 2)
                .mapToObj(i -> defaultAuthor())
                .toList();
        authorRepository.saveAll(authors);

        var authorDto = classUnderTest.getAuthors();

        var expectedAuthorDtos = authors.stream()
                .map(AuthorDtoCreator::authorDtoFrom)
                .toList();
        assertThat(authorDto).containsExactlyElementsOf(expectedAuthorDtos);
    }

    @Test
    void getAuthors_OverTwoHundredAuthorsWithOneBooksEach() {
        var authors = IntStream.range(0, 201)
                .mapToObj(i -> defaultAuthor())
                .toList();
        authorRepository.saveAll(authors);

        var authorDto = classUnderTest.getAuthors();

        var expectedAuthorDtos = authors.stream()
                .map(AuthorDtoCreator::authorDtoFrom)
                .toList();
        assertThat(authorDto).containsExactlyElementsOf(expectedAuthorDtos);
    }

    @Test
    void getAuthors_OverTwoHundredAuthorsWithOverTwoHundredBooksEach() {
        var authors = IntStream.range(0, 201)
                .mapToObj(i -> defaultAuthor(201))
                .toList();
        authorRepository.saveAll(authors);

        var authorDto = classUnderTest.getAuthors();

        var expectedAuthorDtos = authors.stream()
                .map(AuthorDtoCreator::authorDtoFrom)
                .toList();
        assertThat(authorDto).containsExactlyElementsOf(expectedAuthorDtos);
    }

    @Test
    void getAuthors_FiftyAuthorsWithTwentyBooksEach() {
        var authors = IntStream.range(0, 50)
                .mapToObj(i -> defaultAuthor(20))
                .toList();
        authorRepository.saveAll(authors);

        var authorDto = classUnderTest.getAuthors();

        var expectedAuthorDtos = authors.stream()
                .map(AuthorDtoCreator::authorDtoFrom)
                .toList();
        assertThat(authorDto).containsExactlyElementsOf(expectedAuthorDtos);
    }

    @Test
    void getAuthors_OverNineThousandAuthorsWithOneBookEach() {
        var authors = IntStream.range(0, 9001)
                .mapToObj(i -> defaultAuthor())
                .toList();
        authorRepository.saveAll(authors);

        var authorDto = classUnderTest.getAuthors();

        var expectedAuthorDtos = authors.stream()
                .map(AuthorDtoCreator::authorDtoFrom)
                .toList();
        assertThat(authorDto).containsExactlyElementsOf(expectedAuthorDtos);
    }

    @Test
    void getAuthorsUsingQueryAnnotation_OverNineThousandAuthorsWithOneBookEach() {
        var authors = IntStream.range(0, 9001)
                .mapToObj(i -> defaultAuthor())
                .toList();
        authorRepository.saveAll(authors);

        var authorDto = classUnderTest.getAuthorsUsingQueryAnnotation();

        var expectedAuthorDtos = authors.stream()
                .map(AuthorDtoCreator::authorDtoFrom)
                .toList();
        assertThat(authorDto).containsExactlyElementsOf(expectedAuthorDtos);
    }

    private static Author defaultAuthor() {
        return randomAuthorBuilder().withRandomBook().build();
    }

    private static Author defaultAuthor(int numberOfBooks) {
        return randomAuthorBuilder().withRandomBooks(numberOfBooks).build();
    }

}