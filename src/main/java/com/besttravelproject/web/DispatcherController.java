package com.besttravelproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispatcherController {

    @RequestMapping(value = "/")
    String showHomePage() {
        return "home";
    }
}
