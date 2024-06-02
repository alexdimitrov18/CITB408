package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private List<Terminal> terminals ;

    private Warehouse warehouse;

    @Positive
    private double spoilDiscount;


    public Store() {
    }

    public Store(long id, List<Terminal> terminals, Warehouse warehouse, double spoilDiscount) {
        this.id = id;
        this.terminals = terminals;
        this.warehouse = warehouse;
        this.spoilDiscount = spoilDiscount;
    }

    public double getSpoilDiscount() {
        return spoilDiscount;
    }

    public void setSpoilDiscount(double spoilDiscount) {
        this.spoilDiscount = spoilDiscount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<Terminal> terminals) {
        this.terminals = terminals;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

}
