package com.besttravelproject.domain;

import java.util.*;

public class Cart {
    private Map<Flight, Integer> flights = new HashMap<>();

    public Map<Flight, Integer> getFlights() {
        return Collections.unmodifiableMap(this.flights);
    }

    /* addFlight, removeFlight, clear, toString*/
}
