package com.besttravelproject.domain;

import javax.validation.constraints.Size;

public class SearchOrderForm {
    @Size(min = 1, max = 20)
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
