package com.besttravelproject.web.Order;

import com.besttravelproject.domain.Order;
import com.besttravelproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderDetailsController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/show_order/{id}", method = RequestMethod.GET)
    String showOrder(@PathVariable Long id, Model model) {
        if (id < 1) {
            model.addAttribute("error_message", "page_not_found");
            return "error";
        }

        Order order = orderService.findById(id);
        if (null == order) {
            model.addAttribute("message", "nothing_found");
            return "order_info";
        }
        model.addAttribute("order", order);
        model.addAttribute("orderContents", order.getItems());
        return "order_info";
    }
}
