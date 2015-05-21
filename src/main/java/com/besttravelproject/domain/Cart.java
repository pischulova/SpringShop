package com.besttravelproject.domain;

import java.util.Hashtable;

public class Cart {
    private Hashtable<OrderItem, Object> flights = new Hashtable<>();
    private static final Object PRESENT = new Object();

    public Hashtable<OrderItem, Object> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        for (OrderItem item : flights.keySet()) {
            if (flight.equals(item.getFlight())) {
                Integer currentQty = item.getQuantity();
                item.setQuantity(++currentQty);
                return;
            }
        }
        OrderItem item = new OrderItem();
        item.setFlight(flight);
        item.setPrice(flight.getPrice());
        item.setQuantity(1);

        flights.put(item, PRESENT);
    }

    public void removeFlight(Flight flight) {
        for (OrderItem item : flights.keySet()) {
            if (flight.equals(item.getFlight())) {
                Integer currentQty = item.getQuantity();
                if (currentQty > 1) {
                    item.setQuantity(--currentQty);
                    return;
                }
                flights.remove(item);
                return;
            }
        }
    }
}
