package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "terminal")
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //TODO  Ask ivo to help make a constraint so that a single cashier can be at a terminal at a time
    @NotBlank(message = "Cashier fields cannot be blank!")
    private Cashier cashier;

    public Terminal() {
    }

    public Terminal(long id, Cashier cashier) {
        this.id = id;
        this.cashier = cashier;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }
}
