package com.besttravelproject.web;

import com.besttravelproject.domain.Order;
import com.besttravelproject.domain.User;
import com.besttravelproject.domain.UserRole;
import com.besttravelproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ShowOrdersController {
    final static double RESULTS_PER_PAGE = 10.0;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/show_orders", method = RequestMethod.GET)
    String showOrders(Model model, HttpSession session, HttpServletRequest request){
        User user = (User)session.getAttribute("user");
        if (null == user) {
            model.addAttribute("error_message", "page_not_found");
            return "error";
        }

        PagedListHolder<Order> orders = null;

        if (user.getUserRole()== UserRole.ADMIN) {
            orders = new PagedListHolder<>(orderService.findAll());
        } else {
            orders = new PagedListHolder<>(orderService.findByClientId(user.getId()));
        }

        if (null == orders) {
            model.addAttribute("message", "nothing_found");
            return "show_orders";
        }
        int pageNumber = (int) Math.ceil(orders.getNrOfElements() / RESULTS_PER_PAGE);

        String paramPage = request.getParameter("page");
        if (null != paramPage) {
            Integer page = Integer.parseInt(paramPage);

            if (null == page || page < 1 || page > pageNumber) {
                model.addAttribute("error_message", "page_not_found");
                return "error";
            }

            orders.setPageSize((int)RESULTS_PER_PAGE);
            orders.setPage(page - 1);
        }
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("ordersList", orders);

        return "show_orders";
    }
}
