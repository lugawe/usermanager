package com.github.lugawe.usermanager.service.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.lugawe.usermanager.service.validation.RegexValidator;
import com.github.lugawe.usermanager.service.validation.Validator;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.regex.Pattern;

public class ValidationConfig implements Serializable {

    @NotEmpty
    private String userName = RegexValidator.MATCH_ALL_REGEX;

    @NotEmpty
    private String userMail = RegexValidator.MATCH_ALL_REGEX;

    @NotEmpty
    private String userPassword = RegexValidator.MATCH_ALL_REGEX;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        checkValid(userName);
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        checkValid(userMail);
        this.userMail = userMail;
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
    protected void checkValid(String regex) {
        Pattern.compile(regex);
    }

    @JsonIgnore
    public Validator<String> userNameValidator(String input) {
        return new RegexValidator("userName", input, userName);
    }

    @JsonIgnore
    public Validator<String> userMailValidator(String input) {
        return new RegexValidator("userMail", input, userMail);
    }

    @JsonIgnore
    public Validator<String> userPasswordValidator(String input) {
        return new RegexValidator("userPassword", input, userPassword);
    }

}
