package de.andrena.hibernateworkshop.service.author;

import org.junit.jupiter.api.Test;

import static de.andrena.hibernateworkshop.test.author.AuthorBuilder.randomAuthor;
import static de.andrena.hibernateworkshop.test.author.AuthorDtoBuilder.authorDtoFrom;
import static de.andrena.hibernateworkshop.test.author.AuthorDtoBuilder.minimalAuthorDtoFrom;
import static org.assertj.core.api.Assertions.assertThat;

class AuthorDtoTest {

    @Test
    void toMinimalDto() {
        var author = randomAuthor().withRandomBook().build();

        var authorDto = AuthorDto.toMinimalDto(author);

        assertThat(authorDto).isEqualTo(minimalAuthorDtoFrom(author).build());
    }

    @Test
    void toFullDto() {
        var author = randomAuthor().withRandomBook().build();

        var authorDto = AuthorDto.toFullDto(author);

        assertThat(authorDto).isEqualTo(authorDtoFrom(author).build());
    }

}