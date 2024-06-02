package org.example.dto;

import org.example.entities.Client;
import org.example.entities.Employee;
import org.example.entities.Purchase;
import org.example.entities.Vehicle;

import java.time.LocalDate;
import java.util.Set;

public class CompanyDto {
    private long id;
    private String name;
    private Set<Vehicle> vehicles;
    private Set<Employee> employees;

    private Set<Purchase> purchases;

    private Set<Client> clients;

    public CompanyDto(long id, String name, Set<Vehicle> vehicles, Set<Employee> employees, Set<Purchase> purchases, Set<Client> clients) {
        this.id = id;
        this.name = name;
        this.vehicles = vehicles;
        this.employees = employees;
        this.purchases = purchases;
        this.clients = clients;
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

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}