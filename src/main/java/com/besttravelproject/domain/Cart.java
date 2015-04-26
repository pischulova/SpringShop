package com.besttravelproject.domain;

import java.util.*;

public class Cart {
    private Map<Flight, Integer> flights = new HashMap<>();

    public Map<Flight, Integer> getFlights() {
        return Collections.unmodifiableMap(this.flights);
    }

    public void addFlight(Flight flight) {
        flights.put(flight, 1);
    }

    public void removeFlight(Flight flight) {
        if (flights.containsKey(flight)) {
            flights.remove(flight);
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "flights=" + flights +
                '}';
    }
}
