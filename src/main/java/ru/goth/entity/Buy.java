package ru.goth.entity;

public class Buy {
    private long id;
    private String description;
    private String client;

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

    @Override
    public String toString() {
        return "Buy{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", client='" + client + '\'' +
                '}';
    }
}
