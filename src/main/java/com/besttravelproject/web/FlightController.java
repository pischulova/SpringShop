package com.besttravelproject.web;

import com.besttravelproject.domain.ChooseCountryForm;
import com.besttravelproject.domain.Country;
import com.besttravelproject.domain.EditFlightForm;
import com.besttravelproject.domain.Flight;
import com.besttravelproject.service.CountryService;
import com.besttravelproject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    String show(Model model) {
        List<Flight> flights = flightService.findAll();

        if (null != flights && !flights.isEmpty()) {
            model.addAttribute("flightList", flights);
        }

        return "flights";
    }

    @RequestMapping(value = "/flights", method = RequestMethod.POST)
    String showFlights(@Valid @ModelAttribute("chooseCountryForm") ChooseCountryForm form,
                       BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "flights";
        }

        List<Flight> flights = flightService.findByCountry(form.getCountryName());

        if (null != flights && !flights.isEmpty()) {
            model.addAttribute("flightList", flights);
        } else {
            model.addAttribute("message", "nothing_found");
        }

        return "flights";
    }

    @RequestMapping(value = "/editflight", method = RequestMethod.GET)
    String showEditFlightPage(@RequestParam("id") Long id, Model model) {
        Flight flight = flightService.findById(id);

        if (null != flight) {
            model.addAttribute("flight", flight);
            model.addAttribute("message", "flight_updated");

            Map<Long, Country> countryListEn = new HashMap<>();
            Map<Long, Country> countryListRu = new HashMap<>();
            List<Country> countries = countryService.findAll();
            for (Country country : countries) {
                countryListEn.put(country.getId(), country);
                countryListRu.put(country.getId(), country);
            }
            model.addAttribute("countryListEn", countryListEn);
            model.addAttribute("countryListRu", countryListRu);

        } else {
            model.addAttribute("message", "nothing_found");
        }

        return "edit_flight";
    }

    @RequestMapping(value = "/editflight", method = RequestMethod.POST)
    String editFlight(@Valid @ModelAttribute("editFlightForm") EditFlightForm form,
                      BindingResult result, Model model) {
//        Flight flight = flightService.findById(id);
//
//        if (null != flight) {
//            model.addAttribute("flight", flight);
//            model.addAttribute("message", "flight_updated");
//        } else {
//            model.addAttribute("message", "nothing_found");
//        }

        if (result.hasErrors()) {
            return "edit_flight";
        }

        return "flights";
    }
}
