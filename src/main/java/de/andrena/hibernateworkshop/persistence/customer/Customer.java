package de.andrena.hibernateworkshop.persistence.customer;

import de.andrena.hibernateworkshop.persistence.author.Author;
import de.andrena.hibernateworkshop.persistence.book.Book;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Customer {

    @Id
    private UUID id;

    private String name;
    private String address;

    @OneToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    // TODO Ruben Gehring 08.02.2023: Lever 3
//    @Fetch(FetchMode.JOIN)
//    @BatchSize(size = 100)
    private List<Book> checkedOutBooks;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    // TODO Ruben Gehring 08.02.2023: Lever 4
//    @Fetch(FetchMode.JOIN)
//    @BatchSize(size = 100)
    private List<Author> favoriteAuthors;

    public Customer() {}

    public Customer(UUID id, String name, String address, List<Book> checkedOutBooks, List<Author> favoriteAuthors) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.checkedOutBooks = checkedOutBooks;
        this.favoriteAuthors = favoriteAuthors;
    }

    public UUID getId() {
        return id;
    }

    public Customer setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Customer setAddress(String address) {
        this.address = address;
        return this;
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public Customer setCheckedOutBooks(List<Book> checkedOutBooks) {
        this.checkedOutBooks = checkedOutBooks;
        return this;
    }

    public List<Author> getFavoriteAuthors() {
        return favoriteAuthors;
    }

    public Customer setFavoriteAuthors(List<Author> favoriteAuthors) {
        this.favoriteAuthors = favoriteAuthors;
        return this;
    }

}
