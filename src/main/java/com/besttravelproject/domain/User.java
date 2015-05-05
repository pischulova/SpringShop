package com.besttravelproject.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findClientsByStatus", query = "SELECT u FROM User u WHERE u.isBad = ?1 AND u.userRole = ?2"),
        @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.userRole = ?1"),
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = ?1"),
        @NamedQuery(name = "User.countClientsByStatus", query = "SELECT COUNT(u) FROM User u WHERE u.isBad = ?1 AND u.userRole = ?2"),
        @NamedQuery(name = "User.countByRole", query = "SELECT COUNT(u) FROM User u WHERE u.userRole = ?1"),
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    UserRole userRole;

    String username;

    String password;

    @Transient
    String confirmPassword;

    String name;

    @NotEmpty (message = "Email is required field")
    @Email(message = "Invalid email address")
    String email;

    String phone;

    Boolean isBad;

    @OneToMany(mappedBy = "user", cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    List<Order> orders = new LinkedList<>();

    public User() {}

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() { return confirmPassword; }

    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userRole=" + userRole +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
