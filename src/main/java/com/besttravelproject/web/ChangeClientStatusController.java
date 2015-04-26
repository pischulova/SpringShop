package com.besttravelproject.web;

import com.besttravelproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChangeClientStatusController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/add_to_blacklist", method = RequestMethod.GET)
    ModelAndView addToBlacklist(@RequestParam("id") Long id, ModelAndView model) {

        userService.updateMakeBad(id, true);
        model.setViewName("redirect:/show_clients");
        return model;
    }

    @RequestMapping(value = "/unlock", method = RequestMethod.GET)
    ModelAndView removeFromBlacklist(@RequestParam("id") Long id, ModelAndView model) {

        userService.updateMakeBad(id, false);
        model.setViewName("redirect:/show_clients");
        return model;
    }
}
