package com.besttravelproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    @RequestMapping(value = "/addtocart", method = RequestMethod.GET)
    String editFlight(@RequestParam("id") Long id) {

        return "cart";
    }
}
