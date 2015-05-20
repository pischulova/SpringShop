package com.besttravelproject.domain;

import java.util.*;

public class Cart {
    private Hashtable<Flight, Integer> flights = new Hashtable<>();

    public Hashtable<Flight, Integer> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        if (flights.containsKey(flight)) {
            Integer currentQty = flights.get(flight);
            flights.put(flight, ++currentQty);
            return;
        }
        flights.put(flight, 1);
    }

    public void removeFlight(Flight flight) {
        if (flights.containsKey(flight)) {
            Integer currentQty = flights.get(flight);
            if (currentQty > 1) {
                flights.put(flight, --currentQty);
                return;
            }
            flights.remove(flight);
        }
    }
}
