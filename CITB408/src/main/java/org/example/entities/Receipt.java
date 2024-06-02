package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @NotBlank(message = "Cashier fields cannot be blank!")
    private Cashier cashier;
    @Column(name = "sale_date", nullable = false)
    private LocalDateTime sale_date;
    @Column(name = "total_price", nullable = false)
    @Positive
    private Double totalPrice;

    public Receipt() {
    }

    public Receipt(long id, Cashier cashier, LocalDateTime sale_date, Double totalPrice) {
        this.id = id;
        this.cashier = cashier;
        this.sale_date = sale_date;
        this.totalPrice = totalPrice;
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

    public LocalDateTime getSale_date() {
        return sale_date;
    }

    public void setSale_date(LocalDateTime sale_date) {
        this.sale_date = sale_date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receipt receipt)) return false;
        return id == receipt.id && Objects.equals(cashier, receipt.cashier) && Objects.equals(sale_date, receipt.sale_date) && Objects.equals(totalPrice, receipt.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cashier, sale_date, totalPrice);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", cashier=" + cashier +
                ", sale_date=" + sale_date +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
