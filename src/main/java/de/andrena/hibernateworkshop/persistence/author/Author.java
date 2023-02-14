package de.andrena.hibernateworkshop.persistence.author;

import de.andrena.hibernateworkshop.persistence.book.Book;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
// TODO Ruben Gehring 08.02.2023: Lever 2 (Book -> Author ManyToOne)
//@BatchSize(size = 100)
public class Author {

    @Id
    private UUID id;

    private String name;
    private String address;

    // TODO Ruben Gehring 08.02.2023: Lever 1
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "author")
//    @Fetch(FetchMode.SELECT)
//    @BatchSize(size = 100)
    private List<Book> books;

    public Author() {}

    public Author(UUID id, String name, String address, List<Book> books) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.books = books;
    }

    public UUID getId() {
        return id;
    }

    public Author setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Author setAddress(String address) {
        this.address = address;
        return this;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Author setBooks(List<Book> books) {
        this.books = books;
        return this;
    }

}
