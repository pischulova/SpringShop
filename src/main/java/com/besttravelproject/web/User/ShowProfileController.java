package com.besttravelproject.web.User;

import com.besttravelproject.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class ShowProfileController {

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    String show(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (null == user) {
            model.addAttribute("error_message", "page_not_found");
            return "error";
        }
        model.addAttribute("user", user);

        return "profile";
    }
}
