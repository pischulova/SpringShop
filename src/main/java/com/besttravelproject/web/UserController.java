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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("userValidator")
    private Validator validator;


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    String activate(Model model) {
        model.addAttribute("userDTO", new User());
        return "register_user";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    String createUser(@Valid @ModelAttribute("userDTO") User user, Errors errors,
                      BindingResult bindingResult, Model model, HttpSession session) {
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
        session.setAttribute("user", user);
        model.addAttribute("user", user);
        model.addAttribute("message", "sign_up_successful");
        return "home";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    String changeLangRegister() {
        return "register_user";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    String changeLangLogin() {
        return "home";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
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

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    String logout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (null != user) {
            session.removeAttribute("user");
        }
        return "home";
    }
}
