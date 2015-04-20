package com.besttravelproject.web;

import com.besttravelproject.domain.Country;
import com.besttravelproject.service.CountryService;
import com.besttravelproject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FlightController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    String activate() {
        return "flights";
    }

    @ModelAttribute("countryListEN")
    public void countryListEn(Model model){
        Map<Long, String> countryItems = new HashMap<>();
        List<Country> countryList = countryService.findAll();
        for (Country country : countryList) {
            countryItems.put(country.getId(), country.getNameEn());
        }
        model.addAttribute("countryItems", countryItems);
    }

    @ModelAttribute("countryListRU")
    public void countryListRu(Model model){
        Map<Long, String> countryItems = new HashMap<>();
        List<Country> countryList = countryService.findAll();
        for (Country country : countryList) {
            countryItems.put(country.getId(), country.getNameRu());
        }
        model.addAttribute("countryItems", countryItems);
    }
}
