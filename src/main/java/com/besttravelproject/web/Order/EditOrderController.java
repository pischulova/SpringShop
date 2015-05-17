package com.besttravelproject.web.Order;

import com.besttravelproject.domain.Order;
import com.besttravelproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditOrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/admin/approve_order", method = RequestMethod.POST)
    String approveOrder(@RequestParam("id") Long id, Model model) {
        if (null == id || id < 1) {
            model.addAttribute("error_message", "page_not_found");
            return "error";
        }

        Order order = orderService.findById(id);
        if (null == order) {
            model.addAttribute("message", "nothing_found");
            return "order_info";
        }
        order.setIsApproved(true);
        orderService.update(order);

        model.addAttribute("order", order);
        model.addAttribute("orderContents", order.getItems());

        return "order_info";
    }
}
