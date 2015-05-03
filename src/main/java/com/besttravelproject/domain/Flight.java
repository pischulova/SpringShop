package com.besttravelproject.domain;

import javax.persistence.*;

@Entity
@Table(name = "flights")
@NamedQueries({
        @NamedQuery(name = "Flight.findAll", query = "SELECT f FROM Flight f where f.isDisabled = FALSE ORDER BY f.id"),
        @NamedQuery(name = "Flight.findLike", query = "SELECT f FROM Flight f " +
                        "WHERE f.isDisabled = FALSE AND (f.country.nameEn LIKE ?1 OR f.country.nameRu LIKE ?2) " +
                        "ORDER BY f.id"),
        @NamedQuery(name = "Flight.countRowsAll", query = "SELECT COUNT(f) FROM Flight f where f.isDisabled = FALSE"),
        @NamedQuery(name = "Flight.countRowsLike", query = "SELECT COUNT(f) FROM Flight f " +
                "where f.isDisabled = FALSE AND (f.country.nameEn LIKE ?1 OR f.country.nameRu LIKE ?2)")
})
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nameEn;
    String nameRu;

    @ManyToOne
    @JoinColumn(name = "country_id")
    Country country;

    Long price;

    Boolean isDisabled;

    public Flight() { }

    public Long getId() { return id; }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (!id.equals(flight.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
