package org.example.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "name", nullable = false)
    @Size(max = 60, message = "Max length is 30 characters")
    private String name;

    @Column(name = "client_funds", nullable = false)
    @Positive
    private Double clientFunds;
    public Client() {
    }

    public Client(long id, String name, Double clientFunds) {
        this.id = id;
        this.name = name;
        this.clientFunds = clientFunds;
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

    public Double getClientFunds() {
        return clientFunds;
    }

    public void setClientFunds(Double clientFunds) {
        this.clientFunds = clientFunds;
    }

}
