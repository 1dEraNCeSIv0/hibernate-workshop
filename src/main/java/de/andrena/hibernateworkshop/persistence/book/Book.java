package de.andrena.hibernateworkshop.persistence.book;

import de.andrena.hibernateworkshop.persistence.author.Author;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Book {

    @Id
    private UUID id;

    private String title;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id")
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

    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }

}
