package de.andrena.hibernateworkshop.service;

import de.andrena.hibernateworkshop.exception.NotFoundException;
import de.andrena.hibernateworkshop.persistence.repository.AuthorRepository;
import de.andrena.hibernateworkshop.service.dto.AuthorDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public AuthorDto getAuthor(UUID id) {
        return authorRepository.findById(id)
                .map(AuthorDto::toFullDto)
                .orElseThrow(() -> new NotFoundException("No author found for id %s.", id));
    }

}
