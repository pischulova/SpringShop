package com.besttravelproject.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Temporal(TemporalType.DATE)
    Date date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @ElementCollection
    @CollectionTable(name="order_items", joinColumns = @JoinColumn(name="order_id"))
    @MapKeyJoinColumn(name="flight_id", referencedColumnName = "id")
    @Column(name = "quantity")
    private Map<Flight, Integer> flights = new HashMap<>();

    Long sum;

    int isApproved;

    public Order() { }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Map<Flight, Integer> getFlights() { return flights; }

    public void setFlights(Map<Flight, Integer> flights) { this.flights = flights; }

    public Long getSum() { return sum; }

    public void setSum(Long sum) { this.sum = sum; }

    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    @PrePersist
    @PreUpdate
    private void calculateSum() {
        for (Map.Entry<Flight, Integer> entry : flights.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
    }
}
