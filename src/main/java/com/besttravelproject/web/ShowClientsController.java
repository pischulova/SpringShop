package com.besttravelproject.web;

import com.besttravelproject.domain.User;
import com.besttravelproject.domain.UserRole;
import com.besttravelproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShowClientsController {
    final static int RESULTS_PER_PAGE = 10;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/show_clients", method = RequestMethod.GET)
    String showClients(@RequestParam(value = "page", required = false) Integer paramPage,
                      Model model, HttpSession session) {

        if (checkParamsForErrors((User)session.getAttribute("user"), paramPage, model)) {
            return "error";
        }

        if (null == paramPage) {
            paramPage = 1;
        }

        List<User> users = userService.findAllByStatus(RESULTS_PER_PAGE, (paramPage-1)*RESULTS_PER_PAGE, false);
        long pageNumber = (int) Math.ceil(userService.countByStatus(false)/(double)RESULTS_PER_PAGE);

        if (users.size()==0) {
            model.addAttribute("message", "nothing_found");
            return "show_users";
        }
        model.addAttribute("page", paramPage);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("usersList", users);
        model.addAttribute("listType", "clients");

        return "show_users";
    }

    @RequestMapping(value = "/show_blacklist", method = RequestMethod.GET)
    String showBadClients(@RequestParam(value = "page", required = false) Integer paramPage,
                Model model, HttpSession session) {

        if (checkParamsForErrors((User)session.getAttribute("user"), paramPage, model)) {
            return "error";
        }

        if (null == paramPage) {
            paramPage = 1;
        }

        List<User> users = userService.findAllByStatus(RESULTS_PER_PAGE, (paramPage-1)*RESULTS_PER_PAGE, true);
        long pageNumber = (int) Math.ceil(userService.countByStatus(true)/(double)RESULTS_PER_PAGE);

        if (users.size()==0) {
            model.addAttribute("message", "nothing_found");
            return "show_users";
        }
        model.addAttribute("page", paramPage);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("usersList", users);
        model.addAttribute("listType", "clients");

        return "show_users";
    }

    @RequestMapping(value = "/show_admins", method = RequestMethod.GET)
    String showAdmins(@RequestParam(value = "page", required = false) Integer paramPage,
                Model model, HttpSession session) {

        if (checkParamsForErrors((User)session.getAttribute("user"), paramPage, model)) {
            return "error";
        }

        if (null == paramPage) {
            paramPage = 1;
        }

        List<User> users = userService.findAllByRole(RESULTS_PER_PAGE, (paramPage - 1) * RESULTS_PER_PAGE, UserRole.ADMIN);
        long pageNumber = (int) Math.ceil(userService.countByRole(UserRole.ADMIN)/(double)RESULTS_PER_PAGE);

        if (users.size()==0) {
            model.addAttribute("message", "nothing_found");
            return "show_users";
        }
        model.addAttribute("page", paramPage);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("usersList", users);
        model.addAttribute("listType", "clients");

        return "show_users";
    }

    private boolean checkParamsForErrors(User user, Integer paramPage, Model model) {
        if (null == user || (null != paramPage && paramPage < 1)) {
            model.addAttribute("error_message", "page_found");
            return true;
        }
        return false;
    }
}
