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
import javax.servlet.http.HttpSession;

@Controller
public class ShowClientsController {
    final static double RESULTS_PER_PAGE = 10.0;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/show_clients", method = RequestMethod.GET)
    ModelAndView show(ModelAndView model, HttpServletRequest request, HttpSession session) {
        if (null == session.getAttribute("user")) {
            model.addObject("error_message", "page_not_found");
            model.setViewName("error");
            return model;
        }

        PagedListHolder<User> users = new PagedListHolder<>(userService.findAllByStatus(false));

        if (null == users || users.getNrOfElements()==0) {
            model.addObject("message", "nothing_found");
            model.setViewName("show_users");
            return model;
        }
        int pageNumber = (int) Math.ceil(users.getNrOfElements() / RESULTS_PER_PAGE);

        String paramPage = request.getParameter("page");
        if (null != paramPage) {
            if (paramPage.length() > 5) {
                model.addObject("error_message", "page_not_found");
                model.setViewName("error");
                return model;
            }

            Integer page = Integer.parseInt(paramPage);

            if (null == page || page < 1 || page > pageNumber) {
                model.addObject("error_message", "page_not_found");
                model.setViewName("error");
                return model;
            }
            users.setPageSize((int) RESULTS_PER_PAGE);
            users.setPage(page - 1);
        }
        model.addObject("pageNumber", pageNumber);
        model.addObject("usersList", users);
        model.addObject("listType", "clients");

        model.setViewName("show_users");
        return model;
    }

    @RequestMapping(value = "/show_blacklist", method = RequestMethod.GET)
    ModelAndView showBlacklist(ModelAndView model, HttpServletRequest request, HttpSession session) {
        if (null == session.getAttribute("user")) {
            model.addObject("error_message", "page_not_found");
            model.setViewName("error");
            return model;
        }

        PagedListHolder<User> users = new PagedListHolder<>(userService.findAllByStatus(true));

        if (null == users || users.getNrOfElements()==0) {
            model.addObject("message", "nothing_found");
            model.setViewName("show_users");
            return model;
        }
        int pageNumber = (int) Math.ceil(users.getNrOfElements() / RESULTS_PER_PAGE);

        String paramPage = request.getParameter("page");
        if (null != paramPage) {
            Integer page = Integer.parseInt(paramPage);

            if (null == page || page < 1 || page > pageNumber) {
                model.addObject("error_message", "page_not_found");
                model.setViewName("error");
                return model;
            }

            users.setPageSize((int)RESULTS_PER_PAGE);
            users.setPage(page-1);
        }
        model.addObject("pageNumber", pageNumber);

        model.addObject("usersList", users);
        model.addObject("listType", "blacklist");

        model.setViewName("show_users");
        return model;
    }

    @RequestMapping(value = "/show_admins", method = RequestMethod.GET)
    ModelAndView showAdmins(ModelAndView model, HttpServletRequest request, HttpSession session) {
        if (null == session.getAttribute("user")) {
            model.addObject("error_message", "page_not_found");
            model.setViewName("error");
            return model;
        }

        PagedListHolder<User> users = new PagedListHolder<>(userService.findAllByRole(UserRole.ADMIN));

        if (null == users || users.getNrOfElements()==0) {
            model.addObject("message", "nothing_found");
            model.setViewName("show_users");
            return model;
        }
        int pageNumber = (int) Math.ceil(users.getNrOfElements() / RESULTS_PER_PAGE);

        String paramPage = request.getParameter("page");
        if (null != paramPage) {
            Integer page = Integer.parseInt(paramPage);

            if (null == page || page < 1 || page > pageNumber) {
                model.addObject("error_message", "page_not_found");
                model.setViewName("error");
                return model;
            }

            users.setPageSize((int)RESULTS_PER_PAGE);
            users.setPage(page-1);
        }
        model.addObject("pageNumber", pageNumber);

        model.addObject("usersList", users);
        model.addObject("listType", "admins");

        model.setViewName("show_users");
        return model;
    }

}
