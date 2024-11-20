package ru.goth.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {
    @Id
    @Column(name = "author_id")
    private long id;
    @Column(name = "name_author")
    private String name;
    @OneToMany(mappedBy = "author_id", cascade = CascadeType.ALL)
    private List <Book> books;

    public Author() {}
    public Author(String name) {
        this.name = name;
    }
    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

