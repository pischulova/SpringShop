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
    User user;

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    List<OrderItem> items = new ArrayList<>();

    Long sum = 0L;

    Boolean isApproved;

    public Order() { }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addOrderItem(OrderItem item) {
        if (items.contains(item))
            return ;
        items.add(item);
        item.setOrder(this);
    }

    public Long getSum() { return sum; }

    public void setSum(Long sum) { this.sum = sum; }

    public Boolean getIsApproved() { return isApproved; }

    public void setIsApproved(Boolean isApproved) { this.isApproved = isApproved; }

}
