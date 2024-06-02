package org.example.entities;


import jakarta.persistence.*;
import org.example.entities.Goods;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private long storeId;

    private int goodsId;

    private double goodsAmount;

    public Warehouse() {
    }

    public Warehouse(long id, long storeId, int goodsId, double goodsAmount) {
        this.id = id;
        this.storeId = storeId;
        this.goodsId = goodsId;
        this.goodsAmount = goodsAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public double getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(double goodsAmount) {
        this.goodsAmount = goodsAmount;
    }
}
