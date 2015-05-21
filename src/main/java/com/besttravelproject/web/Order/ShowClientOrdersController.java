package com.besttravelproject.web.Order;

import com.besttravelproject.domain.Order;
import com.besttravelproject.domain.User;
import com.besttravelproject.service.OrderService;
import com.besttravelproject.web.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShowClientOrdersController {
    final static int RESULTS_PER_PAGE = 10;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/client_orders", method = RequestMethod.GET)
    String showClientOrders(@RequestParam(value = "page", required = false) Integer paramPage,
                            Model model, RedirectAttributes attributes, HttpSession session){

        User user = (User)session.getAttribute("user");

        if (ControllerHelper.checkParamsForErrors(null, paramPage, attributes)) {
            return "redirect:/client_orders";
        }

        if (null == paramPage) {
            paramPage = 1;
        }

        List<Order> orders = orderService.
                findByClientId(RESULTS_PER_PAGE, (paramPage - 1) * RESULTS_PER_PAGE, user.getId());
        long pageNumber = (int) Math.ceil(orderService.countByClientId(user.getId())/(double)RESULTS_PER_PAGE);

        if (orders.size() == 0) {
            model.addAttribute("message", "no_orders");
            return "show_client_orders";
        }
        model.addAttribute("page", paramPage);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("ordersList", orders);

        return "show_client_orders";
    }
}
