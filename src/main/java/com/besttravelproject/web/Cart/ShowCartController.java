package com.besttravelproject.web.Cart;

import com.besttravelproject.domain.Cart;
import com.besttravelproject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class ShowCartController {
    @Autowired
    FlightService flightService;

    @RequestMapping(value = "/show_cart", method = RequestMethod.GET)
    String showCart(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (null != cart) {
            model.addAttribute("cart", cart.getFlights().keySet());
        }
        return "show_cart";
    }
}
