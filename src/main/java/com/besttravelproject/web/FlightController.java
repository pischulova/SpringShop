package com.besttravelproject.web;

import com.besttravelproject.domain.ChooseCountryForm;
import com.besttravelproject.domain.Country;
import com.besttravelproject.domain.EditFlightForm;
import com.besttravelproject.domain.Flight;
import com.besttravelproject.service.CountryService;
import com.besttravelproject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FlightController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private CountryService countryService;


    @ModelAttribute("chooseCountryForm")
    public ChooseCountryForm constructForm() {
        return new ChooseCountryForm();
    }

    @ModelAttribute("editFlightForm")
    public EditFlightForm constructEditForm() {
        return new EditFlightForm();
    }

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    ModelAndView show(ModelAndView model) {
        List<Flight> flights = flightService.findAll();

        if (null != flights && !flights.isEmpty()) {
            model.addObject("flightList", flights);
        }

        model.setViewName("flights");
        return model;
    }

    @RequestMapping(value = "/flights", method = RequestMethod.POST)
    ModelAndView showFlights(@Valid @ModelAttribute("chooseCountryForm") ChooseCountryForm form,
                       BindingResult result, ModelAndView model) {

        if (result.hasErrors()) {
            model.setViewName("flights");
            return model;
        }

        List<Flight> flights = flightService.findByCountry(form.getCountryName());

        if (null != flights && !flights.isEmpty()) {
            model.addObject("flightList", flights);
        } else {
            model.addObject("message", "nothing_found");
        }

        model.setViewName("flights");
        return model;
    }

    @RequestMapping(value = "/editflight", method = RequestMethod.GET)
    ModelAndView showEditFlightPage(@RequestParam("id") Long id, ModelAndView model) {
        Flight flight = flightService.findById(id);

        if (null != flight) {
            model.addObject("flight", flight);
            model.addObject("message", "flight_updated");

            populateCountryLists(model);
        } else {
            model.addObject("message", "nothing_found");
        }

        model.setViewName("edit_flight");
        return model;
    }

    @RequestMapping(value = "/editflight", params = "save", method = RequestMethod.POST)
    ModelAndView editFlight(@Valid @ModelAttribute("editFlightForm") EditFlightForm form,
                      BindingResult result, ModelAndView model) {

        if (result.hasErrors()) {
            populateCountryLists(model);
            model.setViewName("edit_flight");
            return model;
        }

        Flight flight = flightService.findById(form.getId());

        if (null != flight) {
            flight.setNameEn(form.getNameEn());
            flight.setNameRu(form.getNameRu());
            flight.setCountry(form.getCountry());
            flight.setPrice(form.getPrice());

            flightService.update(flight);
//                model.addObject("message", "flight_updated");
        } else {
            model.addObject("message", "flight_not_updated");
        }

        model.setViewName("redirect:/flights");
        return model;
    }

    @RequestMapping(value = "/editflight", params = "delete", method = RequestMethod.POST)
    ModelAndView deleteFlight(@Valid @ModelAttribute("editFlightForm") EditFlightForm form,
                           ModelAndView model) {

        if (null != form) {
            flightService.deleteById(form.getId());
        }
        model.setViewName("redirect:/flights");
        return model;
    }

    private void populateCountryLists(ModelAndView model) {
        Map<Long, String> countryListEn = new HashMap<>();
        Map<Long, String> countryListRu = new HashMap<>();
        List<Country> countries = countryService.findAll();
        for (Country country : countries) {
            countryListEn.put(country.getId(), country.getNameEn());
            countryListRu.put(country.getId(), country.getNameRu());
        }
        model.addObject("countryListEn", countryListEn);
        model.addObject("countryListRu", countryListRu);

    }
}
