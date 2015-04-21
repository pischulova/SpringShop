package com.besttravelproject.web;

import com.besttravelproject.domain.ChooseCountryForm;
import com.besttravelproject.domain.Flight;
import com.besttravelproject.service.CountryService;
import com.besttravelproject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class FlightController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private CountryService countryService;



//    @ModelAttribute("countryListEN")
//    public Map<Long, String> countryListEn(Model model){
//        Map<Long, String> countryItems = new HashMap<>();
//        List<Country> countryList = countryService.findAll();
//        for (Country country : countryList) {
//            countryItems.put(country.getId(), country.getNameEn());
//        }
//        model.addAttribute("countryItems", countryItems);
//        return countryItems;
//    }
//
//    @ModelAttribute("countryListRU")
//    public Map<Long, String> countryListRu(Model model){
//        Map<Long, String> countryItems = new HashMap<>();
//        List<Country> countryList = countryService.findAll();
//        for (Country country : countryList) {
//            countryItems.put(country.getId(), country.getNameRu());
//        }
//        model.addAttribute("countryItems", countryItems);
//        return countryItems;
//    }

    @ModelAttribute("chooseCountryForm")
    public ChooseCountryForm constructForm() {
        return new ChooseCountryForm();
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
    String showFlights() {

        return "flights";
    }

}
