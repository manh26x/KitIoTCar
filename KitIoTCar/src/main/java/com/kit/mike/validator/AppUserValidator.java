package com.kit.mike.validator;

import com.kit.mike.dao.AppUserDao;
import com.kit.mike.entity.AppUser;
import com.kit.mike.formbean.AppUserForm;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AppUserValidator implements Validator {

    // common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private AppUserDao appUserDao;


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AppUserForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AppUserForm appUserForm = (AppUserForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.appUserForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appUserForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.appUserForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "NotEmpty.appUserForm.fullName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appUserForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.appUserForm.gender");

        if(!this.emailValidator.isValid(appUserForm.getEmail())) {
            errors.rejectValue("email", "Pattern.appUserForm.email");
        }
        if (!errors.hasFieldErrors("userName")) {
            AppUser dbUser = appUserDao.findUserAccount(appUserForm.getUserName());
            if (dbUser != null) {
                // Tên tài khoản đã bị sử dụng bởi người khác.
                errors.rejectValue("userName", "Duplicate.appUserForm.userName");
            }
        }

        if (!errors.hasErrors()) {
            if (!appUserForm.getConfirmPassword().equals(appUserForm.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.appUserForm.confirmPassword");
            }
        }
    }

}
