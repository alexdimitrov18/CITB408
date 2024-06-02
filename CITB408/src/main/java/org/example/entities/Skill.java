package org.example.entities;


import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //Skill's description - Bus driver, Plane driver and so on.
    @Column(name = "type", nullable = false)
    private String type;
    @ManyToMany(mappedBy = "skills")
    private Set<Employee> employee ;

    @OneToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    private Set<Payload> payloads;
    public Skill() {
    }

    public Skill(long id, String type, Set<Employee> employee, Set<Payload> payloads) {
        this.id = id;
        this.type = type;
        this.employee = employee;
        this.payloads = payloads;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Payload> getPayloads() {
        return payloads;
    }

    public void setPayloads(Set<Payload> payloads) {
        this.payloads = payloads;
    }

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill1 = (Skill) o;
        return id == skill1.id && Objects.equals(type, skill1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
