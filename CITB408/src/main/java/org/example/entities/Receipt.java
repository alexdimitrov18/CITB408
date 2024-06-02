package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Cashier fields cannot be blank!")
    private Cashier cashier;
    @Column(name = "sale_date", nullable = false)
    private LocalDateTime sale_date;

    private List<Goods> goods;

    public Receipt() {
    }

    public Receipt(long id, Cashier cashier, LocalDateTime sale_date, List<Goods> goods) {
        this.id = id;
        this.cashier = cashier;
        this.sale_date = sale_date;
        this.goods = goods;
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

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return id == receipt.id && Objects.equals(cashier, receipt.cashier) && Objects.equals(sale_date, receipt.sale_date) && Objects.equals(goods, receipt.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cashier, sale_date, goods);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", cashier=" + cashier +
                ", sale_date=" + sale_date +
                ", goods=" + goods +
                '}';
    }
}
