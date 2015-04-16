package com.besttravelproject.domain.validators;

import com.besttravelproject.domain.User;
import org.springframework.validation.*;

public class UserFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");

        User user = (User) obj;
        if (user.getUsername().length() < 3 || user.getUsername().length() > 20){
            errors.rejectValue("username", "username.length.error");
        }
    }
}
