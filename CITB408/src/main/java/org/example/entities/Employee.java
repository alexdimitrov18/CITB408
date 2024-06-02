package org.example.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Employee name and surname
    @Column(name = "name", nullable = false)
    @Size(max = 30, message = "Max length is 30 characters ")
    private String name;

    @Column(name = "family_name", nullable = false)
    @Size(max = 30, message = "Max length is 30 characters")
    private String family_name;
    //Added restrinctions to EGN
    @Column(name = "EGN", nullable = false)
    @NotBlank(message = "Employee's EGN cannot be null")
    @Size(min = 10, max = 10, message = "EGN has to be 10 characters")
    @Pattern(regexp = "^\\d+$" , message = "A person's social security number has to only contain digits!")
    private String EGN;
    // A salary must be a positive number
    @Column(name = "salary", nullable = false)
    @Positive
    private double salary ;
    //Many employees can work for the same company
    @ManyToOne
    private Company company;
    //An employee can have many skills, these skills can be had by many employees
    @ManyToMany( fetch = FetchType.LAZY)
    private Set<Skill> skills ;
    // One employee can fulfil many purchases
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<Purchase> purchases;
    public Employee() {  //empty constructor
    }
    //Constructor with parameters
    public Employee(long id, String name, String family_name, String EGN, double salary, Company company, Set<Skill> skills, Set<Purchase> purchases) {
        this.id = id;
        this.name = name;
        this.family_name = family_name;
        this.EGN = EGN;
        this.salary = salary;
        this.company = company;
        this.skills = skills;
        this.purchases = purchases;
    }
    //Getters, setters, equals, to string
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

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;

    }

    public String getEGN() {
        return EGN;
    }

    public void setEGN(String EGN) {
        this.EGN = EGN;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Double.compare(salary, employee.salary) == 0 && Objects.equals(name, employee.name) && Objects.equals(family_name, employee.family_name) && Objects.equals(EGN, employee.EGN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, family_name, EGN, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id + "\n" +
                ", name='" + name + '\'' +
                ", family_name='" + family_name + '\'' +
                ", EGN='" + EGN + '\'' +
                ", salary=" + salary +
                '}';
    }
}
