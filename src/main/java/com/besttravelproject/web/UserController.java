package com.besttravelproject.web;

import com.besttravelproject.domain.User;
import com.besttravelproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    String createUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/user/show_all";
    }

    @RequestMapping(value = "user/show_all", method = RequestMethod.GET)
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
