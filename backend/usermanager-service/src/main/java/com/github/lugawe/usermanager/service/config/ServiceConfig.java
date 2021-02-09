package com.github.lugawe.usermanager.service.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;

public class ServiceConfig {

    @Valid
    @JsonProperty("jwt")
    private JwtConfig jwtConfig = new JwtConfig();

    @Valid
    @JsonProperty("validation")
    private ValidationConfig validationConfig = new ValidationConfig();

    public JwtConfig getJwtConfig() {
        return jwtConfig;
    }

    public void setJwtConfig(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public ValidationConfig getValidationConfig() {
        return validationConfig;
    }

    public void setValidationConfig(ValidationConfig validationConfig) {
        this.validationConfig = validationConfig;
    }

}
