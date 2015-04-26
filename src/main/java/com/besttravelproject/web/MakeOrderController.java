package com.besttravelproject.web;

import com.besttravelproject.domain.Cart;
import com.besttravelproject.domain.Flight;
import com.besttravelproject.domain.Order;
import com.besttravelproject.domain.User;
import com.besttravelproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

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

        Order order = new Order();
        order.setUser(user);
        for (Map.Entry entry : cart.getFlights().entrySet()) {
            order.getFlights().put((Flight)entry.getKey(), (Integer)entry.getValue());
        }
        orderService.save(order);

        return "show_cart";
    }
}
