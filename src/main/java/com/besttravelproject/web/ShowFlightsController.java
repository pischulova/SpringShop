package com.besttravelproject.web;

import com.besttravelproject.domain.ChooseCountryForm;
import com.besttravelproject.domain.Flight;
import com.besttravelproject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ShowFlightsController {
    final static int RESULTS_PER_PAGE = 10;

    @Autowired
    private FlightService flightService;

    @ModelAttribute("chooseCountryForm")
    public ChooseCountryForm constructForm() {
        return new ChooseCountryForm();
    }

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    String show(Model model, HttpServletRequest request) {
        int page = checkPageParam(request.getParameter("page"), model);
        if (page == -1)
           return "error";

        String paramSearch = (String)request.getAttribute("search");

        List<Flight> flights;
        long pageNumber;
        if (null == paramSearch || paramSearch.length()==0) {
            flights = flightService.findAll(RESULTS_PER_PAGE, (page-1)*RESULTS_PER_PAGE + 1);
            pageNumber = flightService.getRowsNumber()/RESULTS_PER_PAGE;
        } else {
            flights = flightService.findByCountry(RESULTS_PER_PAGE, (page-1)*RESULTS_PER_PAGE + 1, paramSearch);
            pageNumber = flightService.getRowsNumberByCountry(paramSearch)/RESULTS_PER_PAGE;
        }

        model.addAttribute("search", paramSearch);

        if (flights.size()==0) {
            model.addAttribute("message", "nothing_found");
            return "show_flights";
        }
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("flightList", flights);

        return "show_flights";
    }

    @RequestMapping(value = "/flights", method = RequestMethod.POST)
    ModelAndView showFlights(@Valid @ModelAttribute("chooseCountryForm") ChooseCountryForm form,
                             BindingResult result, ModelAndView model, HttpServletRequest request,
                             RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Size.chooseCountryForm.countryName");
            model.setViewName("redirect:/flights");
            return model;
        }

        String paramSearch = form.getCountryName();
//        model.addObject("search", paramSearch);
        PagedListHolder<Flight> flights = new PagedListHolder<>(
                flightService.findByCountry(RESULTS_PER_PAGE, 1, paramSearch));

        if (flights.getNrOfElements()==0) {
            attributes.addFlashAttribute("message", "nothing_found");
            model.setViewName("redirect:/flights");
            return model;
        }
        int pageNumber = (int) Math.ceil(flights.getNrOfElements() / RESULTS_PER_PAGE);

        String paramPage = request.getParameter("page");
        if (null != paramPage) {
            if (paramPage.length() > 9 || !paramPage.matches("[0-9]+")) {
                model.addObject("error_message", "page_not_found");
                model.setViewName("error");
                return model;
            }
            Integer page = Integer.parseInt(paramPage);

            if (page < 1 || page > pageNumber) {
                model.addObject("error_message", "page_not_found");
                model.setViewName("error");
                return model;
            }
            flights.setPageSize(RESULTS_PER_PAGE);
            flights.setPage(page - 1);
        }
        model.addObject("pageNumber", pageNumber);
        model.addObject("flightList", flights);
        model.setViewName("show_flights");

        return model;
    }

    private int checkPageParam(String paramPage, Model model) {
        Integer pageInt = -1;
        if (null != paramPage) {
            if (paramPage.length() > 9 || !paramPage.matches("[0-9]+")) {
                model.addAttribute("error_message", "page_not_found");
                return -1;
            }
            pageInt = Integer.parseInt(paramPage);
            if (pageInt < 1) {
                model.addAttribute("error_message", "page_not_found");
                return -1;
            }
        } else {
            pageInt = 1;
        }
        model.addAttribute("page", pageInt);
        return pageInt;
    }
}
