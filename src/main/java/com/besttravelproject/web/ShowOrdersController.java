package com.besttravelproject.web;

import com.besttravelproject.domain.Order;
import com.besttravelproject.domain.SearchOrderForm;
import com.besttravelproject.domain.User;
import com.besttravelproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @ModelAttribute("searchOrderForm")
    public SearchOrderForm constructForm() {
        return new SearchOrderForm();
    }

    @RequestMapping(value = "/show_orders", method = RequestMethod.GET)
    String showAllOrders(@RequestParam(value = "search", required = false) String paramSearch,
                         @RequestParam(value = "page", required = false) Integer paramPage,
                         Model model, RedirectAttributes attributes, HttpSession session){

        User user = (User)session.getAttribute("user");

        if (checkParamsForErrors(paramSearch, paramPage, user, attributes)) {
            return "redirect:/orders";
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


    @RequestMapping(value = "/client_orders", method = RequestMethod.GET)
    String showClientOrders(@RequestParam(value = "page", required = false) Integer paramPage,
                            Model model, RedirectAttributes attributes, HttpSession session){

        User user = (User)session.getAttribute("user");

        if (checkParamsForErrors(null, paramPage, user, attributes)) {
            return "redirect:/flights";
        }

        if (null == paramPage) {
            paramPage = 1;
        }

        List<Order> orders = orderService.
                findByClientId(RESULTS_PER_PAGE, (paramPage - 1) * RESULTS_PER_PAGE, user.getId());
        long pageNumber = (int) Math.ceil(orderService.countByClientId(user.getId())/(double)RESULTS_PER_PAGE);

        if (orders.size() == 0) {
            model.addAttribute("message", "no_orders");
            return "show_orders";
        }

        model.addAttribute("page", paramPage);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("ordersList", orders);

        return "show_orders";
    }


    private boolean checkParamsForErrors(String paramSearch, Integer paramPage,
                                         User user, RedirectAttributes attributes) {

        if (null != paramSearch && paramSearch.length() > 20) {
            attributes.addFlashAttribute("message", "Size.chooseCountryForm.countryName");
            return true;
        }

        if (null == user || (null != paramPage && paramPage < 1)) {
            attributes.addFlashAttribute("message", "nothing_found");
            return true;
        }
        return false;
    }
}
