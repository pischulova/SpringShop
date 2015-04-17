package com.besttravelproject.domain.validators;

import com.besttravelproject.domain.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");

        User user = (User) obj;
        if (user.getUsername().length() < 3 || user.getUsername().length() > 20) {
            errors.rejectValue("username", "username.length.error");
        }

        if (!user.getPhone().matches("0[0-9]{9}")) {
            errors.rejectValue("phone", "phone.not.valid");
        }
    }
}
