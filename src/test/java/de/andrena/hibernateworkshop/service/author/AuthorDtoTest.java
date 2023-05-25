package de.andrena.hibernateworkshop.service.author;

import org.junit.jupiter.api.Test;

import static de.andrena.hibernateworkshop.test.author.AuthorCreator.randomAuthorBuilder;
import static de.andrena.hibernateworkshop.test.author.AuthorDtoCreator.authorDtoFrom;
import static de.andrena.hibernateworkshop.test.author.AuthorDtoCreator.minimalAuthorDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class AuthorDtoTest {

    @Test
    void toMinimalDto() {
        var author = randomAuthorBuilder().withRandomBook().build();

        var authorDto = AuthorDto.toMinimalDto(author);

        assertThat(authorDto).isEqualTo(minimalAuthorDtoFrom(author));
    }

    @Test
    void toFullDto() {
        var author = randomAuthorBuilder().withRandomBook().build();

        var authorDto = AuthorDto.toFullDto(author);

        assertThat(authorDto).isEqualTo(authorDtoFrom(author));
    }

}