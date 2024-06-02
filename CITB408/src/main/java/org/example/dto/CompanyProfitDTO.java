package org.example.dto;

public class CompanyProfitDTO {
    private long id;
    private String name;

    private double  profit ;

    public CompanyProfitDTO(long id, String name, double profit) {
        this.id = id;
        this.name = name;
        this.profit = profit;
    }

    public CompanyProfitDTO() {
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

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "CompanyProfitDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profit=" + profit +
                '}';
    }

}
