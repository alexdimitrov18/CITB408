package org.example.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.example.entities.Cashier;
import org.example.entities.Goods;
import org.example.entities.Receipt;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ReceiptDTO {


    private long id;

    private Cashier cashier;
    private LocalDateTime sale_date;
    private List<Integer> countGoods;

    private List<Goods> goods;
    private Double totalPrice;

    public ReceiptDTO() {
    }

    public ReceiptDTO(long id, Cashier cashier, LocalDateTime sale_date, Double totalPrice) {
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
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", cashier=" + cashier +
                ", sale_date=" + sale_date +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
