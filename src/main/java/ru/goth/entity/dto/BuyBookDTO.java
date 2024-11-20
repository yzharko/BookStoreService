package ru.goth.entity.dto;

import ru.goth.entity.Book;
import ru.goth.entity.Buy;

public class BuyBookDTO {
    private Buy buy;
    private Book book;
    private int amount;

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
