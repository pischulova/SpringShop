package com.besttravelproject.web.User;

import com.besttravelproject.domain.User;
import com.besttravelproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class LoginUserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    String login(Principal principal, Model model, HttpSession session) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        session.setAttribute("user", user);
        model.addAttribute("user", user);
        return "home";
    }
}
