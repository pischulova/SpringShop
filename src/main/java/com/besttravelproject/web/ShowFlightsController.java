package com.besttravelproject.web;

import com.besttravelproject.domain.ChooseCountryForm;
import com.besttravelproject.domain.Flight;
import com.besttravelproject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ShowFlightsController {
    final static double RESULTS_PER_PAGE = 10.0;

    @Autowired
    private FlightService flightService;

    @ModelAttribute("chooseCountryForm")
    public ChooseCountryForm constructForm() {
        return new ChooseCountryForm();
    }

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    ModelAndView show(ModelAndView model, HttpServletRequest request) {
        String paramSearch = (String)request.getAttribute("search");

        PagedListHolder<Flight> flights;
        if (null == paramSearch || paramSearch.length()==0) {
            paramSearch = "";
            flights = new PagedListHolder<>(flightService.findByCountry(paramSearch));
        } else {
            flights = new PagedListHolder<>(flightService.findAll());
        }
        model.addObject("search", paramSearch);

        if (flights.getNrOfElements()==0) {
            model.addObject("message", "nothing_found");
            model.setViewName("show_flights");
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
            flights.setPageSize((int) RESULTS_PER_PAGE);
            flights.setPage(page - 1);
        }
        model.addObject("pageNumber", pageNumber);
        model.addObject("flightList", flights);

        model.setViewName("show_flights");
        return model;
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
        model.addObject("search", paramSearch);
        PagedListHolder<Flight> flights = new PagedListHolder<>(flightService.findByCountry(paramSearch));

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
            flights.setPageSize((int) RESULTS_PER_PAGE);
            flights.setPage(page - 1);
        }
        model.addObject("pageNumber", pageNumber);
        model.addObject("flightList", flights);
        model.setViewName("show_flights");

        return model;
    }
}
