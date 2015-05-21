package com.besttravelproject.web.Flight;

import com.besttravelproject.domain.Country;
import com.besttravelproject.domain.Flight;
import com.besttravelproject.forms.EditFlightForm;
import com.besttravelproject.service.CountryService;
import com.besttravelproject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EditFlightController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private CountryService countryService;

    @ModelAttribute("editFlightForm")
    public EditFlightForm constructEditForm() {
        return new EditFlightForm();
    }

    @RequestMapping(value = "/admin/edit_flight/{id}", method = RequestMethod.GET)
    ModelAndView showEditFlightPage(@PathVariable Long id, ModelAndView model) {
        if (null == id || id < 1) {
            model.addObject("error_message", "page_not_found");
            model.setViewName("error");
            return model;
        }

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

    @RequestMapping(value = "/admin/edit_flight", params = "save", method = RequestMethod.POST)
    ModelAndView editFlight(@Valid @ModelAttribute("editFlightForm") EditFlightForm form,
                            BindingResult result, ModelAndView model, RedirectAttributes attributes) {

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

            attributes.addFlashAttribute("message", "flight_updated");
        } else {
            attributes.addFlashAttribute("message", "flight_not_updated");
        }

        model.setViewName("redirect:/flights");
        return model;
    }

    @RequestMapping(value = "/admin/edit_flight", params = "delete", method = RequestMethod.POST)
    ModelAndView deleteFlight(@Valid @ModelAttribute("editFlightForm") EditFlightForm form,
                              ModelAndView model,  RedirectAttributes attributes) {

        if (null != form) {
            flightService.setDisabled(form.getId());
            attributes.addFlashAttribute("message", "flight_deleted");
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
