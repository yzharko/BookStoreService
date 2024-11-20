package ru.goth.entity;

import javax.persistence.*;

@Entity
public class BuyBook {
    @Id
    @Column(name = "buy_book_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "buy_id")
    private Buy buy;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(name = "amount")
    private int amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Buy getBuy() {
        return buy;
    }

    public void setBuy(Buy buy) {
        this.buy = buy;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
