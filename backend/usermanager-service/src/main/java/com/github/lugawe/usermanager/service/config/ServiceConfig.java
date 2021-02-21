package com.github.lugawe.usermanager.service.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.io.Serializable;

public class ServiceConfig implements Serializable {

    @Valid
    @JsonProperty("jwt")
    private JwtConfig jwtConfig = new JwtConfig();

    @Valid
    @JsonProperty("user")
    private UserConfig userConfig = new UserConfig();

    @Valid
    @JsonProperty("validation")
    private ValidationConfig validationConfig = new ValidationConfig();

    public JwtConfig getJwtConfig() {
        return jwtConfig;
    }

    public void setJwtConfig(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public UserConfig getUserConfig() {
        return userConfig;
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public ValidationConfig getValidationConfig() {
        return validationConfig;
    }

    public void setValidationConfig(ValidationConfig validationConfig) {
        this.validationConfig = validationConfig;
    }

}
