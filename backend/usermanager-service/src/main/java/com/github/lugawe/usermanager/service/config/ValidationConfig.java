package com.github.lugawe.usermanager.service.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.lugawe.usermanager.service.validation.RegexValidator;
import com.github.lugawe.usermanager.service.validation.Validator;

import javax.validation.constraints.NotEmpty;
import java.util.regex.Pattern;

public class ValidationConfig {

    @NotEmpty
    private String userName = RegexValidator.MATCH_ALL_REGEX;

    @NotEmpty
    private String userPassword = RegexValidator.MATCH_ALL_REGEX;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        checkValid(userName);
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        checkValid(userPassword);
        this.userPassword = userPassword;
    }

    //
    // validator
    //

    @JsonIgnore
    private void checkValid(String regex) {
        Pattern.compile(regex);
    }

    @JsonIgnore
    public Validator<String> userNameValidator(String input) {
        return new RegexValidator("userName", input, userName);
    }

    @JsonIgnore
    public Validator<String> userPasswordValidator(String input) {
        return new RegexValidator("userPassword", input, userPassword);
    }

}
