package com.besttravelproject.domain;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String login;
    String password;
    String name;
    String email;
    String phone;
    Boolean isBad;

    Integer orderAmount;

    @OneToMany(mappedBy = "client", cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    List<Order> orders = new LinkedList<>();

    @Transient
    List<Flight> cart = new LinkedList<>();

    public Client() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsBad() {
        return isBad;
    }

    public void setIsBad(Boolean isBad) {
        this.isBad = isBad;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }
}
