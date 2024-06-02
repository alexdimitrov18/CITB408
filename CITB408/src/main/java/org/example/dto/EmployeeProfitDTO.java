package org.example.dto;

public class EmployeeProfitDTO {

    private String name;
    private  String family_name;

    private String company_name;

    private  double profit;

    public EmployeeProfitDTO(String name, String family_name, String company_name, double profit) {
        this.name = name;
        this.family_name = family_name;
        this.company_name = company_name;
        this.profit = profit;
    }

    public EmployeeProfitDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "EmployeeProfitDTO{" +
                "name='" + name + '\'' +
                ", family_name='" + family_name + '\'' +
                ", company_name='" + company_name + '\'' +
                ", profit=" + profit +
                '}';
    }
}
