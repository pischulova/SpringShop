package com.besttravelproject.web;

import com.besttravelproject.domain.Cart;
import com.besttravelproject.domain.User;
import com.besttravelproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MakeOrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/make_order")
    String makeOrder(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (null == user || null == cart || cart.getFlights().isEmpty()) {
            model.addAttribute("error_message", "page_not_found");
            return "error";
        }

        Long orderId = orderService.makeOrder(user, cart);
        if (null != orderId) {
            session.setAttribute("cart", null);
            model.addAttribute("message", "order_sent");
        }

        return "show_cart";
    }
}
