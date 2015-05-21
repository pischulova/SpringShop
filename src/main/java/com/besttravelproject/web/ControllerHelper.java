package com.besttravelproject.web;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public final class ControllerHelper {
    private ControllerHelper() {}

    public static boolean checkParamsForErrors(String paramSearch, Integer paramPage, RedirectAttributes attributes) {
        if (null != paramSearch && paramSearch.length() > 20) {
            attributes.addFlashAttribute("message", "Size.chooseCountryForm.countryName");
            return true;
        }

        if (null != paramPage && paramPage < 1) {
            attributes.addFlashAttribute("message", "nothing_found");
            return true;
        }
        return false;
    }
}
