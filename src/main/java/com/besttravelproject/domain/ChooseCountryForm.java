package com.besttravelproject.domain;

import javax.validation.constraints.Size;

public class ChooseCountryForm {
    @Size(min = 1, max = 20)
    String countryName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
