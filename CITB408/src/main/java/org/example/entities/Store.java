package org.example.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private List<Terminal> terminals ;

    private Warehouse warehouse;


    public Store() {
    }

    public Store(long id, List<Terminal> terminals, Warehouse warehouse) {
        this.id = id;
        this.terminals = terminals;
        this.warehouse = warehouse;
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
