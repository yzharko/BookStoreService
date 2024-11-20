package ru.goth.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @Column(name = "book_id")
    private long id;
    @Column(name = "title")
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private Author author;
    @Column(name = "genre")
    private String genre;
    @Column(name = "price")
    private float price;
    @Column(name = "amount")
    private int amount;
    @OneToMany(mappedBy = "book_id", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List <BuyBook> buyBooks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author.toString() +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", buyBooks=" + buyBooks +
                '}';
    }
}
