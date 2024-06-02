package org.example.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Client clients;

    @ManyToOne
    private Purchase purchases;

    public Receipt() {
    }

    public Receipt(long id, Client clients, Purchase purchases) {
        this.id = id;
        this.clients = clients;
        this.purchases = purchases;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClients() {
        return clients;
    }

    public void setClients(Client clients) {
        this.clients = clients;
    }

    public Purchase getPurchases() {
        return purchases;
    }

    public void setPurchases(Purchase purchases) {
        this.purchases = purchases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return id == receipt.id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}