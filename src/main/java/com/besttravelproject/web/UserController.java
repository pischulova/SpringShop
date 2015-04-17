package com.besttravelproject.web;

import com.besttravelproject.domain.User;
import com.besttravelproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

//    @InitBinder
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    String activate(Model model) {
        model.addAttribute("userDTO", new User());
        return "home";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String createUser(@Valid @ModelAttribute("userDTO") User user, BindingResult bindingResult, Model model) {
        validator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "home";
        }

        // check username in DB

        userService.save(user);
        model.addAttribute("user", user);
        model.addAttribute("message", "User saved successfully");
        return "redirect:/user/show_all";
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
}
