package com.besttravelproject.web.Order;

import com.besttravelproject.domain.Order;
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
public class ShowOrdersController {
    final static int RESULTS_PER_PAGE = 10;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/admin/show_orders", method = RequestMethod.GET)
    String showAllOrders(@RequestParam(value = "search", required = false) String paramSearch,
                         @RequestParam(value = "page", required = false) Integer paramPage,
                         Model model, RedirectAttributes attributes, HttpSession session){

        if (ControllerHelper.checkParamsForErrors(paramSearch, paramPage, attributes)) {
            return "redirect:/show_orders";
        }

        if (null == paramPage) {
            paramPage = 1;
        }

        List<Order> orders;
        long pageNumber;
        if (null == paramSearch || paramSearch.length()==0) {
            orders = orderService.findAll(RESULTS_PER_PAGE, (paramPage-1)*RESULTS_PER_PAGE);
            pageNumber = (int) Math.ceil(orderService.countAll()/(double)RESULTS_PER_PAGE);
            paramSearch = "";
        } else {
            orders = orderService.findByClientName(RESULTS_PER_PAGE, (paramPage - 1) * RESULTS_PER_PAGE, paramSearch);
            pageNumber = (int) Math.ceil(orderService.countByClientName(paramSearch)/(double)RESULTS_PER_PAGE);
        }

        if (orders.size() == 0) {
            model.addAttribute("message", "nothing_found");
            return "show_orders";
        }
        model.addAttribute("page", paramPage);
        model.addAttribute("search", paramSearch);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("ordersList", orders);

        return "show_orders";
    }
}
