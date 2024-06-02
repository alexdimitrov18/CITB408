package org.example.dto;

public class CompanyPayloadDto {

    private  long company_id;
    private long purchase_id;

    private long payload_id;

    public CompanyPayloadDto(long company_id, long purchase_id, long payload_id) {
        this.company_id = company_id;
        this.purchase_id = purchase_id;
        this.payload_id = payload_id;
    }

    public CompanyPayloadDto() {
    }

    public long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(long company_id) {
        this.company_id = company_id;
    }

    public long getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(long purchase_id) {
        this.purchase_id = purchase_id;
    }

    public long getPayload_id() {
        return payload_id;
    }

    public void setPayload_id(long payload_id) {
        this.payload_id = payload_id;
    }

    @Override
    public String toString() {
        return "CompanyPayloadDto{" +
                "company_id=" + company_id +
                ", purchase_id=" + purchase_id +
                ", payload_id=" + payload_id +
                '}';
    }
}
