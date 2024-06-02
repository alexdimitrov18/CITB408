package org.example.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    @Size(max = 60, message = "Max length is 60 characters")
    private String name;

    @Column(name = "price", nullable = false)
    @Positive
    private Double deliveryPrice;

    @Column(name = "spoilable", nullable = false)
    private Boolean spoilable  ;
    // A client can make multiple purchases
    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expiration_date;
    // One client can have many receipts

    public Goods() {
    }

    public Goods(long id, String name, Double deliveryPrice, Boolean spoilable, LocalDateTime expiration_date, Set<Receipt> receipts) {
        this.id = id;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.spoilable = spoilable;
        this.expiration_date = expiration_date;
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

    public Double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public Boolean getSpoilable() {
        return spoilable;
    }

    public void setSpoilable(Boolean spoilable) {
        this.spoilable = spoilable;
    }

    public LocalDateTime getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(LocalDateTime expiration_date) {
        this.expiration_date = expiration_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return id == goods.id && Objects.equals(name, goods.name) && Objects.equals(spoilable, goods.spoilable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, spoilable);
    }
}
