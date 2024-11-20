package ru.goth.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Buy {
    @Id
    @Column(name = "buy_id")
    private long id;
    @Column(name = "buy_description")
    private String description;
    @Column(name = "client")
    private String client;
    @OneToMany(mappedBy = "buy_id", cascade = CascadeType.ALL)
    private List <BuyBook> buyBooks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
