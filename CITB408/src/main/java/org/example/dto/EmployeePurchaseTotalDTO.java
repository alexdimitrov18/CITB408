package org.example.dto;

public class EmployeePurchaseTotalDTO {

    private String name;
    private  String family_name;

    private String company_name;

    private  double count;

    public EmployeePurchaseTotalDTO(String name, String family_name, String company_name, double count) {
        this.name = name;
        this.family_name = family_name;
        this.company_name = company_name;
        this.count = count;
    }

    public EmployeePurchaseTotalDTO() {
    }

    @Override
    public String toString() {
        return "EmployeePurchaseTotalDTO{" +
                "name='" + name + '\'' +
                ", family_name='" + family_name + '\'' +
                ", company_name='" + company_name + '\'' +
                ", count=" + count +
                '}';
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

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
