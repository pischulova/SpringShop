package com.besttravelproject.domain;

import javax.validation.constraints.Size;

public class ChooseCountryForm {
    @Size(min = 2, max = 20, message="Sorry, request must be 2 to 20 symbols long")
    String countryName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
