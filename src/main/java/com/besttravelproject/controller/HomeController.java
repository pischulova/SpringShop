package com.besttravelproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {
//    private SpitterService spitterService;

//    @Inject
//    public HomeController(SpitterService spitterService) {
//        this.spitterService = spitterService;
//    }

//    @RequestMapping({"/", "/home"})
//    public String showHomePage(Map<String, Object> model) {           model to show
//        model.put("spittles", spitterService.getRecentSpittles(       spittles - key of the model
//                DEFAULT_SPITTLES_PER_PAGE));                          (in JSP we call the model as ${spittles})
//        return "home";

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
}
