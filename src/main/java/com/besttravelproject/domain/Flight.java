package com.besttravelproject.domain;

import javax.persistence.*;

@Entity
@Table(name = "flights")
@NamedQueries({
        @NamedQuery(name = "Flight.findAll", query = "SELECT f FROM Flight f where f.isDisabled = FALSE ORDER BY f.id"),
        @NamedQuery(name = "Flight.findByCountry", query = "SELECT f FROM Flight f " +
                        "WHERE f.isDisabled = FALSE AND (f.country.nameEn LIKE :name OR f.country.nameRu LIKE :name) " +
                        "ORDER BY f.id"),
        @NamedQuery(name = "Flight.countAll", query = "SELECT COUNT(f) FROM Flight f where f.isDisabled = FALSE"),
        @NamedQuery(name = "Flight.countByCountry", query = "SELECT COUNT(f) FROM Flight f " +
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

        if (!country.equals(flight.country)) return false;
        if (!id.equals(flight.id)) return false;
        if (!isDisabled.equals(flight.isDisabled)) return false;
        if (!nameEn.equals(flight.nameEn)) return false;
        if (!nameRu.equals(flight.nameRu)) return false;
        if (!price.equals(flight.price)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nameEn.hashCode();
        result = 31 * result + nameRu.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + isDisabled.hashCode();
        return result;
    }
}
