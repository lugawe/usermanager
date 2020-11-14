package com.github.lugawe.usermanager.service.validation;

import java.util.Objects;
import java.util.regex.Pattern;

public class RegexValidator implements StringValidator {

    private final String value;
    private final String regex;

    public RegexValidator(String value, String regex) {
        this.value = Objects.requireNonNull(value);
        this.regex = Objects.requireNonNull(regex);
    }

    @Override
    public String get() throws ValidationException {
        if (isValid()) {
            return getValue();
        }
        throw new ValidationException();
    }

    @Override
    public boolean isValid() {
        return Pattern.compile(getRegex()).matcher(getValue()).matches();
    }

    public String getValue() {
        return value;
    }

    public String getRegex() {
        return regex;
    }

}
