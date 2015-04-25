package com.besttravelproject.web;

import com.besttravelproject.domain.User;
import com.besttravelproject.domain.UserRole;
import com.besttravelproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShowClientsController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/show_clients", method = RequestMethod.GET)
    ModelAndView show(ModelAndView model, HttpServletRequest request) {

        PagedListHolder<User> users = new PagedListHolder<>(userService.findAllByRole(UserRole.CLIENT));
        users.setPageSize(10);
        if (null != request.getParameter("page")) {
            int page = Integer.parseInt(request.getParameter("page"));
            users.setPage(page);
        }
        model.addObject("usersList", users);
        model.addObject("listType", "clients");

//        List<User> users = userService.findAll();
//
//        if (null != users && !users.isEmpty()) {
//            model.addObject("usersList", users);
//            model.addObject("listType", "clients");
//        }
        model.setViewName("show_users");
        return model;
    }

    @RequestMapping(value = "/show_blacklist", method = RequestMethod.GET)
    ModelAndView showBlacklist(ModelAndView model) {
        List<User> users = userService.findAllByStatus(true);

        if (null != users && !users.isEmpty()) {
            model.addObject("usersList", users);
            model.addObject("listType", "blacklist");
        }
        model.setViewName("show_users");
        return model;
    }

    @RequestMapping(value = "/show_admins", method = RequestMethod.GET)
    ModelAndView showAdmins(ModelAndView model) {
        List<User> users = userService.findAllByRole(UserRole.ADMIN);

        if (null != users && !users.isEmpty()) {
            model.addObject("usersList", users);
            model.addObject("listType", "admins");
        }
        model.setViewName("show_users");
        return model;
    }
}
