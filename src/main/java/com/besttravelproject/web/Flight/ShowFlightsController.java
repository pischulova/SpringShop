package com.besttravelproject.web.Flight;

import com.besttravelproject.domain.Flight;
import com.besttravelproject.service.FlightService;
import com.besttravelproject.web.ControllerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ShowFlightsController {
    final static int RESULTS_PER_PAGE = 10;

    @Autowired
    private FlightService flightService;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    String show(@RequestParam(value = "search", required = false) String paramSearch,
                @RequestParam(value = "page", required = false) Integer paramPage,
                Model model, RedirectAttributes attributes) {

        if (ControllerHelper.checkParamsForErrors(paramSearch, paramPage, attributes)) {
            return "redirect:/flights";
        }

        if (null == paramPage) {
            paramPage = 1;
        }

        List<Flight> flights;
        long pageNumber;
        if (null == paramSearch || paramSearch.length()==0) {
            flights = flightService.findAll(RESULTS_PER_PAGE, (paramPage-1)*RESULTS_PER_PAGE);
            pageNumber = (int) Math.ceil(flightService.countAll()/(double)RESULTS_PER_PAGE);
            paramSearch = "";
        } else {
            flights = flightService.findByCountry(RESULTS_PER_PAGE, (paramPage-1)*RESULTS_PER_PAGE, paramSearch);
            pageNumber = (int) Math.ceil(flightService.countByCountry(paramSearch)/(double)RESULTS_PER_PAGE);
        }

        if (flights.size()==0) {
            model.addAttribute("message", "nothing_found");
            return "show_flights";
        }
        model.addAttribute("page", paramPage);
        model.addAttribute("search", paramSearch);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("flightList", flights);

        return "show_flights";
    }
}
