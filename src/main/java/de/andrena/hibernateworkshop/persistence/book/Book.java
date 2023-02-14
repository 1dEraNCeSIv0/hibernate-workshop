package de.andrena.hibernateworkshop.persistence.book;

import de.andrena.hibernateworkshop.persistence.author.Author;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Optional;
import java.util.UUID;

@Entity
public class Book {

    @Id
    private UUID id;

    private String title;

    // TODO Ruben Gehring 08.02.2023: Consider lazying this for performance reasons.
    @ManyToOne(cascade = CascadeType.MERGE)
    private Author author;

    public Book() {
    }

    public Book(UUID id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public Book setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Optional<Author> getAuthor() {
        return Optional.ofNullable(author);
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }

}
