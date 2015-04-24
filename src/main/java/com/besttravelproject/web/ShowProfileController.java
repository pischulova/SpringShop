package com.besttravelproject.web;

import com.besttravelproject.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ShowProfileController {

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    ModelAndView show(HttpSession session, ModelAndView model) {
        User user = (User) session.getAttribute("user");

        model.addObject("user", user);

        model.setViewName("profile");
        return model;
    }
}
