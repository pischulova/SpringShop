package com.besttravelproject.web.Order;

import com.besttravelproject.domain.Cart;
import com.besttravelproject.domain.Flight;
import com.besttravelproject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class EditCartController {
    @Autowired
    FlightService flightService;

    @RequestMapping(value = "/show_cart", method = RequestMethod.GET)
    String show() {
        return "show_cart";
    }

    @RequestMapping(value = "/add_to_cart", method = RequestMethod.GET)
    String addToCart(@RequestParam(value = "id") Long id,
                     HttpSession session, Model model) {

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

        return "show_cart";
    }

    @RequestMapping(value = "/remove_from_cart", method = RequestMethod.GET)
    String removeFromCart(@RequestParam("id") Long id, HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");

        if (id < 1 || null == cart) {
            model.addAttribute("error_message", "page_not_found");
            return "error";
        }

        Flight flight = flightService.findById(id);

        if (null != flight) {
            cart.removeFlight(flight);
        }
        session.setAttribute("cart", cart);

        return "show_cart";
    }
}
