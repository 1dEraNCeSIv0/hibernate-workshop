package de.andrena.hibernateworkshop.service.author;

import de.andrena.hibernateworkshop.exception.NotFoundException;
import de.andrena.hibernateworkshop.persistence.author.AuthorRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public AuthorDto getAuthorById(UUID id) {
        return authorRepository.findById(id)
                .map(AuthorDto::toFullDto)
                .orElseThrow(() -> new NotFoundException("No author found for id %s.", id));
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> getAuthors() {
        return authorRepository.findAll().stream()
                .map(AuthorDto::toFullDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> getAuthorsJoinFetchBooks() {
        return authorRepository.findAllJoinFetchBooks().stream()
                .map(AuthorDto::toFullDto)
                .toList();
    }

}
