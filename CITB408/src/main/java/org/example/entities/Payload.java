package org.example.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "payload")
public class Payload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //  Linking the Employee's skill and the Payload's type by the same column skill was the easies way
    // to fix the linking problems
    @ManyToOne( fetch = FetchType.LAZY)
    private Skill skills;
    //Payload's weight
    @Column(name = "weight", nullable = false)
    @Positive
    private double  weight;
    //Each Payload is for an individual purchase
    @OneToOne
    private Purchase purchases;

    //Default constructor
    public Payload() {
    }
    //Constructor with parameters
    public Payload(long id, Skill skill, double weight, Purchase purchases) {
        this.id = id;
        this.skills = skills;
        this.weight = weight;
        this.purchases = purchases;
    }
    //Getters, setters, equals
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Skill getSkills() {
        return skills;
    }

    public void setSkills(Skill skill) {
        this.skills = skills;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Purchase getPurchases() {
        return purchases;
    }

    public void setPurchases(Purchase purchases) {
        this.purchases = purchases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payload payload = (Payload) o;
        return id == payload.id && Double.compare(weight, payload.weight) == 0 && Objects.equals(skills, payload.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skills, weight);
    }
}
