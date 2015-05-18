package com.besttravelproject.web.User;

import com.besttravelproject.domain.User;
import com.besttravelproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegisterUserController {
    @Autowired
    private UserService userService;

    @Autowired @Qualifier("authManager")
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("userValidator")
    private Validator validator;

    @RequestMapping(value = "/register_user", method = RequestMethod.GET)
    String activate(Model model) {
        model.addAttribute("userDTO", new User());
        return "register_user";
    }

    @RequestMapping(value = "/register_user", method = RequestMethod.POST)
    String createUser(@Valid @ModelAttribute("userDTO") User user, Errors errors,
                      BindingResult bindingResult, Model model, HttpSession session) {
        validator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register_user";
        }

        try {
            userService.save(user);
        } catch (IllegalArgumentException e) {
            errors.rejectValue("username", "username_busy");
            return "register_user";
        }

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userDetails, user.getConfirmPassword(), userDetails.getAuthorities());
            authManager.authenticate(auth);

            if (auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                session.setAttribute("user", user);
                model.addAttribute("user", user);
                model.addAttribute("message", "sign_up_successful");

                return "home";
            }
        } catch (Exception e) {
            model.addAttribute("error_message", "bad_login");
            return "error";
        }

        return "home";
    }
}
