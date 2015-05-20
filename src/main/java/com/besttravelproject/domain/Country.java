package com.besttravelproject.domain;

import javax.persistence.*;

@Entity
@Table(name = "countries")
@NamedQueries({
        @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
})
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nameEn;
    String nameRu;

    public Country() { }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (!id.equals(country.id)) return false;
        if (!nameEn.equals(country.nameEn)) return false;
        if (!nameRu.equals(country.nameRu)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nameEn.hashCode();
        result = 31 * result + nameRu.hashCode();
        return result;
    }
}
