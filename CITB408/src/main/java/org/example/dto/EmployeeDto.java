package org.example.dto;

public class EmployeeDto {
    private long id;
    private String name;

    private String family_name;

    private String EGN;

    public EmployeeDto(long id, String name, String family_name, String EGN) {
        this.id = id;
        this.name = name;
        this.family_name = family_name;
        this.EGN = EGN;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family_name='" + family_name + '\'' +
                ", EGN='" + EGN + '\'' +
                '}';
    }

}
