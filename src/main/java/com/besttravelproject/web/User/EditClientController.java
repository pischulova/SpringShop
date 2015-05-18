package com.besttravelproject.web.User;

import com.besttravelproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditClientController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin/add_to_blacklist/{id}", method = RequestMethod.GET)
    ModelAndView addToBlacklist(@PathVariable Long id, ModelAndView model) {

        userService.updateMakeBad(id, true);
        model.setViewName("redirect:/admin/show_clients");
        return model;
    }

    @RequestMapping(value = "/admin/unlock/{id}", method = RequestMethod.GET)
    ModelAndView removeFromBlacklist(@PathVariable Long id, ModelAndView model) {

        userService.updateMakeBad(id, false);
        model.setViewName("redirect:/admin/show_clients");
        return model;
    }
}
