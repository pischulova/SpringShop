package com.besttravelproject.web.Cart;

import com.besttravelproject.domain.Cart;
import com.besttravelproject.domain.Flight;
import com.besttravelproject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AddToCartController {
    @Autowired
    FlightService flightService;

    @RequestMapping(value = "/add_to_cart/{id}", method = RequestMethod.GET)
    String addToCart(@PathVariable Long id, HttpSession session, Model model) {

        if (id < 1) {
            model.addAttribute("error_message", "page_not_found");
            return "error";
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (null == cart) {
            cart = new Cart();
        }
        Flight flight = flightService.findById(id);

        if (null != flight) {
            cart.addFlight(flight);
        }
        session.setAttribute("cart", cart);
        model.addAttribute("cart", cart.getFlights().keySet());

        return "show_cart";
    }
}
