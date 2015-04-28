package com.besttravelproject.web;

import com.besttravelproject.domain.Order;
import com.besttravelproject.domain.SearchOrderForm;
import com.besttravelproject.domain.User;
import com.besttravelproject.domain.UserRole;
import com.besttravelproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ShowOrdersController {
    final static double RESULTS_PER_PAGE = 10.0;

    @Autowired
    OrderService orderService;

    @ModelAttribute("searchOrderForm")
    public SearchOrderForm constructForm() {
        return new SearchOrderForm();
    }

    @RequestMapping(value = "/show_orders", method = RequestMethod.GET)
    String showOrders(Model model, HttpSession session, HttpServletRequest request){
        User user = (User)session.getAttribute("user");
        if (null == user) {
            model.addAttribute("error_message", "page_not_found");
            return "error";
        }

        PagedListHolder<Order> orders;

        if (user.getUserRole()== UserRole.ADMIN) {
            orders = new PagedListHolder<>(orderService.findAll());
            if (orders.getNrOfElements() == 0) {
                model.addAttribute("message", "nothing_found");
                return "show_orders";
            }
        } else {
            orders = new PagedListHolder<>(orderService.findByClientId(user.getId()));
            if (orders.getNrOfElements() == 0) {
                model.addAttribute("message", "no_orders");
                return "show_orders";
            }
        }
        int pageNumber = (int) Math.ceil(orders.getNrOfElements() / RESULTS_PER_PAGE);

        String paramPage = request.getParameter("page");
        if (null != paramPage) {
            if (paramPage.length() > 9 || !paramPage.matches("[0-9]+")) {
                model.addAttribute("error_message", "page_not_found");
                return "error";
            }

            Integer page = Integer.parseInt(paramPage);

            if (page < 1 || page > pageNumber) {
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

    @RequestMapping(value = "/show_orders", method = RequestMethod.POST)
    String showOrdersWithParam(@Valid @ModelAttribute("chooseCountryForm") SearchOrderForm form,
                               BindingResult result, Model model, HttpSession session,
                               HttpServletRequest request, RedirectAttributes attributes){
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Size.searchOrderForm.name");
            return "redirect:/flights";
        }

        if (null == session.getAttribute("user")) {
            model.addAttribute("error_message", "page_not_found");
            return "error";
        }

        PagedListHolder<Order> orders = new PagedListHolder<>(orderService.findByClientName(form.getName()));
        if (orders.getNrOfElements() == 0) {
            model.addAttribute("message", "nothing_found");
            return "show_orders";
        }

        int pageNumber = (int) Math.ceil(orders.getNrOfElements() / RESULTS_PER_PAGE);

        String paramPage = request.getParameter("page");
        if (null != paramPage) {
            if (paramPage.length() > 9 || !paramPage.matches("[0-9]+")) {
                model.addAttribute("error_message", "page_not_found");
                return "error";
            }

            Integer page = Integer.parseInt(paramPage);

            if (page < 1 || page > pageNumber) {
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
