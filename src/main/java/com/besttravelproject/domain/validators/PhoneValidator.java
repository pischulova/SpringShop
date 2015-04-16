package com.besttravelproject.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone phone) {

    }

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext constraintValidatorContext) {
        if (phoneNo == null){
            return false;
        }
        //validate phone numbers of format "0123456789"
        if (phoneNo.matches("0[0-9]{9}")) return true;
        else return false;
    }

//    public static void main(String[] args) {
//        PhoneValidator v = new PhoneValidator();
//        System.out.println(v.isValid("0930323717", null));
//        System.out.println(v.isValid("6930323717", null));
//        System.out.println(v.isValid("093---0717", null));
//
//    }

}
