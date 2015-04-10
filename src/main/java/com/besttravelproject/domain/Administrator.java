package com.besttravelproject.domain;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String login;
    String password;

    public Administrator() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getLogin() { return login;}

    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
