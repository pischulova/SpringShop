package com.besttravelproject.domain.validators;

import com.besttravelproject.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component(value = "userValidator")
public class UserFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;

        if (user.getUsername().length() < 3 || user.getUsername().length() > 20 || user.getUsername().contains(" ")) {
            errors.rejectValue("username", "username.not.valid");
        }

        if (user.getPassword().length() < 3 || user.getPassword().length() > 20 || user.getPassword().contains(" ")) {
            errors.rejectValue("password", "password.not.valid");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "passwords.not.match");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");

        if (!user.getPhone().matches("0[0-9]{9}")) {
            errors.rejectValue("phone", "phone.not.valid");
        }
    }
}
