package com.besttravelproject.web;

import com.besttravelproject.domain.User;
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

    @RequestMapping(value = "/show_clients", method = RequestMethod.GET) // refactor jsp and 2 other methods
    String show(@RequestParam(value = "page", required = false) Integer paramPage,
                      Model model, HttpSession session) {

        if (checkParamsForErrors((User)session.getAttribute("user"), paramPage, model)) {
            return "error";
        }

        if (null == paramPage) {
            paramPage = 1;
        }

        List<User> users = userService.findAllByStatus(RESULTS_PER_PAGE, (paramPage-1)*RESULTS_PER_PAGE, false);
        long pageNumber = (int) Math.ceil(userService.countByStatus(false));

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

//    @RequestMapping(value = "/show_blacklist", method = RequestMethod.GET)
//    ModelAndView showBlacklist(ModelAndView model, HttpServletRequest request, HttpSession session) {
//        if (null == session.getAttribute("user")) {
//            model.addObject("error_message", "page_not_found");
//            model.setViewName("error");
//            return model;
//        }
//
//        PagedListHolder<User> users = new PagedListHolder<>(userService.findAllByStatus(true));
//
//        if (users.getNrOfElements()==0) {
//            model.addObject("message", "nothing_found");
//            model.setViewName("show_users");
//            return model;
//        }
//        int pageNumber = (int) Math.ceil(users.getNrOfElements() / RESULTS_PER_PAGE);
//
//        String paramPage = request.getParameter("page");
//        if (null != paramPage) {
//            if (paramPage.length() > 9 || !paramPage.matches("[0-9]+")) {
//                model.addObject("error_message", "page_not_found");
//                model.setViewName("error");
//                return model;
//            }
//            Integer page = Integer.parseInt(paramPage);
//
//            if (page < 1 || page > pageNumber) {
//                model.addObject("error_message", "page_not_found");
//                model.setViewName("error");
//                return model;
//            }
//
//            users.setPageSize((int)RESULTS_PER_PAGE);
//            users.setPage(page-1);
//        }
//        model.addObject("pageNumber", pageNumber);
//        model.addObject("usersList", users);
//        model.addObject("listType", "blacklist");
//
//        model.setViewName("show_users");
//        return model;
//    }
//
//    @RequestMapping(value = "/show_admins", method = RequestMethod.GET)
//    ModelAndView showAdmins(ModelAndView model, HttpServletRequest request, HttpSession session) {
//        if (null == session.getAttribute("user")) {
//            model.addObject("error_message", "page_not_found");
//            model.setViewName("error");
//            return model;
//        }
//
//        PagedListHolder<User> users = new PagedListHolder<>(userService.findAllByRole(UserRole.ADMIN));
//
//        if (users.getNrOfElements()==0) {
//            model.addObject("message", "nothing_found");
//            model.setViewName("show_users");
//            return model;
//        }
//        int pageNumber = (int) Math.ceil(users.getNrOfElements() / RESULTS_PER_PAGE);
//
//        String paramPage = request.getParameter("page");
//        if (null != paramPage) {
//            if (paramPage.length() > 9 || !paramPage.matches("[0-9]+")) {
//                model.addObject("error_message", "page_not_found");
//                model.setViewName("error");
//                return model;
//            }
//            Integer page = Integer.parseInt(paramPage);
//
//            if (page < 1 || page > pageNumber) {
//                model.addObject("error_message", "page_not_found");
//                model.setViewName("error");
//                return model;
//            }
//
//            users.setPageSize((int)RESULTS_PER_PAGE);
//            users.setPage(page-1);
//        }
//        model.addObject("pageNumber", pageNumber);
//        model.addObject("usersList", users);
//        model.addObject("listType", "admins");
//
//        model.setViewName("show_users");
//        return model;
//    }


    private boolean checkParamsForErrors(User user, Integer paramPage, Model model) {
        if (null == user || (null != paramPage && paramPage < 1)) {
            model.addAttribute("error_message", "page_found");
            return true;
        }
        return false;
    }
}
