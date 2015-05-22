package com.besttravelproject.web.Flight;

import com.besttravelproject.domain.Country;
import com.besttravelproject.domain.Flight;
import com.besttravelproject.forms.CountryDTO;
import com.besttravelproject.forms.EditFlightForm;
import com.besttravelproject.service.CountryService;
import com.besttravelproject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    String showEditFlightPage(@PathVariable Long id, Model model) {
        if (id < 1) {
            model.addAttribute("error_message", "page_not_found");
            return "error";
        }
        Flight flight = flightService.findById(id);

        if (null != flight) {
            model.addAttribute("flight", flight);
            model.addAttribute("message", "flight_updated");

            populateCountryLists(model);
        } else {
            model.addAttribute("message", "nothing_found");
        }

        return "edit_flight";
    }

    @RequestMapping(value = "/admin/edit_flight", params = "save", method = RequestMethod.POST)
    String editFlight(@Valid @ModelAttribute("editFlightForm") EditFlightForm form,
                      BindingResult result, Model model, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            populateCountryLists(model);
            return "edit_flight";
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

        return "redirect:/flights";
    }

    @RequestMapping(value = "/admin/edit_flight", params = "delete", method = RequestMethod.POST)
    String deleteFlight(@Valid @ModelAttribute("editFlightForm") EditFlightForm form,
                        RedirectAttributes attributes) {

        flightService.setDisabled(form.getId());
        attributes.addFlashAttribute("message", "flight_deleted");

        return "redirect:/flights";
    }


    private void populateCountryLists(Model model) {
        List<CountryDTO> countryListEn = new ArrayList<>();
        List<CountryDTO> countryListRu = new ArrayList<>();
        List<Country> countries = countryService.findAll();
        for (Country country : countries) {
            CountryDTO en = new CountryDTO();
            en.setId(country.getId());
            en.setName(country.getNameEn());
            countryListEn.add(en);

            CountryDTO ru = new CountryDTO();
            ru.setId(country.getId());
            ru.setName(country.getNameRu());
            countryListRu.add(ru);
        }

        model.addAttribute("countryListEn", countryListEn);
        model.addAttribute("countryListRu", countryListRu);
    }
}
