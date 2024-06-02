package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime start_time;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime end_time;
    // End point
    @Column(name = "arrival_point", nullable = false)
    private String arrival_point;
    // Price for delivery
    @Column(name = "price", nullable = false)
    @Positive
    private double  price;
    // Starting point
    @Column(name = "departure_point", nullable = false)
    private String departure_point;
    // Multiple purchases can be fulfilled by the same employee
    @ManyToOne
    private Employee employee;   // ????? pitai ivo  - tova e vuv purchase klasa
    //Same with vehicles
    @ManyToOne
    private Vehicle vehicle;
    //Same with companies
    @ManyToOne
    private Company company;
    // Each purchase is fulfilled by the same payload
    @OneToOne
    private Payload payload;
    //Many clients can have many purchases
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Client> clients;
    //Each paid purchase has a single receipt
    @OneToMany(mappedBy = "purchases")
    private Set<Receipt> receipts;
    public Purchase() {// empty constructor
    }
    //Constructor with parameters
    public Purchase(long id, LocalDateTime start_time, LocalDateTime end_time, String arrival_point, double price, String departure_point, Employee employee, Vehicle vehicle, Company company, Payload payload, Set<Client> clients, Set<Receipt> receipts) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.arrival_point = arrival_point;
        this.price = price;
        this.departure_point = departure_point;
        this.employee = employee;
        this.vehicle = vehicle;
        this.company = company;
        this.payload = payload;
        this.clients = clients;
        this.receipts = receipts;
    }
    //Getters, setters and so on
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public String getArrival_point() {
        return arrival_point;
    }

    public void setArrival_point(String arrival_point) {
        this.arrival_point = arrival_point;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDeparture_point() {
        return departure_point;
    }

    public void setDeparture_point(String departure_point) {
        this.departure_point = departure_point;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employees) {
        this.employee = employees;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id == purchase.id && Double.compare(price, purchase.price) == 0 && Objects.equals(start_time, purchase.start_time) && Objects.equals(end_time, purchase.end_time) && Objects.equals(arrival_point, purchase.arrival_point) && Objects.equals(departure_point, purchase.departure_point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start_time, end_time, arrival_point, price, departure_point);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", arrival_point='" + arrival_point + '\'' +
                ", price=" + price +
                ", departure_point='" + departure_point + '\'' +
                '}';
    }

    public Set<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<Receipt> receipts) {
        this.receipts = receipts;
    }
}
