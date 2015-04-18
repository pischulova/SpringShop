package com.besttravelproject.web;

import com.besttravelproject.domain.User;
import com.besttravelproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("userValidator")
    private Validator validator;


    @RequestMapping(value = "", method = RequestMethod.GET)
    String activate(Model model) {
        model.addAttribute("userDTO", new User());
        return "register_user";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    String createUser(@Valid @ModelAttribute("userDTO") User user, Errors errors,
                      BindingResult bindingResult, Model model) {
        validator.validate(user, bindingResult);

        User foundUser = userService.findByUsername(user.getUsername());
        if (null != foundUser) {
            errors.rejectValue("username", "username_busy");
            return "register_user";
        }

        if (bindingResult.hasErrors()) {
            return "register_user";
        }



        userService.save(user);
        model.addAttribute("user", user);
        model.addAttribute("message", "sign_up_successful");
        return "home";
    }

    @RequestMapping(value = "/show_all", method = RequestMethod.GET)
    @ResponseBody
    public String show() {
        List<User> userList = userService.findAll();
        StringBuilder sb = new StringBuilder();
        for (User user : userList) {
            sb.append(user.toString()).append("\n");
        }
        return sb.toString();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String loginUser(@RequestParam String username,
                     @RequestParam String pass,
                     Model model, HttpSession session) {

        User user = userService.login(username, pass);
        if (null == user) {
            model.addAttribute("message", "bad_login");
            return "home";
        }

        session.setAttribute("user", user);
        model.addAttribute("user", user);
        return "home";
    }
}
